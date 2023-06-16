package exchanger;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import commands.Command;
import commands.CommandManager;
import data.StudyGroup;

/**
 * Listens to the commands from client
 */
public class Listener {
    private final CommandManager commandManager;
    private ForkJoinPool readPool = ForkJoinPool.commonPool();
    private ExecutorService handlerPool = Executors.newCachedThreadPool();

    public Listener(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String exchange(SerializableCommand serializableCommand) throws IOException {
        SerializableStudyGroup serializableStudyGroup = serializableCommand.getStudyGroup();
        String argument = serializableCommand.getArgument();
        StudyGroup studyGroup = null;
        if (serializableStudyGroup != null) {
            studyGroup = serializableStudyGroup.toStudyGroup();
        }
        Command command = commandManager.getCommands().get(serializableCommand.getName());
        if (command == null) {
            return "There is no such command!";
        }
        return command.apply(argument, studyGroup, serializableCommand.getUsername(), serializableCommand.getPassword());
    }

    public void handleCommands() {
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            System.out.println("Server is started...");
            serverSocket.bind(new InetSocketAddress("localhost", 5761));
            serverSocket.configureBlocking(true);
            while (true) {
                handleAccept(serverSocket);
            }
        } catch (BindException e) {
            System.out.println("The port is busy. Try changing the port");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Something went wrong when starting server");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void handleAccept(ServerSocketChannel mySocket) throws IOException {
        SocketChannel client = mySocket.accept();
        if (client == null) {
            return;
        }
        System.out.println(String.format("Connection accepted [%s]", client.getRemoteAddress().toString()));
        readPool.submit(new ClientHandler(handlerPool, client, commandManager));
    }
}

//java -jar name.jar
// для каждо

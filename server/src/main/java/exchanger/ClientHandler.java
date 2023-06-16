package exchanger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RecursiveAction;

import commands.Command;
import commands.CommandManager;
import data.StudyGroup;

public class ClientHandler extends RecursiveAction {
    private SocketChannel client;
    private ExecutorService handlerPool;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private CommandManager commandManager;

    public ClientHandler(ExecutorService handlerPool, SocketChannel client, CommandManager commandManager) throws IOException {
        this.handlerPool = handlerPool;
        this.client = client;
        try {
            this.outputStream = new ObjectOutputStream(client.socket().getOutputStream());
            this.inputStream = new ObjectInputStream(client.socket().getInputStream());
        } catch (IOException e) {
            client.close();
        }
        this.commandManager = commandManager;
    }

    @Override
    protected void compute() {
        while (true) {
            try {
                System.out.println("Reading request...");
                SerializableCommand request = (SerializableCommand) inputStream.readObject();
                System.out.println(String.format("Request of '%s' read by %s", request.getName(), request.getUsername()));
                handlerPool.submit(() -> {
                    try {
                        exchange(request);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (ClosedChannelException e) {
                System.out.println("Client disconnected");
                return;
            } catch (IOException | ClassNotFoundException e) {
                try {
                    client.close();
                    return;
                } catch (IOException e1) {
                    System.out.println("Something went wrong when handling reading");
                    e1.printStackTrace();
                    return;
                }
            }
        }
    }

    private void exchange(SerializableCommand serializableCommand) throws IOException {
        SerializableStudyGroup serializableStudyGroup = serializableCommand.getStudyGroup();
        String argument = serializableCommand.getArgument();
        StudyGroup studyGroup = null;
        if (serializableStudyGroup != null) {
            studyGroup = serializableStudyGroup.toStudyGroup();
        }
        Command command = commandManager.getCommands().get(serializableCommand.getName());
        if (command == null) {
            new Thread(new RequestWriter("There is no such command!", client, outputStream)).start();
            return;
        }
        System.out.println("Handling command...");
        String answer = command.apply(argument, studyGroup, serializableCommand.getUsername(), serializableCommand.getPassword());
        System.out.println("Command handled");
        new Thread(new RequestWriter(answer, client, outputStream)).start();
    }
}

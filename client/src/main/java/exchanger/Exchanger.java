package exchanger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import data.StudyGroup;

/**
 * Class, that sends commands to the server
 */
public class Exchanger implements AutoCloseable {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Exchanger() {
        this.socket = openConnection();
    }

    public String exchange(String commandName, String argument, StudyGroup studyGroup, String username, String password) {
        SerializableCommand serializableCommand = new SerializableCommand(
                commandName, argument, SerializableStudyGroup.fromStudyGroup(studyGroup), null, username, password
        );
        return exchange(serializableCommand).getAnswer();
    }

    private SerializableCommand exchange(SerializableCommand serializableCommand) {
        while (true) {
            try {
                outputStream.writeObject(serializableCommand);
                return (SerializableCommand) inputStream.readObject();
            } catch (IOException e) {
                System.out.println("Connection to the server lost. Trying to reopen...");
                reopenConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private Socket openConnection() {
        Socket socket;
        while (true) {
            try {
                System.out.println("Trying to open socket connection...");
                socket = new Socket("localhost", 5761);
                this.outputStream = new ObjectOutputStream(socket.getOutputStream());
                this.inputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("Connection successfully opened.");
                return socket;
            } catch (ConnectException e) {
                System.out.println("Couldn't open connection. Retrying in 3s...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void reopenConnection() {
        this.socket = openConnection();
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}

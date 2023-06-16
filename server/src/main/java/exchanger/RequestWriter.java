package exchanger;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;

public class RequestWriter implements Runnable {
    private SocketChannel client;
    private String answer;
    private ObjectOutputStream stream;

    public RequestWriter(String answer, SocketChannel client, ObjectOutputStream stream) throws IOException {
        this.answer = answer;
        this.client = client;
        this.stream = stream;
    }

    @Override
    public void run() {
        try {
            System.out.println("Writing answer...");
            stream.writeObject(new SerializableCommand(null, null, null, answer, null, null));
            System.out.println("Answer written");
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException e1) {
                System.out.println("Something went wrong when handling reading");
            }
            System.out.println("Client disconnected");
        }
    }
}

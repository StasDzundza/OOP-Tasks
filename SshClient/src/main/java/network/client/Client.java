package network.client;

import network.SocketReader;
import network.SocketWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;

public class Client {
    InetSocketAddress inetAddress;
    private String ipAddress;
    private int port;
    private SocketWriter writer;
    private SocketReader reader;
    private CustomLogger log;

    public Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        inetAddress = new InetSocketAddress(ipAddress, port);
        log = new CustomLogger(Client.class.getName());
        reader = new SocketReader();
        writer = new SocketWriter();
    }

    public void connect() {
        try (SocketChannel channel = SocketChannel.open(this.inetAddress)) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            String answer;
            answer = reader.read(channel);
            log.logMessage(String.format("Received from server : %s",answer));
            while ((userInput = stdIn.readLine()) != null) {
                log.logMessage(String.format("Prepared message : %s", userInput));
                ByteBuffer b = writer.write(channel,userInput);
                log.logMessage(String.format("Sending Message: %s\nbufferBytes: %d", userInput, b.position()));
                answer = reader.read(channel);
                log.logMessage(String.format("Received from server : %s",answer));
            }
        } catch (IOException e) {
            log.logMessage(Level.SEVERE, e.toString(), e);
        }
    }

    public InetSocketAddress getActiveAdress() {
        return inetAddress;
    }
}

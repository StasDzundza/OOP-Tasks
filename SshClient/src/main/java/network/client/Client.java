package network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static Logger log = Logger.getLogger(Client.class.getName());
    InetSocketAddress inetAddress;
    private String ipAddress;
    private int port;
    private static Charset charset = Charset.forName("UTF-8");
    public static final CharsetEncoder encoder = charset.newEncoder();
    public static final CharsetDecoder decoder = charset.newDecoder();

    public Client() {
    }

    public Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        inetAddress = new InetSocketAddress(ipAddress, port);
    }

    public void connect() {
        try (SocketChannel channel = SocketChannel.open(this.inetAddress)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            String answer = Client.read(channel, buffer);
            log.info("Received from server : " + answer);
            while ((userInput = stdIn.readLine()) != null) {
                log.info("Prepared message: " + userInput);
                ByteBuffer b = Client.write(channel, buffer, userInput);
                log.info(String.format("Sending Message: %s\nbufferBytes: %d", userInput, b.position()));
                answer = Client.read(channel, buffer);
                log.info("Received from server : " + answer);
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, e.toString(), e);
        }
    }

    public static String read(final SocketChannel channel, ByteBuffer byteBuffer) throws IOException {
        byteBuffer.clear();
        channel.read(byteBuffer);
        byteBuffer.flip();
        return new String(byteBuffer.array(), StandardCharsets.UTF_8).trim();
    }

    public static ByteBuffer write(final SocketChannel channel, ByteBuffer byteBuffer, final String userInput) throws IOException {
        byteBuffer.put(encoder.encode(CharBuffer.wrap(userInput)));
        byteBuffer.flip();
        channel.write(byteBuffer);
        return byteBuffer;
    }

    public InetSocketAddress getActiveAdress() {
        return inetAddress;
    }
}

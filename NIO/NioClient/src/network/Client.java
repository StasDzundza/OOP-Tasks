package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.*;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) {
        try {
            System.out.println("Starting client...");

            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 1111));
            if(client.isConnected()){
                String data;
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String userInput;
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while ((userInput = stdIn.readLine()) != null) {
                    System.out.println("Prepared message: " + userInput);
                    buffer.put(userInput.getBytes());
                    buffer.flip();
                    int bytesWritten = client.write(buffer);
                    System.out.println(String.format("Sending Message: %s\nbufforBytes: %d", userInput, bytesWritten));
                    buffer.clear();
                    client.read(buffer);
                    buffer.flip();
                    data = new String(buffer.array()).trim();
                    System.out.println("Received " + data);
                }

                client.close();
                System.out.println("Client connection closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
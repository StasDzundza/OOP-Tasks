package network.client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 1111);
        client.connect();
    }
}

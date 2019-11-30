package network;

import java.io.IOException;

public class Main {
    public static void main(String[]args) throws IOException {
        Client c = new Client("127.0.0.1",1111);
        c.connect();
        Person person = new Person("Messi","Barcelona","Argentina",32,20);
        c.sendObject(person);
    }
}

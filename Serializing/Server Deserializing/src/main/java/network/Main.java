package network;

import java.io.IOException;

public class Main {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Server s = new Server();
        s.checkConnection();
        Object object = s.readData();
        FootballPlayer player = (FootballPlayer)object;
        player.writeInFile("player.txt");
    }
}

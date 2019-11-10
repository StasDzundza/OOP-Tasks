package network;

import sun.rmi.runtime.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private ServerSocket server = null;
    private final int port = 1111;
    private Logger log;

    public Server() throws IOException {
        server = new ServerSocket(port);
        log = Logger.getLogger(Server.class.getName());
    }

    public void checkConnection() throws IOException, ClassNotFoundException {
        Socket client = server.accept();
        log.info("Connection is accepted");
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());

        FootballPlayer player = readData(client);
        log.info("Object has been received");
        player.showInformation();

        closeStreams();
        client.close();
    }

    private void closeStreams() throws IOException {
        out.close();
        in.close();
        server.close();
    }

    private FootballPlayer readData(final Socket client) throws IOException, ClassNotFoundException {
        FootballPlayer player = (FootballPlayer) in.readObject();
        return player;
    }

    public int getLocalPort(){
        return server.getLocalPort();
    }

}
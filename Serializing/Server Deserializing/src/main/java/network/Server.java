package network;

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
    private Socket client;

    public Server() throws IOException {
        server = new ServerSocket(port);
        log = Logger.getLogger(Server.class.getName());
    }

    public void checkConnection() throws IOException, ClassNotFoundException {
        client = server.accept();
        log.info("Connection is accepted");
    }

    private void closeStreams() throws IOException {
        out.close();
        in.close();
        server.close();
    }

    public Object readData() throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
        log.info("Object has been received");
        Object obj = in.readObject();
        closeStreams();
        client.close();
        return obj;
    }

    public int getLocalPort(){
        return server.getLocalPort();
    }

}
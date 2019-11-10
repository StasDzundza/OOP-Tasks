package network;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {
    private Socket client = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private String ip;
    private int port;
    private Logger log;

    public Client() {
        ip = "127.0.0.1";
        port = 1111;
        log = Logger.getLogger(Client.class.getName());
    }

    public Client(String ipAddress, int port){
        ip = ipAddress;
        this.port = port;
        log = Logger.getLogger(Client.class.getName());
    }

    public void connect() throws IOException {
        client = new Socket(ip, port);
        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
        log.info(String.format("Connected to the %s",client.getInetAddress().getHostAddress()));
    }

    public void sendObject(FootballPlayer player) throws IOException {
        if(client.isConnected()){
            out.writeObject(player);
            out.flush();
            closeStreams();
            log.info("Object has been sent successfully");
        }
        else{
            log.info("You are not connected to the server. Use \"connect\" firstly");
        }
    }

    private void closeStreams() throws IOException {
        out.close();
        in.close();
        client.close();
    }

    public int getLocalPort() {
        return port;
    }

    public String getHostAddress() {
        return ip;
    }

}
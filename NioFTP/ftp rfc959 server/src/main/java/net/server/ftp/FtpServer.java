package net.server.ftp;

import net.server.Configuration;
import net.server.FtpReader;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class FtpServer {
    Configuration configuration;
    private ServerSocketChannel serverChannel;
    private Selector selector;
    private Logger logger = Logger.getLogger(FtpServer.class.getName());
    private FtpReader reader;

    public FtpServer() {
        configuration = new Configuration();
    }

    public void start() throws IOException {
        configureServer();
        acceptConnections();
    }

    public void setActiveAddress(String host,int port){
        configuration.setAddress(host, port);
    }

    public InetAddress getActiveAddress(){
        return serverChannel.socket().getInetAddress();
    }

    private void configureServer() throws IOException {
        selector = Selector.open();
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(configuration.getActiveAddress());
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void acceptConnections() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> i = selectedKeys.iterator();

            while (i.hasNext()) {
                SelectionKey key = i.next();

                if (key.isAcceptable()) {
                    handleAccept();
                } else if (key.isReadable()) {
                    handleRead(key);
                }
                i.remove();
            }
        }
    }

    private void handleAccept() throws IOException {
        SocketChannel client = serverChannel.accept();
        logger.info("Connection from " + client.toString() + " accepted.");
        client.configureBlocking(false);
        SelectionKey sk = client.register(selector, SelectionKey.OP_READ,SelectionKey.OP_WRITE);
        String response = "220 \r\n";
        sk.attach(response);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        Path path = Paths.get(configuration.getOutputPath());
        FileChannel fileChannel = FileChannel.open(path,
                EnumSet.of(StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.WRITE));
        ByteBuffer buffer = ByteBuffer.allocate(configuration.getBufferSize());
        FtpReader reader = new FtpReader();
        int numOfReadedBytes = reader.readFile(client,fileChannel,configuration.getBufferSize());
        logger.info(numOfReadedBytes + " bytes were readed");
        fileChannel.close();
        client.close();
    }
}

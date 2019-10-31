package network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SocketReader {

    private final int BufferSize = 1024;

    public String read(final SocketChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BufferSize);
        byteBuffer.clear();
        channel.read(byteBuffer);
        byteBuffer.flip();
        return new String(byteBuffer.array(), StandardCharsets.UTF_8).trim();
    }
}

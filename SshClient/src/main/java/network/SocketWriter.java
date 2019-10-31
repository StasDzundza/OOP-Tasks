package network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class SocketWriter {

    private Charset charset;
    private CharsetEncoder encoder;
    private CharsetDecoder decoder;

    public SocketWriter(){
        charset = Charset.forName("UTF-8");
        setCharset(charset);
    }

    public void setCharset(Charset charset){
        this.charset = charset;
        charset = Charset.forName("UTF-8");
        encoder = charset.newEncoder();
        decoder = charset.newDecoder();
    }

    public Charset getCharset() {
        return charset;
    }

    public ByteBuffer write(final SocketChannel channel, final String userInput) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(userInput.length()*2);
        byteBuffer.put(encoder.encode(CharBuffer.wrap(userInput)));
        byteBuffer.flip();
        channel.write(byteBuffer);
        return byteBuffer;
    }
}

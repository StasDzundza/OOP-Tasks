import network.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestClient {
    @Mock
    public SocketChannel channel;

    @InjectMocks
    Client c;

    private final String testStr = "Test";

    public TestClient() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkAddress() {
        c = new Client("localhost", 22);
        InetSocketAddress addr = c.getActiveAdress();
        Assert.assertEquals("localhost", addr.getHostName());
        Assert.assertEquals(22, addr.getPort());
        Assert.assertEquals(1, 1);
    }

    @Test
    public void checkWriting() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        when(channel.write((ByteBuffer) any())).thenReturn(testStr.length());
        ByteBuffer result = c.write(channel, buffer, testStr);
        Assert.assertEquals(testStr.length(), result.remaining());
        String writedData = new String(result.array(), StandardCharsets.UTF_8).trim();
        Assert.assertEquals(testStr, writedData);
        verify(channel).write(buffer);
        verify(channel, times(1)).write(buffer);

    }

    @Test
    public void checkReading() throws IOException {
        ByteBuffer b = ByteBuffer.allocate(1024);
        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
        when(channel.read((ByteBuffer) any())).thenReturn(5);
        b.put(encoder.encode(CharBuffer.wrap(testStr)));
        Assert.assertNotEquals(0, b.remaining());
        c.read(channel, b);
        verify(channel).read(b);
        Assert.assertEquals(0, b.remaining());
        Assert.assertNotEquals(0, channel.read(b));
        verify(channel, times(2)).read(b);
    }
}

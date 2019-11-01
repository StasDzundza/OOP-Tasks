import network.SocketWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestSocketWriter {
    @Mock
    private SocketChannel channel;

    @InjectMocks
    private SocketWriter socketWriter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkWriting() throws IOException {
        String testMsg = "Test Message";
        when(channel.write((ByteBuffer) any())).thenReturn(testMsg.length());
        socketWriter.write(channel, testMsg);
        Assert.assertEquals(testMsg.length(), channel.write((ByteBuffer) any()));
        verify(channel, times(2)).write((ByteBuffer) any());
    }
}
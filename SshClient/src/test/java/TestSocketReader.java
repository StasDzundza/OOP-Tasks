import network.SocketReader;
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
public class TestSocketReader {
    @Mock
    private SocketChannel channel;

    @InjectMocks
    private SocketReader socketReader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkReading() throws IOException {
        when(channel.read((ByteBuffer) any())).thenReturn(10);
        String answer = socketReader.read(channel);
        Assert.assertNotEquals(0, channel.read((ByteBuffer) any()));
        Assert.assertEquals("Empty answer", answer);
        verify(channel, times(2)).read((ByteBuffer) any());
    }
}

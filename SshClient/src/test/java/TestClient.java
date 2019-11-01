import network.client.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TestClient {
    @Mock
    public SocketChannel channel;

    private final String testStr = "Test";

    public TestClient() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkAddress() {
        Client c = new Client("localhost", 22);
        InetSocketAddress addr = c.getActiveAdress();
        Assert.assertEquals("localhost", addr.getHostName());
        Assert.assertEquals(22, addr.getPort());
        Assert.assertEquals(1, 1);
    }

}

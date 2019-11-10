import network.Client;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;


public class ClientTest {
    @Test
    public void checkingInetAddress() throws IOException {
        Client client = new Client();
        Assert.assertEquals(1111,client.getLocalPort());
        Assert.assertEquals("127.0.0.1",client.getHostAddress());
    }
}

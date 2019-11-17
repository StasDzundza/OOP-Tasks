import network.Server;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {

    @Test
    public void checkingInetAddressTest() throws IOException {
        Server s = new Server();
        Assert.assertEquals(1111,s.getLocalPort());
    }
}

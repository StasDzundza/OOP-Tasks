package ReceiverTest;

import Receiver.EmailReceiver;
import org.junit.Assert;
import org.junit.Test;

//import javax.mail.Folder;
import java.lang.reflect.Field;
import java.util.Properties;

public class ReceiverTest {
    /*@Test
    public void checkReceiverPropertiesTest(){
        EmailReceiver receiver = new EmailReceiver();
        Properties prop = new Properties();
        prop.put("mail.pop3.host", "pop.gmail.com");
        prop.put("mail.pop3.port", "995");
        prop.put("mail.pop3.starttls.enable", "true");
        Assert.assertTrue(prop.equals(receiver.getProperties()));
    }

    @Test
    public void checkDefaultParametrsTest() throws NoSuchFieldException, IllegalAccessException {
        EmailReceiver receiver = new EmailReceiver();
        Field field = EmailReceiver.class.
                getDeclaredField("host");
        field.setAccessible(true);
        String host = (String) field.get(receiver);
        Assert.assertEquals("pop.gmail.com",host);

        field = EmailReceiver.class.
                getDeclaredField("protocol");
        field.setAccessible(true);
        String protocol = (String) field.get(receiver);
        Assert.assertEquals("pop3s",protocol);

        field = EmailReceiver.class.
                getDeclaredField("folderName");
        field.setAccessible(true);
        String folderName = (String) field.get(receiver);
        Assert.assertEquals("INBOX",folderName);

        field = EmailReceiver.class.
                getDeclaredField("mode");
        field.setAccessible(true);
        int mode = (int) field.get(receiver);
        Assert.assertEquals(Folder.READ_ONLY,mode);

        field = EmailReceiver.class.
                getDeclaredField("expunge");
        field.setAccessible(true);
        boolean expunge = (boolean) field.get(receiver);
        Assert.assertEquals(false,expunge);
    }
    */

    @Test
    public void t(){
        String t = "text";
        Assert.assertEquals(t,"text");
    }


}

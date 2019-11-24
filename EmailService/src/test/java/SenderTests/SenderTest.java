package SenderTests;

import Sender.EmailSender;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class SenderTest {
    @Test
    public void checkSenderPropertiesTest(){
        EmailSender sender = new EmailSender();
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Assert.assertTrue(prop.equals(sender.getSenderProperties()));
    }
}

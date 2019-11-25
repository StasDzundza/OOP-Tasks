package Receiver;

import javax.mail.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.*;
import java.util.logging.Logger;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Level;

public class EmailReceiver {
    private Properties properties;
    private final org.apache.logging.log4j.Logger logger = (org.apache.logging.log4j.Logger) Logger.getLogger(EmailReceiver.class.getName());
    private final String host = "pop.gmail.com";
    private final String protocol = "pop3s";
    private final String folderName = "INBOX";
    public final int mode = Folder.READ_ONLY;
    public final boolean expunge = false;

    public EmailReceiver(){
        properties = createDefaultProperties();
    }

    private Properties createDefaultProperties() {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        return properties;
    }

    public List getLetters(String username, String password, int numberOfLastMessages)  {
        List<Message> messages = new ArrayList<>();
        try {
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore(protocol);
            store.connect(host, username, password);
            Folder emailFolder = store.getFolder(folderName);
            emailFolder.open(mode);

            Message[] m = emailFolder.getMessages();
            for (int i = 0; i < numberOfLastMessages; i++) {
                messages.add(m[i]);
            }

            emailFolder.close(expunge);
            store.close();
        }catch (MessagingException e){
            logger.log(Level.ERROR,e.getMessage());
        }
        return messages;
    }

    public Properties getProperties() {
        return properties;
    }
}

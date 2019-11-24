package Sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailSender {
    Properties properties;
    private String username = "empty";
    private String password = "empty";
    private Session session;
    private final Logger logger = Logger.getLogger(EmailSender.class.getName());

    public EmailSender(){
        properties = createDefaultProperties();
    }

    public EmailSender(Properties properties){
        this.properties = properties;
    }

    private Properties createDefaultProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        return prop;
    }

    public void setAuthenticationData(String username,String password){
        this.username = username;
        this.password = password;
    }

    private Session getSession(){
        return Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public void sendMessage(String receiverEmailAddress, String subject,String text) {
        session = getSession();
        try {
            Message message = generateMessage(receiverEmailAddress,subject,text);
            Transport.send(message);
        }catch (MessagingException e){
            logger.info(e.getMessage());
        }
    }

    private Message generateMessage(String receiverEmailAddress, String subject,String text) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(receiverEmailAddress)
        );
        message.setSubject(subject);
        message.setText(text);
        return message;
    }

    public Properties getSenderProperties(){
        return properties;
    }
}

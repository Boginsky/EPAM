package by.boginsky.audiostore.util;

import by.boginsky.audiostore.exception.PropertyReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();

    public static final String MAIL_PROPERTY_PATH = "mail.properties";
    public static final String USER_KEY = "mail.user.email";
    public static final String PASSWORD_KEY = "mail.user.password";
    public static final String MAIL_TITLE = "Verification code";

    public boolean sendEmail(String emailTo, String content) throws PropertyReaderException {
        boolean result;
        PropertyReader reader = new PropertyReader();
        Properties properties;
        properties = reader.read(MAIL_PROPERTY_PATH);
        String user = properties.getProperty(USER_KEY);
        String password = properties.getProperty(PASSWORD_KEY);
        Session session = createSession(properties, user, password);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(MAIL_TITLE);
            message.setText(content);
            Transport.send(message);
            result = true;
        } catch (MessagingException e) {
            logger.error("Error during email sending: ", e);
            result = false;
        }
        return result;
    }

    private Session createSession(Properties properties, String user, String password) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        return session;
    }
}

package kc.logix;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SimpleSMTP1 {

    public static void main(String[] args) {

        final String username = "system@kclogix.com";
        final String password = "qwer1234!";
        String recipient = "jis400@daum.net";
        String subject = "Hello from Java SMTP";
        String body = "This is a sample email sent from a Java program using SMTP.";
        String host = "smtp.sg.aliyun.com";
        int port = 465;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true"); // Use STARTTLS for security
        props.put("mail.smtp.auth.user", username);
        props.put("mail.smtp.auth.password", password);
        try {
        	Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("auth.user");
                    String password = props.getProperty("auth.password");
                    return new PasswordAuthentication(userName, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@kclogix.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport();
            transport.connect(host, port, username, password);
            Transport.send(message);
            transport.close();
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace(); //Print the stack trace for more detailed error information
        }
    }
}
package kc.logix;

import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SampleMail {
    public static void main(String[] args) {
        try{
            // Set the SSL connection and email environment.
//            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            Properties props = System.getProperties();
            // The protocol.
            //props.setProperty("mail.transport.protocol", "smtp");

            props.setProperty("mail.smtp.host", "smtp.sg.aliyun.com");// The SMTP server.
             props.setProperty("mail.smtp.port", "25");// The non-encrypted port.
            // Configure the SSL encryption method as follows:
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            props.setProperty("mail.smtp.auth", "true");// Indicates that SMTP sends emails and authentication is required.
            props.setProperty("mail.smtp.from", "postmaster@kclogix.com");// The mailfrom parameter.
            props.setProperty("mail.user","postmaster@kclogix.com");// The account of the sender.
            props.setProperty("mail.password","kclogix0407!");// The password of the account of the sender. If the third-party client security password is enabled, use the new password.
            // Establish an email session.
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                // Identity authentication
                protected PasswordAuthentication getPasswordAuthentication() {
                    // The account and password of the sender.
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            });
            // Create an email object.
            MimeMessage message = new MimeMessage(session);
            // Specify the sender of the email.
            InternetAddress from=new InternetAddress("postmaster@kclogix.com","Test"); //from parameter, which can realize substitute sending. Note: substitute sending is easy to be rejected by the recipient or enter the garbage bin. 
            message.setFrom(from);
            // Set the recipients of the email.
            String[] to = {"is.jung@hmm21.com","jis400@daum.net"};// The list of recipients.
            InternetAddress[] sendTo = new InternetAddress[to.length];
            for (int i=0;i<to.length;i++){
                // System.out.println("Send to:" + to[i]);
                sendTo[i] = new InternetAddress(to[i]);
            }

            // Specify the recipient.
            message.setRecipients(Message.RecipientType.TO,sendTo);
            // Set the subject of the email.
            message.setSubject("Email Subject");
            // Set the text of the email.
            String content="body";
            message.setContent(content,"text/html;charset=UTF-8");
            // Specify the time.
            message.setSentDate(new Date());
            message.saveChanges();
            // Send an email.
            Transport.send(message);
            System.out.println("Sent successfully! ");
        }catch(Exception e){
            System.out.println("Exception:"+ e.toString());
        }
    }
}
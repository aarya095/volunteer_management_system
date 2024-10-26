package main;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class TestingEmailConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String to = "aaryasarfare28@gmail.com"; // recipient's email
        String from = "aaryasarfare-inft@atharvacoe.ac.in"; // your email
        String password = "108*GanaPati!"; // your app password

        // Set properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test Subject");
            message.setText("Test Email Content");

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
		
	}

}

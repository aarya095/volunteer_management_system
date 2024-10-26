package authentication;

import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class OTPService {
	
	private int otp;
	
	 public int generateOTP() {
	        Random random = new Random();
	        otp = random.nextInt(999999); 
	        return otp;
	    }
	 
	 public void sendOTP(String recipientEmail) {
	        // Set up the mail server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");

	        // Create a session with an authenticator
	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("aaryasarfare-inft@atharvacoe.ac.in", "108*GanaPati!");
	            }
	        });

	        try {
	            // Create the email message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("aaryasarfare-inft@atharvacoe.ac.in"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	            message.setSubject("Your OTP Code");
	            message.setText("Your OTP code is: " + otp);

	            // Send the message
	            Transport.send(message);
	            System.out.println("OTP sent successfully!");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public boolean verifyOTP(int enteredOTP) {
	        return otp == enteredOTP; // Compare the generated OTP with the entered OTP
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

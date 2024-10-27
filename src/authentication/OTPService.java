package authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import database.conn; // Import your conn class

public class OTPService {

    private int otp;

    public int generateOTP() {
        Random random = new Random();
        otp = random.nextInt(999999); 
        return otp;
    }

    public void sendOTP(String recipientEmail) {
        String[] emailCredentials = getEmailCredentials();

        if (emailCredentials == null || emailCredentials.length < 2) {
            System.out.println("Error retrieving email credentials from the database.");
            return; // Exit if credentials can't be retrieved
        }

        String email = emailCredentials[0];
        String password = emailCredentials[1];

        // Set up the mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
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

    private String[] getEmailCredentials() {
        String[] credentials = new String[2]; // Array to hold email and password
        conn connection = new conn(); // Create an instance of your conn class
        Statement statement = null;

        try {
            // Create a statement object from the conn class
            statement = connection.s;

            // Execute a query to retrieve the email and password
            String query = "SELECT email, emailpassword FROM lgin WHERE Id=1"; // Adjust the query as needed
            ResultSet rs = statement.executeQuery(query);

            // Assuming there's only one row, retrieve email and password
            if (rs.next()) {
                credentials[0] = rs.getString("email");    // Get email
                credentials[1] = rs.getString("emailpassword"); // Get password
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        } finally {
            // Close resources - you may choose to close the statement only if you're not reusing the conn instance
            try {
                if (statement != null) statement.close();
                // No need to close connection as conn instance is reused
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return credentials; // Return the email and password
    }

    public static void main(String[] args) {
        // You can test the OTP service here
        OTPService otpService = new OTPService();
        otpService.generateOTP();
        otpService.sendOTP("aaryasarfare-inft@atharvacoe.ac.in"); // Change to a valid recipient
    }
}

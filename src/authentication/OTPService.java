package authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import database.conn;

public class OTPService {
    private int otp;
    private static OTPService instance;

    // Singleton pattern to ensure only one instance
    public static OTPService getInstance() {
        if (instance == null) {
            instance = new OTPService();
        }
        return instance;
    }

    // Generate and return OTP
    public int generateOTP() {
        Random random = new Random(); //for random number generation
        otp = 100000 + random.nextInt(900000); // Ensure OTP is 6 digits
        return otp;
    }

    // Send OTP to the recipient's email
    public void sendOTP(String recipientEmail) {
        String[] emailCredentials = getEmailCredentials(); 

        if (emailCredentials == null || emailCredentials.length < 2) {
            System.out.println("Error retrieving email credentials from the database.");
            return; // Exit if credentials can't be retrieved
        }

        String email = emailCredentials[0];
        String password = emailCredentials[1];

        // Set up mail server properties
        Properties properties = new Properties(); //SMTP: Simple Mail Transfer Protocol | TLS:Transport Layer Security
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); //gmail part 

        // Create session with authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            // Create email message
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

    // Verify OTP entered by the user
    public boolean verifyOTP(int enteredOTP) {
        return otp == enteredOTP; 
    }

    // Retrieve email credentials from the database
    private String[] getEmailCredentials() {
        String[] credentials = new String[2]; // Array to hold email and password
        conn connection = new conn(); 
        Statement statement = null;

        try {
            // Create a statement object from the conn class
            statement = connection.s;

            // Execute a query to retrieve the email and password
            String query = "SELECT email, emailpassword FROM login WHERE Id=1"; // Retrieves email and password from MySQL
            ResultSet rs = statement.executeQuery(query);

           
            if (rs.next()) {
                credentials[0] = rs.getString("email");    // Get email
                credentials[1] = rs.getString("emailpassword"); // Get password
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        } finally {
            // No need to close connection as conn instance is reused
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return credentials; // Return the email and password
    }
}

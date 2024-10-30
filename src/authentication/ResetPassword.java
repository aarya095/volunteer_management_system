package authentication;

import javax.swing.*;
import database.conn;
import main.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class ResetPassword extends JFrame implements ActionListener {

    JTextField otpField, newPasswordField, newUsernameField;
    JButton resetButton;
    int validOtp;
    String userEmail;
    OTPService otpService = OTPService.getInstance(); // Use singleton instance of OTPService

    public ResetPassword(int otp, String email) {
        this.validOtp = otp;
        this.userEmail = email;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel otpLabel = new JLabel("Enter OTP:");
        otpLabel.setBounds(50, 30, 120, 30);
        add(otpLabel);

        otpField = new JTextField();
        otpField.setBounds(180, 30, 150, 30);
        add(otpField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(50, 80, 120, 30);
        add(newPasswordLabel);

        newPasswordField = new JTextField();
        newPasswordField.setBounds(180, 80, 150, 30);
        add(newPasswordField);

        JLabel newUsernameLabel = new JLabel("New Username:");
        newUsernameLabel.setBounds(50, 130, 120, 30);
        add(newUsernameLabel);

        newUsernameField = new JTextField();
        newUsernameField.setBounds(180, 130, 150, 30);
        add(newUsernameField);

        resetButton = new JButton("Reset Password and Username");
        resetButton.setBounds(60, 200, 250, 30);
        resetButton.addActionListener(this);
        add(resetButton);

        setSize(400, 300);
        setLocation(550, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the window is closed
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            int enteredOtp = Integer.parseInt(otpField.getText());

            // Check if entered OTP matches the valid OTP
            if (enteredOtp == validOtp) {
                String newPassword = newPasswordField.getText();
                String newUsername = newUsernameField.getText();

                // Validate inputs
                if (newPassword.isEmpty() || newUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
                    return;
                }

                // Update the database
                conn dbConnection = new conn(); // Create a new connection
                Connection connection = dbConnection.c; // Get the connection object

                String query = "UPDATE login SET password = ?, username = ? WHERE email = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, newPassword);  // // Replace first "?" with "newPassword"
                pstmt.setString(2, newUsername);  // Replace second "?" with "newUsername"
                pstmt.setString(3, userEmail);    // Set email condition

                //have not yet applied condition for not entering the same old password again
                
                int rowsAffected = pstmt.executeUpdate(); //executeUpdate by default returns the number of rows it has modified

                // Check if the update was successful
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Username and password reset successfully!");
                    setVisible(false); // Close the current window
                    new Login();  // Redirect to login screen
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating username or password. Try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid OTP! Please try again.");
            }
        } catch (NumberFormatException e) { /*Thrown to indicate that the application has attempted to convert a string to one of the numeric types, but that the string does not have the appropriate format.*/
            JOptionPane.showMessageDialog(null, "Please enter a valid OTP.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ResetPassword(123456, "test@example.com"); // For testing, change as necessary
    }
}

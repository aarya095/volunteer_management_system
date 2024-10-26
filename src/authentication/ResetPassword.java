package authentication;

import javax.swing.*;
import database.conn;
import main.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class ResetPassword extends JFrame implements ActionListener{

	JTextField otpField, newPasswordField;
    JButton resetButton;
    int validOtp;
    String userEmail;
    
    public ResetPassword(int otp, String email) {
    	
    	 this.validOtp = otp;
         this.userEmail = email;

         getContentPane().setBackground(Color.WHITE);
         setLayout(null);

         JLabel otpLabel = new JLabel("Enter OTP:");
         otpLabel.setBounds(30, 50, 100, 30);
         add(otpLabel);

         otpField = new JTextField();
         otpField.setBounds(150, 50, 150, 30);
         add(otpField);

         JLabel newPasswordLabel = new JLabel("New Password:");
         newPasswordLabel.setBounds(30, 100, 120, 30);
         add(newPasswordLabel);

         newPasswordField = new JTextField();
         newPasswordField.setBounds(150, 100, 150, 30);
         add(newPasswordField);

         resetButton = new JButton("Reset Password");
         resetButton.setBounds(100, 170, 150, 30);
         resetButton.addActionListener(this);
         add(resetButton);

         setSize(400, 300);
         setLocation(500, 200);
         setVisible(true);
    	
    }
    
    public void actionPerformed(ActionEvent ae) {
    	 try {
             int enteredOtp = Integer.parseInt(otpField.getText());

             if (enteredOtp == validOtp) {
                 String newPassword = newPasswordField.getText();

                 // Validate that the new password is not empty
                 if (newPassword.isEmpty()) {
                     JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                     return;
                 }

                 // Update the password in the database
                 conn dbConnection = new conn();
                 Connection connection = dbConnection.c;
                 
                 String query = "update login SET password = ? where email = ?";
                 PreparedStatement pstmt = connection.prepareStatement(query);
                 pstmt.setString(1, newPassword);  // Set new password
                 pstmt.setString(2, userEmail);    // Set email condition

                 int rowsAffected = pstmt.executeUpdate();

                 if (rowsAffected > 0) {
                     JOptionPane.showMessageDialog(null, "Password reset successfully!");
                     setVisible(false);
                     new Login();  // Redirect to login screen
                 } else {
                     JOptionPane.showMessageDialog(null, "Error updating password. Try again.");
                 }

             } else {
                 JOptionPane.showMessageDialog(null, "Invalid OTP! Please try again.");
             }
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(null, "Please enter a valid OTP.");
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ResetPassword(123456, "test@example.com");
	}

}

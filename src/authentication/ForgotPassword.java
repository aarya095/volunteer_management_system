package authentication;

import javax.swing.*;
import database.conn;
import main.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPassword extends JFrame implements ActionListener {

    JTextField tfemail;
    JButton sendOtp;
    OTPService otpService = new OTPService(); // Create OTPService instance

    public ForgotPassword() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel enteremail = new JLabel("Enter Your Registered Email: ");
        enteremail.setBounds(30, 50, 220, 30);
        enteremail.setFont(new Font("Raieway", Font.BOLD, 15));
        add(enteremail);

        tfemail = new JTextField();
        tfemail.setBounds(260, 50, 300, 30);
        add(tfemail);

        sendOtp = new JButton("Send OTP");
        sendOtp.setBounds(200, 170, 150, 30);
        sendOtp.addActionListener(this);
        add(sendOtp);

        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == sendOtp) {
                String email = tfemail.getText();

                conn c = new conn();  // Get the connection object
                Connection connection = c.c;  // Use the connection directly

                String query = "select * from login where email = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, email);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int otp = otpService.generateOTP();  // Generate OTP
                    otpService.sendOTP(email);  // Send OTP to email

                    JOptionPane.showMessageDialog(null, "OTP sent to your email. Please check your inbox.");
                    setVisible(false);
                    new ResetPassword(otp, email);  // Open ResetPassword window
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email! Please enter the correct email address.");
                    setVisible(false);
                    new Login();  // Redirect to Login
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ForgotPassword();
    }
}
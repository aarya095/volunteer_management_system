package authentication;

import javax.swing.*;
import database.conn;
import main.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class ForgotPassword extends JFrame implements ActionListener {

    JTextField tfemail;
    JButton sendOtp;
    OTPService otpService = OTPService.getInstance(); // Use singleton instance of OTPService

    public ForgotPassword() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel enteremail = new JLabel("Enter Your Registered Email: ");
        enteremail.setBounds(40, 50, 220, 30);
        enteremail.setFont(new Font("Raieway", Font.BOLD, 15));
        add(enteremail);

        tfemail = new JTextField();
        tfemail.setBounds(15, 100, 280, 30);
        add(tfemail);

        sendOtp = new JButton("Send OTP");
        sendOtp.setBounds(70, 170, 150, 30);
        sendOtp.addActionListener(this);
        add(sendOtp);

        setSize(320, 300);
        setLocation(600, 270);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == sendOtp) {
                String email = tfemail.getText();

                conn c = new conn();  // Get the connection object
                Connection connection = c.c;  // Use the connection directly

                String query = "SELECT * FROM login WHERE email = ?";
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

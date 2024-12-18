package main;

import java.awt.*;
import javax.swing.*;

import EventManagement.Event;
import VolunteerManagement.volunteerManagement;
import authentication.ForgotPassword;
import database.conn;

import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener { // Login inherits JFrame and implements ActionListner interface

JTextField tfusername;
JButton login, forgotPassword;
JPasswordField pfpassword; 
	
	 public Login(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(40,50,100,30);
		add(lblusername);
		
		tfusername = new JTextField();
		tfusername.setBounds(150,50,150,30);
		add(tfusername);
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(40,100,100,30);
		add(lblpassword);
		
		pfpassword = new JPasswordField();
		pfpassword.setBounds(150,100,150,30);
		add(pfpassword);
		
		login = new JButton("LOGIN");
		login.setBounds(150,170,150,30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.white);
		login.addActionListener(this); //registers login button as a event source for actionListenser
		add(login);
		
		forgotPassword = new JButton("Forgot Password/Username");
		forgotPassword.setBounds(125,210,200,32);
		forgotPassword.setBackground(Color.BLACK);
		forgotPassword.setForeground(Color.white);
		forgotPassword.addActionListener(this); //registers forgotPassword button as a event source for actionListenser
		add(forgotPassword);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/second.jpg"));
		Image i2 = i1.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(350,20,200,200);
		add(image);
		
		setSize(600,300);
		setLocation(480,220);
		setVisible(true);
}

		public void actionPerformed(ActionEvent ae) { //ActionListener interface is overriden here
			try {
				
				if (ae.getSource() == login) {
				
				String username = tfusername.getText();
				char[] passwordArray = pfpassword.getPassword(); //can't use getText since pfpassword is declared as JPasswordField
	            String password = new String(passwordArray); //converts char into string
				
				conn c = new conn();
				String query = "select * from login where username = '"+username+"' and password ='"+password+"' "; //SQL injection can occur not safe
				
				ResultSet rs = c.s.executeQuery(query); //must use prepared statement
				
				
				if(rs.next()) {
					setVisible(false);
					new Home();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!");
					setVisible(false);
					new Start();
				} 
			}
			
			
			else if (ae.getSource() == forgotPassword) {
	            setVisible(false); 
	            new ForgotPassword(); 
	        }
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	public static void main(String[] args) { //start point
		// TODO Auto-generated method stub
		new Login(); //object creation
	}

}

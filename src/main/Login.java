package main;

import java.awt.*;
import javax.swing.*;

import database.conn;

import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

JTextField tfusername, tfpassword;
	
	Login(){
		
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
		
		tfpassword = new JTextField();
		tfpassword.setBounds(150,100,150,30);
		add(tfpassword);
		
		JButton login = new JButton("LOGIN");
		login.setBounds(150,170,150,30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.white);
		login.addActionListener(this);
		add(login);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/second.jpg"));
		Image i2 = i1.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(350,20,200,200);
		add(image);
		
		setSize(600,300);
		setLocation(450,200);
		setVisible(true);
}

		public void actionPerformed(ActionEvent ae) {
			try {
				String username = tfusername.getText();
				String password = tfpassword.getText();
				
				conn c = new conn();
				String query = "select * from login where username = '"+username+"' and password ='"+password+"' ";
				
				ResultSet rs = c.s.executeQuery(query);
				if(rs.next()) {
					setVisible(false);
					new Home();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!");
					setVisible(false);
					new Start();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}

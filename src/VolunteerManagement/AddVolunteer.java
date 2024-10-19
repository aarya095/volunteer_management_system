package VolunteerManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import volunteer_management_system.Home;
import volunteer_management_system.conn;

import java.sql.*;
import java.util.*;

public class AddVolunteer extends JFrame implements ActionListener {
	
	Random ran = new Random();
	int number = ran.nextInt(999999);
	
	JTextField tfname,tffname,tfphone,tfemail,tfaadhar,tfdob,tfaddress,tfdesignation;
	JComboBox<String> cbeducation;
	JButton add,back;
	JLabel lblvolunteerId;
	
	AddVolunteer(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading = new JLabel("Add Volunteer Details");
		heading.setBounds(320,30,500,50);
		heading.setFont(new Font("SAN_SERIF",Font.BOLD, 25));
		add(heading);
		
		JLabel labelname = new JLabel("Name: ");
		labelname.setBounds(50,150,150,30);
		labelname.setFont(new Font("serif", Font.PLAIN,20));
		add(labelname);
		
		tfname = new JTextField();
		tfname.setBounds(200,150,150,30);
		add(tfname);
		
		JLabel labelfname = new JLabel("Father's Name: ");
		labelfname.setBounds(400,150,150,30);
		labelfname.setFont(new Font("serif", Font.PLAIN,20));
		add(labelfname);
		
		tffname = new JTextField();
		tffname.setBounds(600,150,150,30);
		add(tffname);
		
		JLabel labeldob = new JLabel("Date Of Birth: ");
		labeldob.setBounds(50,200,150,30);
		labeldob.setFont(new Font("serif", Font.PLAIN,20));
		add(labeldob);
		
		tfdob = new JTextField();
		tfdob.setBounds(200,200,150,30);
		add(tfdob);
		
		JLabel labeladdress = new JLabel("Address: ");
		labeladdress.setBounds(50,250,150,30);
		labeladdress.setFont(new Font("serif", Font.PLAIN,20));
		add(labeladdress);
		
		tfaddress = new JTextField();
		tfaddress.setBounds(200,250,150,30);
		add(tfaddress);
		
		JLabel labelphone = new JLabel("Phone: ");
		labelphone.setBounds(400,200,150,30);
		labelphone.setFont(new Font("serif", Font.PLAIN,20));
		add(labelphone);
		
		tfphone = new JTextField();
		tfphone.setBounds(600,200,150,30);
		add(tfphone);
		
		JLabel labelemail = new JLabel("Email: ");
		labelemail.setBounds(50,300,150,30);
		labelemail.setFont(new Font("serif", Font.PLAIN,20));
		add(labelemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(200,300,150,30);
		add(tfemail);
		
		JLabel labeleducation = new JLabel("Highest Education: ");
		labeleducation.setBounds(400,250,200,30);
		labeleducation.setFont(new Font("serif", Font.PLAIN,20));
		add(labeleducation);
		
		String courses[]= {"BBA","BCA","BA","BSc","B.Com","B.Tech","MBA","M.A","M.Tech","Phd"};
		cbeducation = new JComboBox<>(courses);
		cbeducation.setBackground(Color.WHITE);
		cbeducation.setForeground(Color.BLACK);
		cbeducation.setBounds(600,250,150,30);
		add(cbeducation);
		
		JLabel labeldesignation = new JLabel("Designation: ");
		labeldesignation.setBounds(50,350,150,30);
		labeldesignation.setFont(new Font("serif", Font.PLAIN,20));
		add(labeldesignation);
		
		tfdesignation = new JTextField();
		tfdesignation.setBounds(200,350,150,30);
		add(tfdesignation);
		
		JLabel labelaadhar = new JLabel("Aadhar Number: ");
		labelaadhar.setBounds(400,300,150,30);
		labelaadhar.setFont(new Font("serif", Font.PLAIN,20));
		add(labelaadhar);
		
		tfaadhar = new JTextField();
		tfaadhar.setBounds(600,300,150,30);
		add(tfaadhar);
		
		JLabel labelvolunteerId = new JLabel("Volunteer ID: ");
		labelvolunteerId.setBounds(400,350,150,30);
		labelvolunteerId.setFont(new Font("serif", Font.PLAIN,20));
		add(labelvolunteerId);

		lblvolunteerId = new JLabel("" +number);
		lblvolunteerId.setBounds(600,350,150,30);
		lblvolunteerId.setFont(new Font("serif", Font.PLAIN,20));
		add(lblvolunteerId);
		
		add = new JButton("Add Details");
		add.setBounds(250,550,150,40);
		add.addActionListener(this);
		add.setBackground(Color.BLACK);
		add.setForeground(Color.WHITE);
		add(add);
	
		back = new JButton("Back");
		back.setBounds(450,550,150,40);
		back.addActionListener(this);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		add(back);

		setSize(900,700);
		setLocation(300,50);
		setVisible(true);
	
}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == add) {
			String name = tfname.getText();
			String fname = tffname.getText();
			String dob = tfdob.getText();
			String address = tfaddress.getText();
			String phone = tfphone.getText();
			String email = tfemail.getText();
			String education = (String) cbeducation.getSelectedItem();
			String designation = tfdesignation.getText();
			String aadhar = tfaadhar.getText();
			String volunteerId = lblvolunteerId.getText();
			
			try {
				conn conn = new conn();                         
				String query = "insert into volunteer values('"+name+"', '"+fname+"','"+dob+"','"+address+"','"+phone+"','"+email+"','"+education+"','"+designation+"','"+aadhar+"','"+volunteerId+"')";
			    conn.s.executeUpdate(query);
			    JOptionPane.showMessageDialog(null, "Details added Successfully");
			    setVisible(false);
			    new Home();
			    
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (ae.getSource() == back){
			setVisible(false);
			new Home();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddVolunteer();
	}

}

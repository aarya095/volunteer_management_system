package main;

import java.awt.Image;
import javax.swing.*;

import EventManagement.Event;
import VolunteerManagement.AddVolunteer;
import VolunteerManagement.RemoveVolunteer;
import VolunteerManagement.ViewVolunteer;
import VolunteerManagement.volunteerManagement;

import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

JButton VolunteerManagement,event,logout;
	
	public Home(){
		
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/home.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1120,630, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,1120,630);
		add(image);
		
		JLabel heading = new JLabel("Volunteer Management System");
		heading.setBounds(600,20,500,40);
		heading.setFont(new Font( "Raieway", Font.BOLD, 30));
		image.add(heading);
		
		VolunteerManagement = new JButton("Volunteer Management");
		VolunteerManagement.setBounds(700,100,230,50);
		VolunteerManagement.addActionListener(this);
		image.add(VolunteerManagement);
		
		event = new JButton("Event Management");
		event.setBounds(700,200,230,50);
		event.addActionListener(this);
		image.add(event);
		
		logout = new JButton("Log Out");
		logout.setBounds(940,520,150,40);
		logout.addActionListener(this);
		image.add(logout);
		
		setSize(1120,630);
		setLocation(250,100);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == VolunteerManagement) {
			setVisible(false);
			new volunteerManagement();
		}
		 else if (ae.getSource()== event){
			setVisible(false);
			new Event();
		} else if (ae.getSource()== logout){
			setVisible(false);
			new Start();
		}
		
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Home();
	}

}

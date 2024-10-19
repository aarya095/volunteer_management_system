package volunteer_management_system;

import java.awt.Image;
import javax.swing.*;

import EventManagement.Event;
import VolunteerManagement.AddVolunteer;
import VolunteerManagement.RemoveVolunteer;
import VolunteerManagement.ViewVolunteer;

import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

JButton add,view,update,remove,event,logout;
	
	Home(){
		
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/home.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1120,630, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0,0,1120,630);
		add(image);
		
		JLabel heading = new JLabel("Volunteer Management System");
		heading.setBounds(620,20,400,40);
		heading.setFont(new Font( "Raieway", Font.BOLD, 25));
		image.add(heading);
		
		add = new JButton("Add Volunteer");
		add.setBounds(650,80,150,40);
		add.addActionListener(this);
		image.add(add);
		
		view = new JButton("View Volunteers");
		view.setBounds(820,80,150,40);
		view.addActionListener(this);
		image.add(view);
		
		update = new JButton("Update Volunteer");
		update.setBounds(650,140,150,40);
		update.addActionListener(this);
		image.add(update);
		
		remove = new JButton("Remove Volunteer");
		remove.setBounds(820,140,150,40);
		remove.addActionListener(this);
		image.add(remove);
		
		event = new JButton("Event Management");
		event.setBounds(740,200,150,40);
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
		
		if(ae.getSource() == add) {
			setVisible(false);
			new AddVolunteer();
		}else if(ae.getSource()==view) {
			setVisible(false);
			new ViewVolunteer();
		}else if (ae.getSource()==update) {
			setVisible(false);
			new ViewVolunteer();
		}else if (ae.getSource()== remove){
			setVisible(false);
			new RemoveVolunteer();
		} else if (ae.getSource()== event){
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

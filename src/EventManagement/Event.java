package EventManagement;

import javax.swing.*;

import volunteer_management_system.Home;

import java.awt.*;
import java.awt.event.*;

public class Event extends JFrame implements ActionListener {

	JButton CreateEvent, AssignVolunteer, ViewAssignments, RemoveEvent, back ;

	Event() {
		
		setLayout(null);
		
		JLabel heading = new JLabel("EVENT MANAGEMENT");
		heading.setBounds(260,20,400,40);
		heading.setFont(new Font( "Raieway", Font.BOLD, 25));
		add(heading);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/print.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 00, 1050, 500);
        add(image);
		
		CreateEvent = new JButton("New Event");
		CreateEvent.setBounds(120,150,150,40);
		CreateEvent.addActionListener(this);
		image.add(CreateEvent);
		
		AssignVolunteer = new JButton("Assign Volunteers to an Event");
		AssignVolunteer.setBounds(370,150,300,40);
		AssignVolunteer.addActionListener(this);
		image.add(AssignVolunteer);
		
		ViewAssignments = new JButton("View Assignments");
		ViewAssignments.setBounds(450,250,150,40);
		ViewAssignments.addActionListener(this);
		image.add(ViewAssignments);
		
		RemoveEvent = new JButton("Remove Event");
		RemoveEvent.setBounds(120,250,150,40);
		RemoveEvent.addActionListener(this);
		image.add(RemoveEvent);
		
		back = new JButton("Back");
		back.setBounds(320, 340, 120, 40);
		back.addActionListener(this);
		image.add(back);
		
		setSize(800,430);
		setLocation(250,100);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == CreateEvent) {
			setVisible(false);
			new CreateEvent();
		} else if(ae.getSource() == AssignVolunteer) {
			setVisible(false);
			new AssignVolunteer();
		} 
		else if(ae.getSource() == ViewAssignments) {
			setVisible(false);
			new ViewAssignments();
		}  
		else if(ae.getSource() == RemoveEvent) {
			setVisible(false);
			new RemoveEvent();
		} 
		else if  (ae.getSource() == back) {
			setVisible(false);
			new Home();
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Event();
	}

}

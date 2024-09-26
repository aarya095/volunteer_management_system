package volunteer_management_system;

import javax.swing.*;
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
		
		CreateEvent = new JButton("New Event");
		CreateEvent.setBounds(120,150,150,40);
		CreateEvent.addActionListener(this);
		add(CreateEvent);
		
		AssignVolunteer = new JButton("Assign Volunteers to an Event");
		AssignVolunteer.setBounds(370,150,300,40);
		AssignVolunteer.addActionListener(this);
		add(AssignVolunteer);
		
		ViewAssignments = new JButton("View Assignments");
		ViewAssignments.setBounds(450,250,150,40);
		ViewAssignments.addActionListener(this);
		add(ViewAssignments);
		
		RemoveEvent = new JButton("Remove Event");
		RemoveEvent.setBounds(120,250,150,40);
		RemoveEvent.addActionListener(this);
		add(RemoveEvent);
		
		back = new JButton("Back");
		back.setBounds(320, 340, 120, 40);
		back.addActionListener(this);
		add(back);
		
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

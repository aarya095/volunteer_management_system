package VolunteerManagement;

import javax.swing.*;

import EventManagement.Event;
import main.Home;
import main.Start;

import java.awt.*;
import java.awt.event.*;

public class volunteerManagement extends JFrame implements ActionListener{
	
	JButton add,view,update,remove,back;
	
	public volunteerManagement() {
		
		setLayout(null);
		
		JLabel heading = new JLabel("VOLUNTEER MANAGEMENT");
		heading.setBounds(230,20,400,40);
		heading.setFont(new Font( "Raieway", Font.BOLD, 25));
		add(heading);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/print.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 00, 1050, 500);
        add(image);
        
        add = new JButton("Add Volunteer");
		add.setBounds(140,130,150,40);
		add.addActionListener(this);
		image.add(add);
		
		view = new JButton("View Volunteers");
		view.setBounds(470,130,150,40);
		view.addActionListener(this);
		image.add(view);
		
		update = new JButton("Update Volunteer");
		update.setBounds(470,230,150,40);
		update.addActionListener(this);
		image.add(update);
		
		remove = new JButton("Remove Volunteer");
		remove.setBounds(140,230,150,40);
		remove.addActionListener(this);
		image.add(remove);
		
		back = new JButton("Back");
		back.setBounds(320, 320, 120, 40);
		back.addActionListener(this);
		image.add(back);
		
		setSize(800,430);
		setLocation(400,180);
		setVisible(true);
		
	}
	
public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == add) {
			setVisible(false);
			new AddVolunteer();
		}else if(ae.getSource() == view) {
			setVisible(false);
			new ViewVolunteer();
		}else if (ae.getSource() == update) {
			setVisible(false);
			new ViewVolunteer();
		}else if (ae.getSource() == remove){
			setVisible(false);
			new RemoveVolunteer();
		}else if  (ae.getSource() == back) {
			setVisible(false);
			new Home(); 
		}
		
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new volunteerManagement();
	}

}

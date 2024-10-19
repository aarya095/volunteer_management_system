package EventManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.conn;

import java.sql.*;
import java.util.*;

public class CreateEvent extends JFrame implements ActionListener{
	
	Random ran = new Random();
	int number = ran.nextInt(999999);
	
	JTextField tfEventName, tfEventDate, tflocation;
	JButton add,back;
	JLabel lblEventId;
	
	CreateEvent(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading = new JLabel("Add Event Details");
		heading.setBounds(320,30,500,50);
		heading.setFont(new Font("SAN_SERIF",Font.BOLD, 25));
		add(heading);
		
		JLabel labelEventName = new JLabel("Event Name: ");
		labelEventName.setBounds(50,150,150,30);
		labelEventName.setFont(new Font("serif", Font.PLAIN,20));
		add(labelEventName);
		
		tfEventName = new JTextField();
		tfEventName.setBounds(200,150,150,30);
		add(tfEventName);
		
		JLabel labelEventDate = new JLabel("Event Date: ");
		labelEventDate.setBounds(500,150,150,30);
		labelEventDate.setFont(new Font("serif", Font.PLAIN,20));
		add(labelEventDate);
		
		tfEventDate = new JTextField();
		tfEventDate.setBounds(600,150,150,30);
		add(tfEventDate);
		
		JLabel labellocation = new JLabel("Location: ");
		labellocation.setBounds(50,200,150,30);
		labellocation.setFont(new Font("serif", Font.PLAIN,20));
		add(labellocation);
		
		tflocation = new JTextField();
		tflocation.setBounds(200,200,150,30);
		add(tflocation);
		
		JLabel labelEventId = new JLabel("Event ID: ");
		labelEventId.setBounds(500,200,150,30);
		labelEventId.setFont(new Font("serif", Font.PLAIN,20));
		add(labelEventId);

		lblEventId = new JLabel("" +number);
		lblEventId.setBounds(600,200,150,30);
		lblEventId.setFont(new Font("serif", Font.PLAIN,20));
		add(lblEventId);
		
		add = new JButton("Add Details");
		add.setBounds(250,400,150,40);
		add.addActionListener(this);
		add.setBackground(Color.BLACK);
		add.setForeground(Color.WHITE);
		add(add);
	
		back = new JButton("Back");
		back.setBounds(450,400,150,40);
		back.addActionListener(this);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		add(back);

		setSize(900,500);
		setLocation(300,50);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == add) {
			String eventId = lblEventId.getText();
			String eventName = tfEventName.getText();
			String eventDate = tfEventDate.getText();
			String location = tflocation.getText();
			
			
			try {
				conn conn = new conn();                         
				String query = "insert into event values('"+eventId+"','"+eventName+"', '"+eventDate+"','"+location+"')";
			    conn.s.executeUpdate(query);
			    JOptionPane.showMessageDialog(null, "Details added Successfully");
			    setVisible(false);
			    new Event();
			    
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (ae.getSource() == back){
			setVisible(false);
			new Event();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CreateEvent();
	}

}

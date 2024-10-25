package EventManagement;

import java.awt.*;
import javax.swing.*;

import database.conn;

import java.sql.*;
import java.awt.event.*;

public class RemoveEvent extends JFrame implements ActionListener {

	
	
	Choice cEventId;
	JButton delete, back;
	
	RemoveEvent(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel labelvolunteerId = new JLabel("Event Id");
		labelvolunteerId.setBounds(50, 50, 100, 30);
		add(labelvolunteerId);
		
		cEventId = new Choice();
		cEventId.setBounds(200, 50, 150, 30);
		add(cEventId);
		
		try {
			conn c =new conn();
			String query = "select * from event";
			ResultSet rs = c.s.executeQuery(query);
			while(rs.next()) {
				cEventId.add(rs.getString("eventId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel labeleventName = new JLabel("Event Name");
		labeleventName.setBounds(50, 100, 100, 30);
		add(labeleventName);
		
		JLabel lbleventName  = new JLabel();
		lbleventName.setBounds(200, 100, 100, 30);
		add(lbleventName);
		
		JLabel labeleventDate = new JLabel("Event Date");
		labeleventDate.setBounds(50, 150, 100, 30);
		add(labeleventDate);
		
		JLabel lbleventDate  = new JLabel();
		lbleventDate.setBounds(200, 150, 100, 30);
		add(lbleventDate);
		
		JLabel labellocation = new JLabel("Event Location");
		labellocation.setBounds(50, 200, 100, 30);
		add(labellocation);
		
		JLabel lbllocation  = new JLabel();
		lbllocation.setBounds(200, 200, 100, 30);
		add(lbllocation);
		
		try {
			conn c =new conn();
			String query = "select * from event where eventId ='"+cEventId.getSelectedItem()+"'";
			ResultSet rs = c.s.executeQuery(query);
			while(rs.next()) {
				lbleventName.setText(rs.getString("eventName"));
				lbleventDate.setText(rs.getString("eventDate"));
				lbllocation.setText(rs.getString("location"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cEventId.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
						try {
							conn c =new conn();
							String eventId = cEventId.getSelectedItem(); 
							String query = "select eventName, eventDate, location from event where eventId = ?";
							PreparedStatement ps = c.c.prepareStatement(query);
				            ps.setString(1, eventId);
				            ResultSet rs = ps.executeQuery();
				            if(rs.next()) {
								lbleventName.setText(rs.getString("eventName"));
								lbleventDate.setText(rs.getString("eventDate"));
								lbllocation.setText(rs.getString("location"));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
			}
		});
		
		
		
		delete = new JButton("Delete Event");
		delete.setBounds(50, 300, 150, 30);
		delete.setBackground(Color.BLACK);
		delete.setForeground(Color.WHITE);
		delete.addActionListener(this);
		add(delete);
		
		back = new JButton("Back");
		back.setBounds(210, 300, 100, 30);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.addActionListener(this);
		add(back);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 600, 400);
        add(image);
		
		setSize(1000,400);
		setLocation(300,150);
		setVisible(true);
		
	}
	
public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == delete) {
			try {
				conn c = new conn();
				String query = "delete from event where eventId = ?";
				PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, cEventId.getSelectedItem());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Event Information Deleted Successfully");
				setVisible(false);
				new Event();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if  (ae.getSource() == back) {
			setVisible(false);
			new Event();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RemoveEvent();
	}

}

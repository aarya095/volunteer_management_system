package VolunteerManagement;

import java.awt.*;
import javax.swing.*;

import main.Home;
import main.conn;

import java.sql.*;
import java.awt.event.*;

public class RemoveVolunteer extends JFrame implements ActionListener {
	
	Choice cVolunteerId;
	JButton delete, back;
	
	public RemoveVolunteer(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel labelvolunteerId = new JLabel("Volunteer Id");
		labelvolunteerId.setBounds(50, 50, 100, 30);
		add(labelvolunteerId);
		
		cVolunteerId = new Choice();
		cVolunteerId.setBounds(200, 50, 150, 30);
		add(cVolunteerId);
		
		try {
			conn c =new conn();
			String query = "select * from volunteer";
			ResultSet rs = c.s.executeQuery(query);
			while(rs.next()) {
				cVolunteerId.add(rs.getString("volunteerId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel labelname = new JLabel("Name");
		labelname.setBounds(50, 100, 100, 30);
		add(labelname);
		
		JLabel lblname  = new JLabel();
		lblname.setBounds(200, 100, 100, 30);
		add(lblname);
		
		JLabel labelphone = new JLabel("Phone");
		labelphone.setBounds(50, 150, 100, 30);
		add(labelphone);
		
		JLabel lblphone  = new JLabel();
		lblphone.setBounds(200, 150, 100, 30);
		add(lblphone);
		
		JLabel labelemail = new JLabel("Email");
		labelemail.setBounds(50, 200, 100, 30);
		add(labelemail);
		
		JLabel lblemail  = new JLabel();
		lblemail.setBounds(200, 200, 100, 30);
		add(lblemail);
		
		try {
			conn c =new conn();
			String query = "select * from volunteer where volunteerId ='"+cVolunteerId.getSelectedItem()+"'";
			ResultSet rs = c.s.executeQuery(query);
			while(rs.next()) {
				lblname.setText(rs.getString("name"));
				lblphone.setText(rs.getString("phone"));
				lblemail.setText(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cVolunteerId.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
						try {
							conn c =new conn();
							String query = "select * from volunteer where volunteerId ='"+cVolunteerId.getSelectedItem()+"'";
							ResultSet rs = c.s.executeQuery(query);
							while(rs.next()) {
								lblname.setText(rs.getString("name"));
								lblphone.setText(rs.getString("phone"));
								lblemail.setText(rs.getString("email"));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
			}
		});
		
		delete = new JButton("Delete");
		delete.setBounds(80, 300, 100, 30);
		delete.setBackground(Color.BLACK);
		delete.setForeground(Color.WHITE);
		delete.addActionListener(this);
		add(delete);
		
		back = new JButton("Back");
		back.setBounds(220, 300, 100, 30);
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
				String query = "delete from volunteer where volunteerId = '"+cVolunteerId.getSelectedItem()+"'";
				c.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null,"Volunteer Information Deleted Successfully");
				setVisible(false);
				new volunteerManagement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if  (ae.getSource() == back) {
			setVisible(false);
			new volunteerManagement();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new RemoveVolunteer();
	}

}

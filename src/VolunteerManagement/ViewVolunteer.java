package VolunteerManagement;

import javax.swing.*;

import main.DbUtils;
import main.Home;
import main.conn;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewVolunteer extends JFrame implements ActionListener {
	
	JTable table;
	Choice cvolunteerId;
	JButton search,print, update, back;
	
	
	public ViewVolunteer(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel searchlbl = new JLabel("Search by Volunteer ID");
		searchlbl.setBounds(20, 20, 150, 20);
		add(searchlbl);
		
		cvolunteerId = new Choice();
		cvolunteerId.setBounds(180, 20, 150, 20);
		add(cvolunteerId);
		
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from volunteer");
			
			while(rs.next()) {
				cvolunteerId.add(rs.getString("volunteerId"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		table = new JTable();
		
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from volunteer");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(0,100,900,600);
		add(jsp);
		
		search = new JButton("Search");
		search.setBounds(20, 70, 80, 20);
		search.addActionListener(this);
		add(search);
		
		print = new JButton("Print");
		print.setBounds(120, 70, 80, 20);
		print.addActionListener(this);
		add(print);
		
		update = new JButton("Update");
		update.setBounds(220, 70, 80, 20);
		update.addActionListener(this);
		add(update);
		
		back = new JButton("Back");
		back.setBounds(320, 70, 80, 20);
		back.addActionListener(this);
		add(back);
		
		setSize(900,700);
		setLocation(300,100);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == search) {
			String query  = "select * from volunteer where volunteerId = '"+cvolunteerId.getSelectedItem()+"'";
			try {
				conn c = new conn();
				ResultSet rs = c.s.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				
			}
		} else if (ae.getSource() == print) {
			try {
				table.print();
			}	catch (Exception e) {
				e.printStackTrace();
			}		
		} else if (ae.getSource() == update) {
			setVisible(false);
			new UpdateVolunteer(cvolunteerId.getSelectedItem());
		} else if (ae.getSource() == back){
			setVisible(false);
			new Home();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewVolunteer();
	}

}

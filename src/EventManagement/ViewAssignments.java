package EventManagement;

import javax.swing.*;

import database.DbUtils;
import database.conn;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewAssignments extends JFrame implements ActionListener {
	
	JTable assignmenttable, eventtable;
	Choice ceventId;
	JButton searchEvent,back;
	
	ViewAssignments(){
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading = new JLabel("Volunteer Assignment Details");
		heading.setBounds(450,30,500,50);
		heading.setFont(new Font("SAN_SERIF",Font.BOLD, 25));
		add(heading);
		
		JLabel searchEventlbl = new JLabel("Search by Event ID");
		searchEventlbl.setBounds(50, 100, 150, 20);
		add(searchEventlbl);
		
		ceventId = new Choice();
		ceventId.setBounds(200, 100, 150, 20);
		add(ceventId);
		
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select eventId, eventName from event");
			
			while(rs.next()) {
				ceventId.add(rs.getString("eventId"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		eventtable = new JTable();
		
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select  eventId, eventName, eventDate, location from event");
			eventtable.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane Eventjsp = new JScrollPane(eventtable);
		Eventjsp.setBounds(00,200,700,500);
		add(Eventjsp);
		
		searchEvent = new JButton("Search Event");
		searchEvent.setBounds(50, 140, 150, 20);
		searchEvent.addActionListener(this);
		add(searchEvent);
		
		assignmenttable = new JTable();
		
		
		JScrollPane Assignmentjsp = new JScrollPane(assignmenttable);
		Assignmentjsp.setBounds(700,200,500,500);
		add(Assignmentjsp);
		
		back = new JButton("Back");
		back.setBounds(1080, 140, 80, 20);
		back.addActionListener(this);
		add(back);
		
		setSize(1200,700);
		setLocation(200,100);
		setVisible(true);
		
	}
	
	 

	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == searchEvent) {
            String selectedEventId = ceventId.getSelectedItem();
            
            String query = "SELECT v.volunteerId, v.name AS volunteerName, v.phone AS phone " +
                           "FROM volunteer_event ve " +
                           "JOIN volunteer v ON ve.volunteerId = v.volunteerId " +
                           "WHERE ve.eventId = ?";
            try {
                conn c = new conn();
                PreparedStatement pstmt = c.c.prepareStatement(query);
                pstmt.setString(1, selectedEventId);
                ResultSet rs = pstmt.executeQuery();
                assignmenttable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading assignments: " + e.getMessage());
            }
        } else if (ae.getSource() == back){
			setVisible(false);
			new Event();
		}
    }
		
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ViewAssignments();
	}

}

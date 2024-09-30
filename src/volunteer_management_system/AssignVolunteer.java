package volunteer_management_system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import volunteer_management_system.DbUtils;
import java.awt.event.*;

public class AssignVolunteer extends JFrame implements ActionListener {
	
	JButton searchVolunteer, searchEvent, back, assignVolunteer, dismissVolunteer;
	Choice cvolunteerId, ceventId;
	JTable volunteertable, eventtable;
	
	AssignVolunteer(){
	
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
		ResultSet rs = c.s.executeQuery("select volunteerId, name, address from volunteer");
		
		while(rs.next()) {
			cvolunteerId.add(rs.getString("volunteerId"));
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	volunteertable = new JTable();
	
	try {
		conn c = new conn();
		ResultSet rs = c.s.executeQuery("select volunteerId, name, address from volunteer");
		volunteertable.setModel(DbUtils.resultSetToTableModel(rs));
		
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	JScrollPane Volunteerjsp = new JScrollPane(volunteertable);
	Volunteerjsp.setBounds(0,100,700,500);
	add(Volunteerjsp);
	
	searchVolunteer = new JButton("Search Volunteer");
	searchVolunteer.setBounds(20, 70, 150, 20);
	searchVolunteer.addActionListener(this);
	add(searchVolunteer);
	
	// after this starts the event part
	
	JLabel searchEventlbl = new JLabel("Search by Event ID");
	searchEventlbl.setBounds(750, 20, 150, 20);
	add(searchEventlbl);
	
	ceventId = new Choice();
	ceventId.setBounds(930, 20, 150, 20);
	add(ceventId);
	
	try {
		conn c = new conn();
		ResultSet rs = c.s.executeQuery("select eventId, eventName, eventDate, location from event");
		
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
	Eventjsp.setBounds(700,100,830,500);
	add(Eventjsp);
	
	searchEvent = new JButton("Search Event");
	searchEvent.setBounds(750, 70, 150, 20);
	searchEvent.addActionListener(this);
	add(searchEvent);
	
	back = new JButton("Back");
	back.setBounds(1400, 70, 80, 20);
	back.addActionListener(this);
	add(back);
	
	assignVolunteer = new JButton("Assign Volunteer");
	assignVolunteer.setBounds(550, 610, 200, 30);
	assignVolunteer.addActionListener(this);
	add(assignVolunteer);
	
	dismissVolunteer = new JButton("Dismiss Volunteer");
	dismissVolunteer.setBounds(750, 610, 200, 30);
	dismissVolunteer.addActionListener(this);
	add(dismissVolunteer);
	
	setSize(1530,700);
	setLocation(0,100);
	setVisible(true);
	
	}
	
	 private void assignVolunteerToEvent(String volunteerId, String eventId) {
	        
		 String checkQuery = "select count(*) from volunteer_event where volunteerId = ? and eventId = ? ";
		 String assignQuery = "INSERT INTO volunteer_event (volunteerId, eventId) VALUES (?, ?)";
   	     
		 try {
	            conn c = new conn();
	            
	            PreparedStatement checkStmt = c.c.prepareStatement(checkQuery);
	            checkStmt.setString(1, volunteerId);
	            checkStmt.setString(2, eventId);
	            ResultSet rs = checkStmt.executeQuery();

	            if (rs.next() && rs.getInt(1) > 0) {
	                JOptionPane.showMessageDialog(this, "Error: This volunteer is already assigned to this event.");
	                return;
	            }
	             
	            PreparedStatement pstmt = c.c.prepareStatement(assignQuery);
	            pstmt.setString(1, volunteerId);
	            pstmt.setString(2, eventId);
	            int rowsAffected = pstmt.executeUpdate();
	           
	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(this, "Volunteer assigned successfully!");
	            } else {
	                JOptionPane.showMessageDialog(this, "Failed to assign volunteer.");
	            }	
	            	}
	         catch (SQLException e) {
	        	
	        	if (e.getErrorCode() == 1062) {
	        		JOptionPane.showMessageDialog(this, "Error: This volunteer is already assigned to this event.");
	        	       
	        	} else {
	        	e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
	        }
	        	}
	    }
	 
	 private void dismissVolunteerFromEvent(String volunteerId, String eventId) {
		  
		 String deleteQuery = "delete from volunteer_event where volunteerId = ? and eventId = ?";
		    
		    try {
		        conn c = new conn();
		        
		        PreparedStatement pstmt = c.c.prepareStatement(deleteQuery);
		        pstmt.setString(1, volunteerId);
		        pstmt.setString(2, eventId);
		        int rowsAffected = pstmt.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            JOptionPane.showMessageDialog(this, "Volunteer dismissed from the event successfully!");
		        } else {
		            JOptionPane.showMessageDialog(this, "Error: No such assignment exists for this volunteer and event.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
		    }
		}

	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == searchVolunteer) {
			String volunteerquery  = "select  volunteerId ,name, address from volunteer where volunteerId = '"+cvolunteerId.getSelectedItem()+"'";
			try {
				conn c = new conn();
				ResultSet rs = c.s.executeQuery(volunteerquery);
				volunteertable.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {
				e.printStackTrace();
				} 
			}
			if(ae.getSource() == searchEvent) {
				String eventquery  = "select  eventId, eventName, eventDate, location from event where eventId = '"+ceventId.getSelectedItem()+"'";
				try {
					conn c = new conn();
					ResultSet rs = c.s.executeQuery(eventquery);
					eventtable.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
					} 
				}	
			
			if (ae.getSource() == assignVolunteer) {
	           
				String selectedVolunteerId = cvolunteerId.getSelectedItem();
	            String selectedEventId = ceventId.getSelectedItem();
	            
	            if (selectedVolunteerId != null && selectedEventId != null) {
	                assignVolunteerToEvent(selectedVolunteerId, selectedEventId);
	            } else {
	                JOptionPane.showMessageDialog(this, "Please select both a volunteer and an event.");
	            }
			}			
	
	if (ae.getSource() == dismissVolunteer) {
        
		String selectedVolunteerId = cvolunteerId.getSelectedItem();
        String selectedEventId = ceventId.getSelectedItem();
        
        if (selectedVolunteerId != null && selectedEventId != null) {
        	dismissVolunteerFromEvent(selectedVolunteerId, selectedEventId);
        } else {
            JOptionPane.showMessageDialog(this, "Please select both a volunteer and an event.");
        }
    }
	
	else if (ae.getSource() == back){
			setVisible(false);
			new Event();
		}
	}
				
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AssignVolunteer();
	}

}

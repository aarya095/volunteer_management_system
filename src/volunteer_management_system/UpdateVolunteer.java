package volunteer_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;

public class UpdateVolunteer extends JFrame implements ActionListener {
	
	
	JTextField tfeducation,tffname,tfphone,tfemail,tfaddress,tfdesignation;
	JButton add,back;
	JLabel lblvolunteerId;
	String volunteerId;
	
	UpdateVolunteer(String volunteerId){
		
		this.volunteerId = volunteerId;
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading = new JLabel("Update Volunteer Details");
		heading.setBounds(320,30,500,50);
		heading.setFont(new Font("SAN_SERIF",Font.BOLD, 25));
		add(heading);
		
		JLabel labelname = new JLabel("Name: ");
		labelname.setBounds(50,150,150,30);
		labelname.setFont(new Font("serif", Font.PLAIN,20));
		add(labelname);
		
		JLabel lblname = new JLabel();
		lblname.setBounds(200,150,150,30);
		add(lblname);
		
		JLabel labelfname = new JLabel("Father's Name: ");
		labelfname.setBounds(400,150,150,30);
		labelfname.setFont(new Font("serif", Font.PLAIN,20));
		add(labelfname);
		
		 tffname = new JTextField();
		tffname.setBounds(600,150,150,30);
		add(tffname);
		
		JLabel labeldob = new JLabel("Date Of Birth: ");
		labeldob.setBounds(50,200,150,30);
		labeldob.setFont(new Font("serif", Font.PLAIN,20));
		add(labeldob);
		
		 JLabel lbldob = new JLabel();
		 lbldob.setBounds(200,200,150,30);
		 add(lbldob);
		
		JLabel labeladdress = new JLabel("Address: ");
		labeladdress.setBounds(50,250,150,30);
		labeladdress.setFont(new Font("serif", Font.PLAIN,20));
		add(labeladdress);
		
		tfaddress = new JTextField();
		tfaddress.setBounds(200,250,150,30);
		add(tfaddress);
		
		JLabel labelphone = new JLabel("Phone: ");
		labelphone.setBounds(400,200,150,30);
		labelphone.setFont(new Font("serif", Font.PLAIN,20));
		add(labelphone);
		
		tfphone = new JTextField();
		tfphone.setBounds(600,200,150,30);
		add(tfphone);
		
		JLabel labelemail = new JLabel("Email: ");
		labelemail.setBounds(50,300,150,30);
		labelemail.setFont(new Font("serif", Font.PLAIN,20));
		add(labelemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(200,300,150,30);
		add(tfemail);
		
		JLabel labeleducation = new JLabel("Highest Education: ");
		labeleducation.setBounds(400,250,200,30);
		labeleducation.setFont(new Font("serif", Font.PLAIN,20));
		add(labeleducation);
		
		tfeducation = new JTextField();
		tfeducation.setBounds(600,250,150,30);
		add(tfeducation);
		
		JLabel labeldesignation = new JLabel("Designation: ");
		labeldesignation.setBounds(50,350,150,30);
		labeldesignation.setFont(new Font("serif", Font.PLAIN,20));
		add(labeldesignation);
		
		tfdesignation = new JTextField();
		tfdesignation.setBounds(200,350,150,30);
		add(tfdesignation);
		
		JLabel labelaadhar = new JLabel("Aadhar Number: ");
		labelaadhar.setBounds(400,300,150,30);
		labelaadhar.setFont(new Font("serif", Font.PLAIN,20));
		add(labelaadhar);
		
		JLabel lblaadhar = new JLabel();
		lblaadhar.setBounds(600,300,150,30);
		add(lblaadhar);
		
		JLabel labelvolunteerId = new JLabel("Volunteer ID: ");
		labelvolunteerId.setBounds(400,350,150,30);
		labelvolunteerId.setFont(new Font("serif", Font.PLAIN,20));
		add(labelvolunteerId);
		
		lblvolunteerId = new JLabel();
		lblvolunteerId.setBounds(600,350,150,30);
		lblvolunteerId.setFont(new Font("serif", Font.PLAIN,20));
		add(lblvolunteerId);
		
		try {
			conn c = new conn();
			String query = "select * from volunteer where volunteerId ='"+volunteerId+"'";
			ResultSet rs = c.s.executeQuery(query);
			while(rs.next()) {
				lblname.setText(rs.getString("name"));
				tffname.setText(rs.getString("fname"));
				lbldob.setText(rs.getString("dob"));
				tfaddress.setText(rs.getString("address"));
				tfphone.setText(rs.getString("phone"));
				tfemail.setText(rs.getString("email"));
				tfeducation.setText(rs.getString("education"));
				lblaadhar.setText(rs.getString("aadhar"));
				lblvolunteerId.setText(rs.getString("volunteerId"));
				tfdesignation.setText(rs.getString("designation"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	add = new JButton("Add Details");
		add.setBounds(250,550,150,40);
		add.addActionListener(this);
		add.setBackground(Color.BLACK);
		add.setForeground(Color.WHITE);
		add(add);
	
		back = new JButton("Back");
		back.setBounds(450,550,150,40);
		back.addActionListener(this);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		add(back);

		setSize(900,700);
		setLocation(300,50);
		setVisible(true);
	
}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == add) {
			
			String fname = tffname.getText();
			String phone = tfphone.getText();
			String email = tfemail.getText();
			String address = tfaddress.getText();
			String education = tfeducation.getText();
			String designation = tfdesignation.getText();
			
			try {
				conn conn = new conn();                         
				String query = "update volunteer set fname ='"+fname+"', address ='"+address+"', phone = '"+phone+"', email = '"+email+"', education = '"+education+"', designation = '"+designation+"' where volunteerId = '"+volunteerId+"')";
			    conn.s.executeUpdate(query);
			    JOptionPane.showMessageDialog(null, "Details added Successfully");
			    setVisible(false);
			    new Home();
			    
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else{
			setVisible(false);
			new Home();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new UpdateVolunteer("");
	}

}

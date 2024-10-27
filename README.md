NGO Volunteer and Event Management System

-Description:

The NGO Volunteer and Event Management System is a desktop application designed to streamline the management of volunteers and events. It provides an easy-to-use interface for administrators to manage volunteer information, events, and assignments. The system improves coordination by reducing the administrative workload and allows administrators to send email notifications to users.

-Features:

    Volunteer Management: Add, view, update, and delete volunteer details.
    Event Management: Create and manage events, and assign volunteers to specific events.
    Authentication System:
        Administrator login with username and password.
        OTP-based password recovery system via email.
    Reset Password & Username: Admin can reset both username and password simultaneously using OTP verification.
    Email Integration: Send email notifications to volunteers (e.g., OTP for recovery) using Gmail SMTP.
    GUI Interface: User-friendly GUI built with Java Swing.

-Technologies Used:

    Programming Language: Java
    IDE: Eclipse
    Database: MySQL
    GUI Framework: Java Swing
    Java Platform Module System (JPMS): Used to modularize the project.
    Email Libraries:
        mail-1.4.7.jar
        activation-1.1.1.jar
    Database Connector:
        mysql-connector-java-8.0.28.jar
    JDK Version: 17

-Installation and Setup Instructions
 -Prerequisites

    Java JDK 17 installed on your system.
    MySQL Server installed and running.
    Eclipse IDE (or any other Java IDE) for development.
    Add the following JAR files to the project’s ModulePath:
        mail-1.4.7.jar
        activation-1.1.1.jar
        mysql-connector-java-8.0.28.jar

-Database Setup

    Create a new database called volunteermanagementsystem.

    Use the following SQL script to create the necessary tables:

    sql

CREATE TABLE login (
    email VARCHAR(100) PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE volunteers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    event_assigned VARCHAR(100)
);

CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    date DATE
);

Insert a test admin account (optional):

sql

    INSERT INTO login (email, username, password) 
    VALUES ('admin@example.com', 'admin', 'password123');

-Project Setup:

    Clone the repository or download the source code.
    Open the project in Eclipse IDE.
    Add the necessary libraries to the ModulePath in Eclipse:
        mysql-connector-java-8.0.28.jar
        mail-1.4.7.jar
        activation-1.1.1.jar
    Configure Java firewall access to allow SMTP communication.

-Usage:
1. Login

    The admin logs in with the username and password stored in the MySQL login table.

2. Managing Volunteers

    Add, update, or remove volunteers through the GUI.

3. Managing Events

    Create and manage events. Assign volunteers to events.

4. Reset Password & Username (with OTP)

    If the admin forgets their password, they can use the “Forgot Password” feature.
    An OTP will be sent to the admin's registered email.
    Once verified, the admin can reset both the username and password.

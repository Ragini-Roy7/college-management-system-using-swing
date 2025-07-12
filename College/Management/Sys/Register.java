package College.Management.Sys;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class Register extends JFrame {
    JTextField usernameField, emailField;
    JPasswordField passwordField;
    JButton registerButton;

    Register() {
        setTitle("Register - College Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 30, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 150, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 80, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 150, 30);
        add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 130, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 130, 150, 30);
        add(emailField);

        registerButton = new JButton("Register");
        registerButton.setBounds(130, 180, 120, 40);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = String.valueOf(passwordField.getPassword());
                String email = emailField.getText();

                if (user.isEmpty() || pass.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                } else {
                    registerUser(user, pass, email);
                }
            }
        });

        setVisible(true);
    }

    // JDBC Method
    public void registerUser(String username, String password, String email) {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection to DB
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_management_system",
                    "root",
                    "Dw8eihqNdh/h" //your MySQL password
            );

            // Insert query
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";


            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "User registered successfully!");
                dispose(); // Close register window
            }

            conn.close();
        } catch (SQLIntegrityConstraintViolationException dup) {
            JOptionPane.showMessageDialog(null, "Username already exists!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred during registration.");
        }
    }

    public static void main(String[] args) {
        new Register();
    }
}


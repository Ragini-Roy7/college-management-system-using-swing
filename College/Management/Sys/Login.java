package College.Management.Sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField textFieldName;
    JPasswordField passwordField;
    JButton login, back;

    Login() {
        setTitle("Login - College Management System");
        setLayout(null);

        // Username
        JLabel labelName = new JLabel("Username:");
        labelName.setBounds(40, 40, 100, 20);
        add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(150, 40, 150, 25);
        add(textFieldName);

        // Password
        JLabel labelPass = new JLabel("Password:");
        labelPass.setBounds(40, 80, 100, 20);
        add(labelPass);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 150, 25);
        add(passwordField);

        // Login Button
        login = new JButton("Login");
        login.setBounds(40, 130, 120, 30);
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);
        login.setFont(new Font("Arial", Font.BOLD, 12));
        login.addActionListener(this);
        add(login);

        login.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                login.setBackground(Color.BLUE);
            }

            public void mouseExited(MouseEvent evt) {
                login.setBackground(Color.BLACK);
            }
        });

        // Back Button
        back = new JButton("Back");
        back.setBounds(180, 130, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Arial", Font.BOLD, 12));
        back.addActionListener(this);
        add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                back.setBackground(Color.BLACK);
            }

            public void mouseExited(MouseEvent evt) {
                back.setBackground(Color.BLUE);
            }
        });

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/second.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(350, 40, 200, 200);
        add(image);

        // Background
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Icon/loginback.png"));
        Image i5 = i4.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i5));
        img.setBounds(0, 0, 600, 300);
        add(img);

        // Frame Settings
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = textFieldName.getText();
            String password = new String(passwordField.getPassword());

            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";

            try {
                Conn c = new Conn(); // Make sure Conn class is defined
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                    new main_class(); // Assuming this opens our dashboard
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while logging in.");
            }

        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}


package College.Management.Sys;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentLeave extends JFrame implements ActionListener {
    Choice choiceRollNo, choTime;
    JDateChooser selDate;
    JButton submit, cancel;

    StudentLeave() {
        setTitle("Student Leave Application");
        setLayout(null);
        getContentPane().setBackground(new Color(210, 232, 252));

        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel rollLabel = new JLabel("Search by Roll Number");
        rollLabel.setBounds(60, 100, 200, 20);
        rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(rollLabel);

        choiceRollNo = new Choice();
        choiceRollNo.setBounds(60, 130, 200, 20);
        add(choiceRollNo);

        // Fill dropdown with roll numbers
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM student");
            while (resultSet.next()) {
                choiceRollNo.add(resultSet.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 180, 200, 20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldate);

        selDate = new JDateChooser();
        selDate.setBounds(60, 210, 200, 25);
        add(selDate);

        JLabel time = new JLabel("Time Duration");
        time.setBounds(60, 260, 200, 20);
        time.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(time);

        choTime = new Choice();
        choTime.setBounds(60, 290, 200, 20);
        choTime.add("Full Day");
        choTime.add("Half Day");
        add(choTime);

        submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setSize(500, 500);
        setLocation(550, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String rollno = choiceRollNo.getSelectedItem();
            String date = ((JTextField) selDate.getDateEditor().getUiComponent()).getText();
            String time = choTime.getSelectedItem();

            String Q = "INSERT INTO studentLeave VALUES('" + rollno + "','" + date + "','" + time + "')";
            try {
                Conn c = new Conn();
                c.statement.executeUpdate(Q);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error in submitting leave.");
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentLeave();
    }
}

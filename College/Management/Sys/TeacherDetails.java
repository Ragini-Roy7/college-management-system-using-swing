package College.Management.Sys;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; //  rs2xml.jar is added to our project

public class TeacherDetails extends JFrame implements ActionListener {
    Choice choice;
    JTable table;
    JButton search, print, update, addBtn, cancel;

    TeacherDetails() {
        getContentPane().setBackground(new Color(192, 164, 252));
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee ID");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        choice = new Choice();
        choice.setBounds(180, 20, 150, 20);
        add(choice);

        // Populate dropdown with employee IDs
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT empId FROM teacher");
            while (resultSet.next()) {
                choice.add(resultSet.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table setup
        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM teacher");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 100, 900, 600);
        add(js);

        // Buttons
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        addBtn = new JButton("Add");
        addBtn.setBounds(220, 70, 80, 20);
        addBtn.addActionListener(this);
        add(addBtn);

        update = new JButton("Update");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String q = "SELECT * FROM teacher WHERE empId = '" + choice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == addBtn) {
            setVisible(false);
            new AddFaculty();

        } else if (e.getSource() == update) {
            // Placeholder for update logic
            //JOptionPane.showMessageDialog(null, "Update logic not implemented yet.");

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}


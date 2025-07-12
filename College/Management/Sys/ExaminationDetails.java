package College.Management.Sys;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ExaminationDetails extends JFrame implements ActionListener {

    JTextField search;
    JButton result, back, print;
    JTable table;

    ExaminationDetails() {
        getContentPane().setBackground(new Color(241, 252, 210));

        JLabel heading = new JLabel("Check Result");
        heading.setBounds(350, 15, 400, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        search = new JTextField();
        search.setBounds(80, 90, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        result = new JButton("Result");
        result.setBounds(300, 90, 100, 30);
        result.setBackground(Color.black);
        result.setForeground(Color.white);
        result.addActionListener(this);
        add(result);

        back = new JButton("Back");
        back.setBounds(420, 90, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        print = new JButton("Print");
        print.setBounds(540, 90, 100, 30);
        print.setBackground(Color.black);
        print.setForeground(Color.white);
        print.addActionListener(this);
        add(print);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 140, 1000, 300);
        add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 2).toString());
            }
        });

        setSize(1000, 500);
        setLocation(300, 100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == result) {
            setVisible(false);
           new  EnterMarks();

        }
        else if (e.getSource() == back) {
            setVisible(false);
        } else if (e.getSource() == print) {
            try {
                if (!table.print()) {
                    JOptionPane.showMessageDialog(null, "Unable to print table");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while printing: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        new ExaminationDetails();
    }
}


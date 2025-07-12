
package College.Management.Sys;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    Thread t;

    Splash() {
        // Set frame properties first
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Main panel
        JPanel content = new JPanel();
        content.setLayout(null); // Allows absolute positioning
        content.setBackground(Color.WHITE);
        setContentPane(content);

        // Load and scale the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/first.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1000, 500);
        content.add(img);

        // Welcome Label
        JLabel text = new JLabel("WELCOME TO COLLEGE MANAGEMENT SYSTEM");
        text.setFont(new Font("Serif", Font.BOLD, 30));
        text.setForeground(new Color(0, 102, 204));
        text.setBounds(100, 520, 800, 40);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(text);

        // Progress bar
        JProgressBar progress = new JProgressBar();
        progress.setBounds(300, 580, 400, 20);
        progress.setIndeterminate(true); // Shows loading
        content.add(progress);

        setVisible(true);

        // Start thread
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            Thread.sleep(3000); // Wait 3 seconds
            setVisible(false);
            //  Open login screen next
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}


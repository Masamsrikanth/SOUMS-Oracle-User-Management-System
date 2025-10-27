import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public Dashboard(String username) {

        setTitle("Dashboard - Welcome " + username);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome, " + username + "!");
        lblWelcome.setBounds(120, 30, 200, 30);
        add(lblWelcome);

        JButton btnViewUsers = new JButton("View Users");
        btnViewUsers.setBounds(120, 80, 150, 30);
        add(btnViewUsers);

        JButton btnAddUser = new JButton("Add User");
        btnAddUser.setBounds(120, 120, 150, 30);
        add(btnAddUser);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(120, 160, 150, 30);
        add(btnLogout);

        btnViewUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewUsers();
            }
        });

        btnAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterForm();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm();
            }
        });

        setVisible(true);
    }
}

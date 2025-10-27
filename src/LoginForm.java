import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginForm() {
        setTitle("Login Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(20, 30, 100, 25);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(100, 30, 150, 25);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(20, 70, 100, 25);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(100, 70, 150, 25);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 110, 100, 30);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }

    void login() {
        String user = txtUser.getText();
        String pass = String.valueOf(txtPass.getPassword());

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/orcl",
                "system",
                "system123"
            );

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, PasswordUtil.hashPassword(pass));


            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, " Login Successful!");
                 dispose();
                 new Dashboard(user);

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }

            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}

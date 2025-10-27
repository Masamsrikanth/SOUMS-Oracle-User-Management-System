import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.security.MessageDigest;


public class RegisterForm extends JFrame {
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnRegister;

    public RegisterForm() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        btnRegister = new JButton("Register");
        btnRegister.setBounds(100, 110, 100, 30);
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }

    void registerUser() {
        String user = txtUser.getText();
        String pass = String.valueOf(txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Enter Username & Password!");
            return;
        }

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/orcl",
                "system",
                "system123"
            );

            // Check if username exists
            String check = "SELECT * FROM users WHERE username=?";
            PreparedStatement pstCheck = con.prepareStatement(check);
            pstCheck.setString(1, user);
            ResultSet rs = pstCheck.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "⚠ Username Already Exists!");
                return;
            }

            // Insert new user
            String query = "INSERT INTO users(username,password) VALUES(?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, PasswordUtil.hashPassword(pass));

           

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, " Registration Successful!");

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RegisterForm();
    }
}

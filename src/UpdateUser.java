import javax.swing.*;
import java.sql.*;

public class UpdateUser extends JFrame {

    int userId;
    JTextField txtUsername;
    JPasswordField txtPassword;

    public UpdateUser(int id, String username) {
        this.userId = id;

        setTitle("Update User");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(30, 40, 100, 25);
        add(lblUser);

        txtUsername = new JTextField(username);
        txtUsername.setBounds(130, 40, 150, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 80, 100, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 80, 150, 25);
        add(txtPassword);

        JButton btnUpdate = new JButton("Save");
        btnUpdate.setBounds(100, 130, 120, 30);
        add(btnUpdate);

        btnUpdate.addActionListener(e -> updateUser());

        setVisible(true);
    }

    void updateUser() {
        String uname = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/orcl",
                "system",
                "system123"
            );

            String sql = "UPDATE users SET username=?, password=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, uname);
            pst.setString(2, PasswordUtil.hashPassword(pass));
            pst.setInt(3, userId);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, " User Updated Successfully!");

            con.close();
            dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

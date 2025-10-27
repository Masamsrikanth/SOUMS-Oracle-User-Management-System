import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewUsers extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton btnDelete;

    public ViewUsers() {
        setTitle("Users List");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Password (Hashed)");

        fetchUsers();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 450, 180);
        add(scrollPane);

        btnDelete = new JButton("Delete User");
        btnDelete.setBounds(180, 220, 120, 30);
        add(btnDelete);

        //  Link button to delete function
        btnDelete.addActionListener(e -> deleteUser());
        JButton btnUpdate = new JButton("Edit User");
        btnUpdate.setBounds(320, 220, 120, 30);
        add(btnUpdate);

        btnUpdate.addActionListener(e -> editUser());


        setVisible(true);
    }

    void fetchUsers() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/orcl",
                "system",
                "system123"
            );

            String sql = "SELECT id, username, password FROM users";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
                });
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Delete selected user function
    void deleteUser() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "⚠ Please select a user!");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/orcl",
                "system",
                "system123"
            );

            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, " User Deleted!");

            fetchUsers(); //  Refresh table

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void editUser() {
    int row = table.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "⚠ Please select a user!");
        return;
    }

    int id = (int) model.getValueAt(row, 0);
    String username = (String) model.getValueAt(row, 1);

    new UpdateUser(id, username);
}

}

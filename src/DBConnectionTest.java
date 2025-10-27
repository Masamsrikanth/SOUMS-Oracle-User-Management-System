import java.sql.*;

public class DBConnectionTest {
    public static void main(String[] args) {
        try {
            //  Correct Driver
            Class.forName("oracle.jdbc.OracleDriver");
            
            //  Correct Connection URL for SERVICE_NAME
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/orcl",
                "system",
                "system123"
            );
            
            System.out.println(" Database Connected Successfully!");
            con.close();
        } catch (Exception e) {
            System.out.println(" Connection Failed!");
            e.printStackTrace();
        }
    }
}

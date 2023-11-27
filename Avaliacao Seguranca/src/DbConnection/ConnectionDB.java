package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mariadb://localhost/clinica", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/monkies_database";
    private static final String USER = "root";
    private static final String PASSWORD = "infobi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}



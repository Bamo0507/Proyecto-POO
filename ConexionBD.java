
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionBD {
    private Connection currentConnection = null;
    public Statement stQuery = null;
    public ResultSet rsRecords = null;
    private String DB_URL = "";
    private String DB_USERNAME = "";
    private String DB_PASSWORD = "";

    public ConexionBD(String URL, String USER, String PASSWORD) {
        this.DB_URL = URL;
        this.DB_USERNAME = USER;
        this.DB_PASSWORD = PASSWORD;
    }

    public Connection getNewConnection() {
        try{
            currentConnection = null;

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            currentConnection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD);
        }
        catch (Exception ex) {
            System.out.println ("ConnectionBD->getConnection()..Error..: " + ex.getMessage());
            ex.printStackTrace();
        }
        return currentConnection;
    }

    public Connection getCurrentConnection() {
        return currentConnection;
    }

    public void closeConnection () {
        try {
            currentConnection.close();
        }
        catch (Exception ex) {
            System.out.println ("ConnectionBD->getConnection()..Error..: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


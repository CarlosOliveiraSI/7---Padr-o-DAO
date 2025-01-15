package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author carlo
 */
public class DatabaseConnect {
    
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1/facebook";
    static final String USER = "root";
    static final String PASSWORD = "";
    
    static {
        try {
            Class.forName(JDBC_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver JDBC", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }
}

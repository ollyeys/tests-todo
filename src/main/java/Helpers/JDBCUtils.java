package Helpers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {

    private static final String jdbcURL = System.getenv("DB_URL");


    private static final String jdbcUsername = System.getenv("DB_USER");
    private static final String jdbcPassword = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;

    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
}

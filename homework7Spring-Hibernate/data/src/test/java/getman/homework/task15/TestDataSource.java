package getman.homework.task15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDataSource {
    private static TestDataSource dataSource;
    protected TestDataSource() throws ClassNotFoundException {
        // Load JDBC driver for MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    protected Connection getOnlineBankConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/homework_spring_hibernate",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new TestDataSource();
        }
        return dataSource.getOnlineBankConnection();
    }
}

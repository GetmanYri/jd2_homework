package getman.homework.task7.data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDataSource {
    private static TestDataSource testDataSource;
    private TestDataSource() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

    }

    private Connection getUtilConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ListExpensesCreateLB_test",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(testDataSource ==null){
            testDataSource =new TestDataSource();
        }
        return testDataSource.getUtilConnection();
    }
}

package getman.homework.task5.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static DataSource dataSource;
    private DataSource() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

    }

    private Connection getUtilConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/ListExpensesCreateLB",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(dataSource==null){
            dataSource=new DataSource();
        }
        return dataSource.getUtilConnection();
    }
}


package getman.homework.task4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceListExpenses {
    private static DataSourceListExpenses dataSource;
    private DataSourceListExpenses() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

    }

    private Connection getUtilConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/?user=user&password=user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(dataSource==null){
            dataSource=new DataSourceListExpenses();
        }
        return dataSource.getUtilConnection();
    }
}


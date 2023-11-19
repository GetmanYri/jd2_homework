package getman.homework.data.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataTestSource {
    private static DataTestSource dataTestSource;
    private DataTestSource() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

    }

    private Connection getUtilConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernateNADB_test",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(dataTestSource ==null){
            dataTestSource =new DataTestSource();
        }
        return dataTestSource.getUtilConnection();
    }
}


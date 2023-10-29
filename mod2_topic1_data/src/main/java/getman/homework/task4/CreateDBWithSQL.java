package getman.homework.task4;

import java.sql.*;
import java.time.LocalDate;

public class CreateDBWithSQL {

    private static final String nameDataBase = "listExpenses";
    private static final String nameTable1 = "expenses";
    private static final String nameTable2 = "receivers";
    private static int index = 1;

    private static final String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS " + nameDataBase;
    private static final String sqlUseData = "USE " + nameDataBase;
    private static final String sqlCreateTableExpenses = "CREATE Table IF NOT EXISTS " + nameTable1 +
            "(id INT NOT NULL AUTO_INCREMENT," +
            "paydate date," +
            "receiver INT," +
            "value decimal(10,2)," +
            "PRIMARY KEY (id)," + //+
            "FOREIGN KEY (receiver) REFERENCES " + nameTable2 + " (id));";
    private static final String sqlCreateTableReceivers = "CREATE Table IF NOT EXISTS " + nameTable2 +
            "(id INT NOT NULL AUTO_INCREMENT," +
            "receiver varchar(250)," +
            "PRIMARY KEY (id));";
    private static final String sqlSelectTable1 = "SELECT * FROM " + nameTable1 + ";";


    public static void createDataBase(Statement statement) throws SQLException {
        statement.executeUpdate(sqlCreateDatabase);

    }

    public static void insertTable2(Statement statement, String value) throws SQLException {

        String sqlInsertReceivers = "INSERT INTO " + nameTable2 +
                " (receiver)" +
                "VALUE ('" + value + "');";

        ResultSet resultReceivers = statement.executeQuery("SELECT * FROM " + nameTable2 + ";");
        while (resultReceivers.next()) {
            if (resultReceivers.getString(2).equalsIgnoreCase(value)) {
                index = resultReceivers.getInt(1);
                resultReceivers.close();
                break;
            }
            index = resultReceivers.getInt(1) + 1;
        }
        if (!resultReceivers.isClosed()) {
            statement.executeUpdate(sqlInsertReceivers);
            resultReceivers.close();
        }
    }


    public static void insertTable1(Statement statement, LocalDate data, Double value) throws SQLException {
        String sqlInsertExpenses = "INSERT INTO " + nameTable1 +
                " (paydate,receiver,value)" +
                "VALUES ('" + data + "'," + index + "," + value + ");";

        statement.executeUpdate(sqlInsertExpenses);
    }

    public static void printTable1(Connection conn, Statement statement) throws SQLException {
        ResultSet resultExpenses = statement.executeQuery(sqlSelectTable1);
        while (resultExpenses.next()) {
            int id = resultExpenses.getInt(1);
            LocalDate localDate = resultExpenses.getDate(2).toLocalDate();
            int receiver_id = resultExpenses.getInt(3);
            String nameReceiver = "";
            double valueExpense = resultExpenses.getDouble(4);
            nameReceiver = getNameReceiver(receiver_id, conn);
            System.out.println(id + "; " + localDate + "; " + nameReceiver + "; " + valueExpense);
        }
        resultExpenses.close();
    }

    public static String getNameReceiver(int id, Connection conn) throws SQLException {
        String nameReceiver = "";
        Statement statementReceiver = conn.createStatement();

        ResultSet resultReceiver = statementReceiver.executeQuery("SELECT receiver FROM " + nameTable2 + " WHERE id=" + id + ";");
        while (resultReceiver.next()) {
            nameReceiver = resultReceiver.getString("receiver");
        }
        resultReceiver.close();
        statementReceiver.close();

        return nameReceiver;
    }

    public static void printTable2(Statement statement) throws SQLException {
        ResultSet resultReceivers = statement.executeQuery("SELECT * FROM Receivers;");
        System.out.println("__________________________");
        while (resultReceivers.next()) {
            System.out.println(resultReceivers.getString(1) + "; " + resultReceivers.getString(2));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        LocalDate date = LocalDate.parse(args[0]);
        String receiver = args[1];
        Double value = Double.parseDouble(args[2]);

        Connection conn = DataSourceListExpenses.getConnection();
        Statement statement = conn.createStatement();

        createDataBase(statement);
        statement.executeUpdate(sqlUseData);
        statement.executeUpdate(sqlCreateTableReceivers);
        statement.executeUpdate(sqlCreateTableExpenses);
        insertTable2(statement, receiver);
        insertTable1(statement, date, value);
        printTable1(conn, statement);
        printTable2(statement);

        statement.close();
        conn.close();
    }

}

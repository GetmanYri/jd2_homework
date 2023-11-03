package getman.homework.task5;


import getman.homework.task5.data.DataSource;

import java.sql.*;
import java.time.LocalDate;


public class CreateDBWithLB {

    //changing the code using preparedStatement and create DB with liquibase
    private static final String nameDataBase = "ListExpensesCreateLB";
    private static final String nameTable1 = "expenses";
    private static final String nameTable2 = "receivers";
    private static int index = 1;
    private static final String sqlUseData = "USE " + nameDataBase;

    private static final String sqlSelectTable1 = "SELECT o.id, o.paydate, o.value, c.receiver " +
            "FROM "+nameTable2+" AS c, "+nameTable1+" AS o "+
            "WHERE c.id=o.receiver";
    private static final String sqlInsertReceivers = "INSERT INTO " + nameTable2 +
            " (receiver)" +
            "VALUE (?);";
    private static final String sqlInsertExpenses = "INSERT INTO " + nameTable1 +
            " (paydate,receiver,value)" +
            "VALUES (?,?,?);";
    private static final String sqlSelectReceiver = "SELECT receiver FROM " + nameTable2 + " WHERE id=?;";


    public static void insertTable2(Statement statement, String value, Connection conn) throws SQLException {

        PreparedStatement pStatement = conn.prepareStatement(sqlInsertReceivers);
        pStatement.setString(1, value);

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
            pStatement.executeUpdate();
            resultReceivers.close();
        }
        pStatement.close();
    }


    public static void insertTable1(Connection conn, LocalDate data, Double value) throws SQLException {

        PreparedStatement pStatement = conn.prepareStatement(sqlInsertExpenses);
        pStatement.setDate(1, Date.valueOf(data));
        pStatement.setInt(2, index);
        pStatement.setDouble(3, value);

        pStatement.executeUpdate();
        pStatement.close();

    }

    public static void printTable1(Connection conn, Statement statement) throws SQLException {
        ResultSet resultExpenses = statement.executeQuery(sqlSelectTable1);
        while (resultExpenses.next()) {
            int id = resultExpenses.getInt(1);
            LocalDate localDate = resultExpenses.getDate(2).toLocalDate();
            String nameReceiver = resultExpenses.getString(4);
            double valueExpense = resultExpenses.getDouble(3);

            System.out.println(id + "; " + localDate + "; " + nameReceiver + "; " + valueExpense);
        }
        resultExpenses.close();
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

        Connection conn = DataSource.getConnection();
        Statement statement = conn.createStatement();

        statement.executeUpdate(sqlUseData);
        insertTable2(statement, receiver, conn);
        insertTable1(conn, date, value);
        printTable1(conn, statement);
        printTable2(statement);

        statement.close();
        conn.close();
    }

}

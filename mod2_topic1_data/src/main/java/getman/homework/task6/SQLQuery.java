package getman.homework.task6;

import getman.homework.task5.data.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuery {
    private static final String nameDataBase = "ListExpensesCreateLB";
    private static final String sqlSum ="SELECT c.receiver AS Получатель, sum(o.value) AS Сумма " +
            "FROM receivers AS c, expenses AS o " +
            "WHERE c.id=o.receiver " +
            "group by o.receiver;";
    private static final String sqlUseData = "USE " + nameDataBase;
    private static final String sqlMax = "SELECT paydate AS 'Payment date', SUM(value) AS Amount " +
            "FROM expenses " +
            "WHERE paydate=(SELECT paydate FROM expenses WHERE value=(SELECT MAX(value) FROM expenses))" +
            "GROUP BY paydate;";
    private static final String sqlMaxInDate = "select max(value) " +
            "from expenses " +
            "where paydate=(" +
            "select paydate " +
            "from(select paydate, sum(value) AS value from expenses group by paydate) s " +
            "where value=(select max(value) from (select paydate, sum(value) AS value from expenses group by paydate) f)" +
            ");";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn=DataSource.getConnection();
        Statement statement= conn.createStatement();
        statement.executeUpdate(sqlUseData);
        ResultSet rs= statement.executeQuery(sqlSum);
        while(rs.next()) {
            System.out.println(rs.getString(1)+" "+rs.getInt(2));
        }
        rs.close();
        System.out.println("_____________________________");
        rs= statement.executeQuery(sqlMax);
        while(rs.next()) {
            System.out.println(rs.getDate(1)+" "+rs.getInt(2));
        }
        rs.close();
        System.out.println("_____________________________");
        rs= statement.executeQuery(sqlMaxInDate);
        while(rs.next()) {
            System.out.println(rs.getInt(1));
        }
        rs.close();
    }
}



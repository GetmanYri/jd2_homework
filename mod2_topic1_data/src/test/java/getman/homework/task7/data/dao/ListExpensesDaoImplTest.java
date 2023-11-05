package getman.homework.task7.data.dao;

import getman.homework.task7.dao.ListExpensesDao;
import getman.homework.task7.dao.ListExpensesDaoImpl;
import getman.homework.task7.data.TestDataSource;
import getman.homework.task7.model.Expense;
import getman.homework.task7.model.Receiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListExpensesDaoImplTest {
    ListExpensesDao expensesDao;
    private final String sqlInsertReceiver = "INSERT INTO `listexpensescreatelb_test`.`receivers_task7`\n" +
            "(`id`,\n" +
            "`receiver`)\n" +
            "VALUES\n" +
            "(1,\n" +
            "'Mile');";

    private final String sqlInsertReceiver2 = "INSERT INTO `listexpensescreatelb_test`.`receivers_task7`\n" +
            "(`id`,\n" +
            "`receiver`)\n" +
            "VALUES\n" +
            "(2,\n" +
            "'Green');";
    private final String sqlInsertExpense = "INSERT INTO `listexpensescreatelb_test`.`expenses_task7`\n" +
            "(`id`,\n" +
            "`paydate`,\n" +
            "`receiver`,\n" +
            "`value`)\n" +
            "VALUES\n" +
            "(1,\n" +
            "'2023-10-10',\n" +
            "1,\n" +
            "100.2);";

    private final String sqlInsertExpense2 = "INSERT INTO `listexpensescreatelb_test`.`expenses_task7`\n" +
            "(`id`,\n" +
            "`paydate`,\n" +
            "`receiver`,\n" +
            "`value`)\n" +
            "VALUES\n" +
            "(2,\n" +
            "'2023-10-11',\n" +
            "2,\n" +
            "200.2);";

    @org.junit.Before
    public void setUp() throws Exception {
        expensesDao = new ListExpensesDaoImpl(TestDataSource.getConnection());
        Connection connection = TestDataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        statement.executeUpdate("TRUNCATE expenses_task7;");
        statement.executeUpdate("TRUNCATE receivers_task7;");
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");

        statement.close();
        connection.close();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        expensesDao = null;
        Connection connection = TestDataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        statement.executeUpdate("TRUNCATE expenses_task7;");
        statement.executeUpdate("TRUNCATE receivers_task7;");
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");

        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void addReceiver() throws Exception {

        Receiver receiver = new Receiver(1, "bigz");

        Long id = expensesDao.addReceiver(receiver);

        Connection connection = TestDataSource.getConnection();
        ResultSet resultSet = connection.createStatement()
                .executeQuery("Select count(*) from receivers_task7 where id = 1 and receiver = 'bigz'");

        resultSet.next();
        int count = resultSet.getInt(1);

        assertNotNull(id);
        assertEquals(1, count);
        resultSet.close();
        connection.close();
    }

    @org.junit.Test
    public void addExpense() throws Exception {
        Expense expense = new Expense(1, "2023-10-26", 1, 156.2);
        Receiver receiver = new Receiver(1, "bigz");
        expense.setReceiver(receiver);

        expensesDao.addExpense(expense);

        Connection connection = TestDataSource.getConnection();
        ResultSet resultSet = connection.createStatement()
                .executeQuery("Select count(*) from expenses_task7 " +
                        "where id = 1 and paydate='2023-10-26' and receiver = 1 and value=156.2");

        resultSet.next();
        int count = resultSet.getInt(1);

        assertEquals(1, count);
        resultSet.close();
        connection.close();
    }

    @org.junit.Test
    public void getExpenses() throws Exception {
        ArrayList<Expense> expenses;
        Expense expense;
        Connection connection = TestDataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate(sqlInsertReceiver);
        statement.executeUpdate(sqlInsertExpense);
        statement.executeUpdate(sqlInsertReceiver2);
        statement.executeUpdate(sqlInsertExpense2);

        expenses = expensesDao.getExpenses();

        expense = expenses.get(1);
        assertEquals(2, expenses.size());
        assertEquals("2023-10-11", expense.getDate());
        assertEquals(2, expense.getReceiverId());
        assertEquals(200.2, expense.getValue(), 0);

        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void getExpense() throws Exception {
        long id = 1;
        Connection connection = TestDataSource.getConnection();
        connection.createStatement()
                .executeUpdate(sqlInsertReceiver);
        connection.createStatement()
                .executeUpdate(sqlInsertExpense);

        Expense expense = expensesDao.getExpense(id);

        assertNotNull(expense);
        assertEquals(id, expense.getId());
        assertEquals("2023-10-10", expense.getDate());
        assertEquals(1, expense.getReceiverId());
        assertEquals(100.2, expense.getValue(), 0);

        connection.close();
    }

    @org.junit.Test
    public void getReceivers() throws Exception {
        ArrayList<Receiver> receivers;
        Receiver receiver;
        Connection connection = TestDataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.executeUpdate(sqlInsertReceiver);
        statement.executeUpdate(sqlInsertExpense);
        statement.executeUpdate(sqlInsertReceiver2);
        statement.executeUpdate(sqlInsertExpense2);

        receivers = expensesDao.getReceivers();

        receiver = receivers.get(1);
        assertEquals(2, receivers.size());
        assertEquals(2, receiver.getId());
        assertEquals("Green", receiver.getReceiver());

        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void getReceiver() throws Exception {
        long id = 1;
        Connection connection = TestDataSource.getConnection();
        connection.createStatement()
                .executeUpdate(sqlInsertReceiver);
        connection.createStatement()
                .executeUpdate(sqlInsertExpense);

        Receiver receiver = expensesDao.getReceiver(id);

        assertNotNull(receiver);
        assertEquals(id, receiver.getId());
        assertEquals("Mile", receiver.getReceiver());
        connection.close();

    }
}
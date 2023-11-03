package getman.homework.task7.data.dao;

import getman.homework.task7.dao.ListExpensesDao;
import getman.homework.task7.dao.ListExpensesDaoImpl;
import getman.homework.task7.data.TestDataSource;
import getman.homework.task7.model.Receiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ListExpensesDaoImplTest {
    ListExpensesDao expensesDao;

    @org.junit.Before
    public void setUp() throws Exception {
        expensesDao = new ListExpensesDaoImpl(TestDataSource.getConnection());

    }

    @org.junit.After
    public void tearDown() throws Exception {
        expensesDao = null;
        Connection connection = TestDataSource.getConnection();
        Statement statement = connection.createStatement();
      /* statement.execute("ALTER TABLE `expenses_task7` DROP FOREIGN KEY receiver;");
        statement.execute("TRUNCATE `listexpensescreatelb_test`.`receivers_task7`;");
        statement.execute("TRUNCATE `listexpensescreatelb_test`.`expenses_task7`;");*/

        statement.close();
        connection.close();
    }

    @org.junit.Test
    public void addReceiver() throws Exception {

        Receiver receiver = new Receiver(1, "bigz");
        Long id = expensesDao.addReceiver(receiver);

        Connection connection=TestDataSource.getConnection();
        ResultSet resultSet = connection.createStatement()
                .executeQuery("Select count(*) from receivers_task7 where id = 1 and receiver = 'bigz'");

        resultSet.next();
        int count =resultSet.getInt(1);

        assertNotNull(id);
        assertEquals(1,count);

    }

    @org.junit.Test
    public void addExpense() {
    }

    @org.junit.Test
    public void getExpenses() {
    }

    @org.junit.Test
    public void getExpense() {
    }

    @org.junit.Test
    public void getReceivers() {
    }

    @org.junit.Test
    public void getReceiver() {
    }
}
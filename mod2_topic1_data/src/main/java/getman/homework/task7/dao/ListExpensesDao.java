package getman.homework.task7.dao;

import getman.homework.task7.model.Expense;
import getman.homework.task7.model.Receiver;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ListExpensesDao {

    Receiver getReceiver(long id);
    ArrayList<Receiver> getReceivers();
    Expense getExpense(long id);
    ArrayList<Expense> getExpenses();
    long addReceiver(Receiver receiver) throws SQLException, ClassNotFoundException;
    void addExpense(Expense expense);
}

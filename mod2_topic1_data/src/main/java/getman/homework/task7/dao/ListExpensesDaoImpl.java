package getman.homework.task7.dao;

import getman.homework.task7.model.Expense;
import getman.homework.task7.model.Receiver;

import java.sql.*;
import java.util.ArrayList;

public class ListExpensesDaoImpl implements ListExpensesDao {

    private final String nameTable2 = "receivers_task7";
    private final String nameTable1 = "expenses_task7";
    private final Connection conn;

    public ListExpensesDaoImpl(Connection connection) {
        this.conn = connection;
    }

    @Override
    public long addReceiver(Receiver receiver) {
        long idReceiver = 1;
        String nameReceiver = receiver.getReceiver();

        String sqlInsertReceivers = "INSERT INTO " + nameTable2 +
                " (receiver)" +
                "VALUE ('" + nameReceiver + "');";

        try (Statement statement = conn.createStatement()) {
            ResultSet resultReceivers = statement.executeQuery("SELECT * FROM " + nameTable2 + ";");
            while (resultReceivers.next()) {
                if (resultReceivers.getString(2).equalsIgnoreCase(nameReceiver)) {
                    idReceiver = resultReceivers.getLong(1);
                    resultReceivers.close();
                    break;
                }
                idReceiver = resultReceivers.getLong(1) + 1;
            }
            if (!resultReceivers.isClosed()) {
                statement.executeUpdate(sqlInsertReceivers);
                resultReceivers.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idReceiver;
    }

    @Override
    public void addExpense(Expense expense) {

        String paydate = expense.getDate();
        long receiverId = addReceiver(expense.getReceiver());
        double value = expense.getValue();


        String sqlInsertExpenses = "INSERT INTO " + nameTable1 +
                " (paydate,receiver,value)" +
                "VALUES ('" + paydate + "'," + receiverId + "," + value + ");";
        /*String sqlInsertExpenses2 = "INSERT INTO " + nameTable1 +
                " (paydate,value)" +
                "VALUES ('" + paydate + "'," + value + ");";
*/
        try ( Statement statement = conn.createStatement()) {
            statement.executeUpdate(sqlInsertExpenses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense>expenses = new ArrayList<Expense>();
        String sqlSelectExpenses="select * from "+nameTable1;

        try (Statement statement = conn.createStatement()) {
        ResultSet resultSet=statement.executeQuery(sqlSelectExpenses);
        while(resultSet.next()) {
            long id = resultSet.getLong(1);
            String payDate = resultSet.getString(2);
            long receiverId=resultSet.getLong(3);
            double value=resultSet.getDouble(4);
            expenses.add(new Expense(id, payDate, receiverId,value));
        }
        resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return expenses;
    }

    @Override
    public Expense getExpense(long id) {
        String sqlSelectExpense="select * from "+nameTable1+" where id="+id;
        Expense expense;
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet=statement.executeQuery(sqlSelectExpense);
            resultSet.next();
                long idExpense = resultSet.getLong(1);
                String payDate = resultSet.getString(2);
                long receiverId=resultSet.getLong(3);
                double value=resultSet.getDouble(4);
                expense=new Expense(idExpense, payDate, receiverId,value);
                resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return expense;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        ArrayList<Receiver>receivers = new ArrayList<Receiver>();
        String sqlSelectReceivers="select * from "+nameTable2;

        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet=statement.executeQuery(sqlSelectReceivers);
            while(resultSet.next()) {
                long id = resultSet.getLong(1);
                String nameReceiver = resultSet.getString(2);
                receivers.add(new Receiver(id,nameReceiver));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receivers;
    }

    @Override
    public Receiver getReceiver(long id) {
        String sqlSelectReceiver="select * from "+nameTable2+" where id="+id;
        Receiver receiver;
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet=statement.executeQuery(sqlSelectReceiver);
            resultSet.next();
            long idReceiver = resultSet.getLong(1);
            String nameReceiver = resultSet.getString(2);

            receiver=new Receiver(idReceiver,nameReceiver);
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receiver;
    }

   /* public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ListExpensesDao listExpensesDao = new ListExpensesDaoImpl(DataSource.getConnection());
        Receiver receiver = new Receiver(0,"Bigz");
        receiver.setId(1);
        Expense expense = new Expense(1,"2023.12.31", receiver.getId(), 1148757.56);
        expense.setReceiver(receiver);
        //listExpensesDao.addReceiver(receiver);
        listExpensesDao.addExpense(expense);

        ArrayList<Expense>expenses=listExpensesDao.getExpenses();
        for(Expense ex:expenses){
            System.out.println(ex.toString());
        }
        System.out.println("__________________");

        System.out.println(listExpensesDao.getExpense(1));
        System.out.println("__________________");

        ArrayList<Receiver>receivers=listExpensesDao.getReceivers();
        for(Receiver rec:receivers){
            System.out.println(rec.toString());
        }

        System.out.println("__________________");

        System.out.println(listExpensesDao.getReceiver(1));
    }*/


}

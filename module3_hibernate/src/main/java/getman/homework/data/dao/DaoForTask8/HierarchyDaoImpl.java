package getman.homework.data.dao.DaoForTask8;

import getman.homework.data.pojo.forTask8.perSubclass.Employee;
import getman.homework.data.pojo.forTask8.perSubclass.Engineer;
import getman.homework.data.pojo.forTask8.perSubclass.Manager;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountMasterSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountVisaSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.ClientSingleTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HierarchyDaoImpl implements HierarchyDao {
    SessionFactory sessionFactory;

    public HierarchyDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) throw new IllegalStateException("sessionFactory cannot be null");
        this.sessionFactory = sessionFactory;
    }

    public String saveSingleTable(Object object) {
        if (object instanceof ClientSingleTable) {
            Session session = null;
            Transaction transaction = null;
            String saveId;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                saveId = (String) session.save(object);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException(e);
            } finally {
                if (session != null) session.close();
            }

            return saveId;
        }
        throw new IllegalStateException("invalid method arguments");
    }

    public ClientSingleTable getClientById(String id) {
        Session session = null;
        Transaction transaction = null;
        ClientSingleTable client;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            client = session.get(ClientSingleTable.class, id);
                transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return client;
    }
    public BankAccountVisaSingleTable getVisaClientById(String id) {
        Session session = null;
        Transaction transaction = null;
        BankAccountVisaSingleTable bankAccountVisaSingleTable;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            bankAccountVisaSingleTable= session.get(BankAccountVisaSingleTable.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return bankAccountVisaSingleTable;
    }
    public BankAccountMasterSingleTable getMasterClientById(String id) {
        Session session = null;
        Transaction transaction = null;
        BankAccountMasterSingleTable master;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            master= session.get(BankAccountMasterSingleTable.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return master;
    }
    public String savePerSubclass(Object object) {
        if (object instanceof Employee) {
            Session session = null;
            Transaction transaction = null;
            String saveId;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                saveId = (String) session.save(object);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException(e);
            } finally {
                if (session != null) session.close();
            }

            return saveId;
        }
        throw new IllegalStateException("invalid method arguments");
    }
    public Employee getEmployeeById(String id) {
        Session session = null;
        Transaction transaction = null;
        Employee employee;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            employee = session.get(Employee.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return employee;
    }
    public Engineer getEngineerById(String id) {
        Session session = null;
        Transaction transaction = null;
        Engineer engineer;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            engineer = session.get(Engineer.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return engineer;
    }
    public Manager getManagerById(String id) {
        Session session = null;
        Transaction transaction = null;
        Manager manager;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            manager = session.get(Manager.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return manager;
    }
}

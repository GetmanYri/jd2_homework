package getman.homework.data.dao.DaoForTask2_3;

import getman.homework.data.pojo.forTask2_3.BankAccount;
import getman.homework.data.pojo.forTask2_3.Person;
import org.hibernate.*;

public class PersonDaoImp implements PersonDao {
    SessionFactory sessionFactory;

    public PersonDaoImp(SessionFactory sessionFactory) {
        if (sessionFactory == null) throw new IllegalStateException("sessionFactory cannot be null");
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String save(Person person) {
        Session session = null;
        Transaction transaction = null;
        String saveId;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            saveId = (String) session.save(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return saveId;
    }

    public String save(BankAccount bankAccount) {
        Session session = null;
        Transaction transaction = null;
        String saveId;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            saveId = (String) session.save(bankAccount);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return saveId;
    }

    @Override
    public boolean deletePerson(String id) {
        Session session = null;
        Transaction transaction = null;
        Person person;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            person = session.get(Person.class, id);

            if (person == null) {
                return false;
            }
            session.remove(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return true;
    }

    @Override
    public Person getPersonById(String id) {
        Session session = null;
        Transaction transaction = null;
        Person person;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            person = session.get(Person.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return person;
    }

    @Override
    public Person getPersonWithBankAccountById(String id) {
        Session session = null;
        Transaction transaction = null;
        Person person;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            person = session.get(Person.class, id);
            session.refresh(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return person;
    }

    public Person loadPersonById(String id) {
        Session session = null;
        Transaction transaction = null;
        Person person;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            person = session.load(Person.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return person;
    }


    @Override
    public void savePersonWithId(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.replicate(person, ReplicationMode.OVERWRITE);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void saveBankAccountWithId(BankAccount bankAccount) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.replicate(bankAccount, ReplicationMode.IGNORE);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Person refreshPerson(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.refresh(person);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return person;
    }


    public void saveFlushManual(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {

            session = sessionFactory.openSession();
            session.setHibernateFlushMode(FlushMode.MANUAL);

            transaction = session.beginTransaction();
            session.save(person);
            session.flush(); //insert into PERSON (AGE, NAME, SURNAME, PERSON_ID) values (?, ?, ?, ?)
            person.setName("John");
            session.flush();//update PERSON set AGE=?, NAME=?, SURNAME=? where PERSON_ID=?
            session.clear();


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
    }
}



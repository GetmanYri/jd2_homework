package getman.homework.data.dao;

import getman.homework.data.pojo.BankAccount;
import getman.homework.data.pojo.Person;
import getman.homework.data.util.HibernateSessionFactory;
import org.hibernate.*;
import org.hibernate.id.uuid.StandardRandomStrategy;

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
        String saveId = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            saveId = (String) session.save(person);
            //session.saveOrUpdate(person);
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
        String saveId = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            saveId = (String) session.save(bankAccount);
            //session.saveOrUpdate(person);
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
            //session.saveOrUpdate(person);
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
            session.refresh(person);
            //session.saveOrUpdate(person);
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
        String saveId = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.replicate(person, ReplicationMode.IGNORE);

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

    public void saveFlushManual(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {

            session = sessionFactory.openSession();
            session.setHibernateFlushMode(FlushMode.MANUAL);

            transaction = session.beginTransaction();
            session.save(person);
            session.flush(); //insert into PERSON (AGE, NAME, SURNAME, PERSON_ID) values (?, ?, ?, ?)
            person.setAge(13);
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


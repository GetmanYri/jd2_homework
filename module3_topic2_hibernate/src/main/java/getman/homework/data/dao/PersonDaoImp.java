package getman.homework.data.dao;

import getman.homework.data.pojo.Person;
import getman.homework.data.util.HibernateSessionFactory;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.id.uuid.StandardRandomStrategy;

public class PersonDaoImp implements PersonDao {
    SessionFactory sessionFactory;

    public PersonDaoImp(SessionFactory sessionFactory) {
        if (sessionFactory == null) throw new IllegalStateException("sessionFactory cannot be null");
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String savePerson(Person person) {
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

            session.delete(person);
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
    public void savePersonById(Person person) {
        Session session = null;
        Transaction transaction = null;
        String saveId = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.replicate(person, ReplicationMode.EXCEPTION);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
    }
}


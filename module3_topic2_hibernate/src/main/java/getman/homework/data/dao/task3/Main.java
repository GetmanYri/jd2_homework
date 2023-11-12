package getman.homework.data.dao.task3;

import getman.homework.data.dao.PersonDao;
import getman.homework.data.dao.PersonDaoImp;
import getman.homework.data.pojo.Person;
import getman.homework.data.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
       /* String id="156";
        PersonDao personDao =new PersonDaoImp(HibernateSessionFactory.getSessionFactory());
        Person person=new Person(id,32,"xxxxxx","hhhh");

        personDao.saveFlushManual(person);
personDao.savePersonWithId(person);
Person person1=personDao.loadPersonById(id);

        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //session.update(loadPerson);
            session.refresh(person1);
            System.out.println(person1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }*/

       /* Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try {

            session = sessionFactory.openSession();
            session.setHibernateFlushMode(FlushMode.MANUAL);
            System.out.println("1-"+session.isDirty());
            transaction = session.beginTransaction();
            System.out.println("1-"+session.isDirty());

            session.save(person);
            session.flush();
            person.setAge(13);
            session.flush();

            //session.saveOrUpdate(person);
            System.out.println("2-"+session.isDirty());

            System.out.println("con-"+session.contains(person));
            System.out.println("3-"+session.isDirty());


            //transaction.commit();
            System.out.println("con-"+session.contains(person));
            System.out.println("4-"+session.isDirty());
            session.clear();
            System.out.println("con-"+session.contains(person));
            System.out.println("4-"+session.isDirty());

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }*/

    }


}

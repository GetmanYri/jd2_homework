package getman.homework.data.task2;

import getman.homework.data.dao.PersonDao;
import getman.homework.data.dao.PersonDaoImp;
import getman.homework.data.pojo.BankAccount;
import getman.homework.data.pojo.Person;
import getman.homework.data.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PracticalTask {
    private final static PersonDao personDao = new PersonDaoImp(HibernateSessionFactory.getSessionFactory());

    public static void main(String[] args) {
        Person person = new Person("464", 45, "Дмитрий", "Егоров");
        BankAccount bankAccount = new BankAccount("84", 4468);
        Person loadPerson;
        Person getPerson;
        bankAccount.setPerson(person);
        personDao.savePersonWithId(person);
        personDao.saveBankAccountWithId(bankAccount);

        loadPerson = personDao.loadPersonById("464");
        getPerson = personDao.getPersonById("464");

        System.out.println("-------------" + getPerson + "-----------------");
        try {
            System.out.println(loadPerson);
        } catch (Exception e) {
            System.out.println("--------------It's proxy " + e.getMessage() + "---------------");
        }


        Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            //session.update(loadPerson);
            session.refresh(loadPerson);
            System.out.println(loadPerson);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }


        Person loadPerson2 = personDao.loadPersonById("555");
        Person getPerson2 = personDao.getPersonById("555");

        System.out.println(getPerson2);

         sessionFactory = HibernateSessionFactory.getSessionFactory();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.refresh(loadPerson2);
            System.out.println(loadPerson2);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("-----------"+e.getMessage());
            if (transaction != null) transaction.rollback();
            //throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }




    }
}

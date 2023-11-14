package getman.homework.data.task2;



public class PracticalTask {

    //Practical tasks in PersonDaoImpTest

}

















   /* private final static PersonDao personDao = new PersonDaoImp(HibernateSessionFactory.getSessionFactory());

    public static void main(String[] args) {

        *//*Person person = new Person("464", 45, "Дмитрий", "Егоров");
        BankAccount bankAccount = new BankAccount("84", 4468);
        Person loadPerson;
        Person getPerson;
        bankAccount.setPerson(person);
        personDao.savePersonWithId(person);
        personDao.saveBankAccountWithId(bankAccount);

        loadPerson = personDao.loadPersonById("464");
        getPerson = personDao.getPersonById("464");*//*

        //person.setName("Tom");
       // personDao.savePersonWithId(person);


        *//*System.out.println("-------------" + getPerson + "-----------------");
        try {
            System.out.println(loadPerson);
        } catch (Exception e) {
            System.out.println("--------------It's proxy " + e.getMessage() + "---------------");
        }

*//*
        //System.out.println(getPerson.getName());
       // personDao.refreshPerson(getPerson);
        ///System.out.println(getPerson.getName());
        *//*Session session = null;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(loadPerson);*//*
            //session.update(loadPerson);
            //session.refresh(loadPerson);
            //session.refresh(getPerson);
//session.flush();
            //loadPerson.getBankAccounts();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        //System.out.println(getPerson.getName());
        System.out.println(loadPerson);
        //System.out.println(loadPerson.getBankAccounts());

    }*/


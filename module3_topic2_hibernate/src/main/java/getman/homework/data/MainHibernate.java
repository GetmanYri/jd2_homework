package getman.homework.data;

import getman.homework.data.dao.PersonDao;
import getman.homework.data.dao.PersonDaoImp;
import getman.homework.data.pojo.Person;
import getman.homework.data.util.HibernateSessionFactory;

public class MainHibernate {

    public static void main(String[] args) throws ClassNotFoundException {

        PersonDao personDao=new PersonDaoImp(HibernateSessionFactory.getSessionFactory());
        personDao.savePerson(new Person(null,45,"Jack","Oliver"));
        personDao.savePersonById(new Person("157",45,"Jack","Oliver"));
        personDao.savePersonById(new Person("158",34,"Max","Jackson"));
        Person person =personDao.getPersonById("157");
        Person person2 =personDao.loadPersonById("158");

        person.getName();
        person2.getName();  //Exception LazyInitializationException


        //System.out.println(person);
        //System.out.println(person2);
    }

}

package getman.homework;

import getman.homework.data.pojo.Person;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.io.File;

public class ExampleJPA {


    // This class is for training jpa
    public static void savePersonJPA(Person person){
        EntityTransaction eTransaction;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getman.homework");

        EntityManager entityManager = emf.createEntityManager();

        eTransaction=entityManager.getTransaction();
        eTransaction.begin();
        entityManager.persist(person);
        eTransaction.commit();

    }

    public static void main(String[] args) {
        Person person = new Person(null,45,"Yury","Houp");
        savePersonJPA(person);
    }
}
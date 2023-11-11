package getman.homework.data.dao;

import getman.homework.data.util.HibernateTestSessionFactory;
import getman.homework.data.pojo.Person;
import getman.homework.data.util.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class PersonDaoImpTest {
    PersonDao personDao;


    @Before
    public void setUp() throws Exception {
        personDao = new PersonDaoImp(HibernateTestSessionFactory.getSessionFactory());
        /*Connection connection = DataSource.getConnection();
        connection.createStatement().execute("Truncate table person");
        connection.close();*/
    }

    @After
    public void tearDown() throws Exception {

        personDao=null;
        Connection connection = DataSource.getConnection();
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().execute("truncate table bankaccount");

        connection.createStatement().execute("truncate table person");
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }

    @Test
    public void savePerson() throws Exception {
        Person savePerson = new Person(null, 23, "John", "Smith");
        Person readPerson = null;
        int count;

        personDao.save(savePerson);
        Connection connection = DataSource.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(" SELECT * from person");
        while (resultSet.next()) {
            Integer age = resultSet.getInt("age");
            String name = resultSet.getString("name");
            String surName = resultSet.getString("surName");
            readPerson = new Person(null, age, name, surName);
        }
        resultSet.close();

        resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);
        assertNotNull(readPerson);
        assertEquals(23, (long) readPerson.getAge());
        assertEquals("John", readPerson.getName());
        assertEquals("Smith", readPerson.getSurname());
        connection.close();
    }

    @Test
    public void deletePerson() throws Exception {

        String id = UUID.randomUUID().toString();
        int count;
        boolean successful;
        Connection connection = DataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO `hibernateNADB_test`.`person`\n" +
                "(`PERSON_ID`,\n" +
                "`AGE`,\n" +
                "`NAME`,\n" +
                "`SURNAME`)\n" +
                "VALUES\n" +
                "('" + id + "',\n" +
                "25,\n" +
                "'Max',\n" +
                "'Von');");

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        successful = personDao.deletePerson(id);

        assertTrue(successful);
        resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 0);
        connection.close();
    }

    @Test
    public void getPersonById() throws Exception {
        String id = UUID.randomUUID().toString();
        int count;
        Person person;
        Connection connection = DataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO `hibernateNADB_test`.`person`\n" +
                "(`PERSON_ID`,\n" +
                "`AGE`,\n" +
                "`NAME`,\n" +
                "`SURNAME`)\n" +
                "VALUES\n" +
                "('" + id + "',\n" +
                "25,\n" +
                "'Max',\n" +
                "'Von');");

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        person = personDao.getPersonById(id);
        assertNotNull(person);


        assertEquals(25, (long) person.getAge());
        assertEquals("Max", person.getName());
        assertEquals("Von", person.getSurname());
        connection.close();
    }
}
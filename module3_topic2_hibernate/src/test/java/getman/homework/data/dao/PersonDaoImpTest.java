package getman.homework.data.dao;

import getman.homework.data.pojo.BankAccount;
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
    Connection connection;

    @Before
    public void setUp() throws Exception {
        personDao = new PersonDaoImp(HibernateTestSessionFactory.getSessionFactory());
        connection = DataSource.getConnection();
        /*Connection connection = DataSource.getConnection();
        connection.createStatement().execute("Truncate table person");
        connection.close();*/
    }

    @After
    public void tearDown() throws Exception {

        personDao = null;
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS = 0;");
        connection.createStatement().execute("truncate table bankaccount");

        connection.createStatement().execute("truncate table person");
        connection.createStatement().execute("SET FOREIGN_KEY_CHECKS = 1;");
        connection.close();
    }

    @Test
    public void testSavePerson() throws Exception {

        Person savePerson = new Person(null, 23, "John", "testSavePerson");
        Person readPerson = null;
        int count;
        String savedId = null;

        savedId = personDao.save(savePerson);

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

        assertNotNull(savedId);
        assertEquals(count, 1);
        assertNotNull(readPerson);
        assertEquals(23, (long) readPerson.getAge());
        assertEquals("John", readPerson.getName());
        assertEquals("testSavePerson", readPerson.getSurname());
    }

    @Test
    public void testSaveBankAccount() throws Exception {
        BankAccount bankAccount = new BankAccount(null, 1234);
        BankAccount readBankAccount = null;
        int count;
        String savedId = null;

        savedId = personDao.save(bankAccount);

        ResultSet resultSet = connection.createStatement().executeQuery(" SELECT * from bankAccount");
        while (resultSet.next()) {
            long account = resultSet.getLong(2);
            readBankAccount = new BankAccount(null, account);
        }
        resultSet.close();

        resultSet = connection.createStatement().executeQuery("Select count(*) from bankAccount");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();

        assertNotNull(savedId);
        assertEquals(count, 1);
        assertNotNull(readBankAccount);
        assertEquals(1234, readBankAccount.getAccountNumber());
    }

    @Test
    public void testDeletePerson() throws Exception {
        String id = UUID.randomUUID().toString();
        int count;
        boolean successful;

        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'testDeletePerson');"
        );

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
    }

    @Test
    public void testGetPersonById() throws Exception {
        String id = UUID.randomUUID().toString();
        int count;
        Person person;
        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'testGetPersonById');"
        );

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        person = personDao.getPersonById(id);
        assertNotNull(person);


        assertEquals(25, (long) person.getAge());
        assertEquals("Max", person.getName());
        assertEquals("testGetPersonById", person.getSurname());
    }

    @Test
    public void testGetPersonWithBankAccountById() throws Exception {
        Person person;
        String idPerson = UUID.randomUUID().toString();
        String idBankAccount = UUID.randomUUID().toString();

        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + idPerson + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'testGetPersonWithBankAccountById');"
        );
        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`bankAccount`\n" +
                        "(`ACCOUNT_ID`,\n" +
                        "`ACCOUNT_NUM`,\n" +
                        "`PERSON_ID`)\n" +
                        "VALUES\n" +
                        "('" + idBankAccount + "',\n" +
                        "215,\n" +
                        "'" + idPerson + "');"
        );
        person = personDao.getPersonWithBankAccountById(idPerson);

        assertNotNull(person);
        assertEquals(person.getBankAccounts().get(0).getAccountNumber(), 215);
        assertEquals("testGetPersonWithBankAccountById", person.getSurname());
    }

    @Test
    public void testGetPersonByNonExistentId() throws Exception {

        // Test for practical task 2

        String id = UUID.randomUUID().toString();
        String nonExistentId = UUID.randomUUID().toString();
        int count;
        Person person;
        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'testGetPersonByNonExistentId');"
        );

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        person = personDao.getPersonById(nonExistentId);
        assertNull(person);
    }


    @Test
    public void testLoadPersonById() throws Exception {
        String id = UUID.randomUUID().toString();
        int count;
        Person person;
        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'TestLoadPersonById');"
        );

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        person = personDao.loadPersonById(id);
        assertNotNull(person);

        try {
            assertEquals(25, (long) person.getAge());
            assertEquals("Max", person.getName());
            assertEquals("TestLoadPersonById", person.getSurname());
        } catch (Exception e) {
            System.out.println(
                    "It's proxy object. To synchronize with the database, " +
                            "you need to open a session and apply session.refresh(object) method. Exception: " +
                            e.getMessage()
            );

        }
    }


    @Test
    public void testLoadPersonByNonExistentId() throws Exception {

        // Test for practical task 2

        String id = UUID.randomUUID().toString();
        String nonExistentId = UUID.randomUUID().toString();
        int count;
        Person person;
        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'testLoadPersonByNonExistentId');"
        );

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        person = personDao.loadPersonById(nonExistentId);

        assertNotNull(person);

        try {
            assertEquals(25, (long) person.getAge());
            assertEquals("Max", person.getName());
            assertEquals("testLoadPersonByNonExistentId", person.getSurname());
        } catch (Exception e) {
            System.out.println(
                    "It's proxy object. To synchronize with the database, " +
                            "you need to open a session and apply session.refresh(object) method. Exception: " +
                            e.getMessage()
            );
        }
    }


    @Test
    public void testSavePersonWithId() throws Exception {
        String id = UUID.randomUUID().toString();
        Person savePerson = new Person(id, 23, "John", "testSavePersonWithId");
        Person readPerson = null;
        int count;

        personDao.savePersonWithId(savePerson);

        ResultSet resultSet = connection.createStatement().executeQuery(" SELECT * from person");
        while (resultSet.next()) {
            String readId = resultSet.getString("person_id");
            Integer age = resultSet.getInt("age");
            String name = resultSet.getString("name");
            String surName = resultSet.getString("surName");
            readPerson = new Person(readId, age, name, surName);
        }
        resultSet.close();

        resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();

        assertEquals(count, 1);
        assertNotNull(readPerson);
        assertEquals(id, readPerson.getId());
        assertEquals(23, (long) readPerson.getAge());
        assertEquals("John", readPerson.getName());
        assertEquals("testSavePersonWithId", readPerson.getSurname());
    }

    @Test
    public void testSaveBankAccountWithId() throws Exception {
        String id = UUID.randomUUID().toString();
        BankAccount bankAccount = new BankAccount(id, 1234);
        BankAccount readBankAccount = null;
        int count;

        personDao.saveBankAccountWithId(bankAccount);

        ResultSet resultSet = connection.createStatement().executeQuery(" SELECT * from bankaccount");
        while (resultSet.next()) {
            String readId = resultSet.getString("account_id");
            Long num = resultSet.getLong("account_num");
            readBankAccount = new BankAccount(readId, num);
        }
        resultSet.close();

        resultSet = connection.createStatement().executeQuery("Select count(*) from bankaccount");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();

        assertEquals(count, 1);
        assertNotNull(readBankAccount);
        assertEquals(id, readBankAccount.getId());
        assertEquals(1234, (long) readBankAccount.getAccountNumber());
    }

    @Test
    public void refreshPerson() throws Exception {
        String id = UUID.randomUUID().toString();
        int count;
        Person person = new Person(id, null, null, null);
        connection.createStatement().executeUpdate(
                "CREATE TRIGGER `access_level_trigger` BEFORE INSERT ON `person`\n" +
                        "            FOR EACH ROW BEGIN\n" +
                        "            IF NEW.AGE < 18 THEN\n" +
                        "            SET NEW.ACCESS_LEVEL = 1;\n" +
                        "            ELSEIF NEW.AGE > 18 THEN\n" +
                        "            SET NEW.ACCESS_LEVEL = 2;\n" +
                        "            END IF;\n" +
                        "            END;"
        );

        connection.createStatement().executeUpdate(
                "INSERT INTO `hibernateNADB_test`.`person`\n" +
                        "(`PERSON_ID`,\n" +
                        "`AGE`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "25,\n" +
                        "'Max',\n" +
                        "'refreshPerson');"
        );

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        person = personDao.refreshPerson(person);
        assertNotNull(person);


        assertEquals(25, (long) person.getAge());
        assertEquals("Max", person.getName());
        assertEquals("refreshPerson", person.getSurname());
        assertEquals(2, (long) person.getAccessLevel());

        connection.createStatement().executeUpdate(
                "DROP TRIGGER `access_level_trigger`;"
        );
    }


    @Test
    public void saveFlushManual() throws Exception {
        Person savePerson = new Person(null, 23, "Max", "saveFlushManual");
        Person readPerson = null;
        int count;

        personDao.saveFlushManual(savePerson);

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
        assertEquals("saveFlushManual", readPerson.getSurname());
    }

    @Test
    public void saveAndDeletePerson() throws Exception {

        //Test for task 3 practical task
        boolean successful;
        Person savePerson = new Person(null, 23, "John", "saveAndDeletePerson");
        Person readPerson = null;
        int saveCount;
        int deleteCount;
        String savedId = null;

        savedId = personDao.save(savePerson);

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
        saveCount = resultSet.getInt(1);
        resultSet.close();

        successful = personDao.deletePerson(savedId);

        resultSet = connection.createStatement().executeQuery("Select count(*) from Person");
        resultSet.next();
        deleteCount = resultSet.getInt(1);
        resultSet.close();

        assertEquals(deleteCount, 0);
        assertTrue(successful);
        assertNotNull(savedId);
        assertEquals(saveCount, 1);
        assertNotNull(readPerson);
        assertEquals(23, (long) readPerson.getAge());
        assertEquals("John", readPerson.getName());
        assertEquals("saveAndDeletePerson", readPerson.getSurname());
    }


}

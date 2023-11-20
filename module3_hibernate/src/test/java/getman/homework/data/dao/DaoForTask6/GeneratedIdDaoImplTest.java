package getman.homework.data.dao.DaoForTask6;

import getman.homework.data.pojo.forTask6.*;
import getman.homework.data.util.DataTestSource;
import getman.homework.data.util.HibernateTestSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class GeneratedIdDaoImplTest {
    GeneratedIdDao generatedIdDao;
    Connection conn;

    @Before
    public void setUp() throws Exception {
        generatedIdDao = new GeneratedIdDaoImpl(HibernateTestSessionFactory.getSessionFactory());
        conn = DataTestSource.getConnection();
        conn.createStatement().executeUpdate("TRUNCATE TABLE ID_UUID;");
        conn.createStatement().executeUpdate("TRUNCATE TABLE ID_HILO;");
        conn.createStatement().executeUpdate("TRUNCATE TABLE ID_GUID;");
        conn.createStatement().executeUpdate("TRUNCATE TABLE ID_SEQUENCE;");
        conn.createStatement().executeUpdate("TRUNCATE TABLE hibernate_sequence;");
        conn.createStatement().executeUpdate("TRUNCATE TABLE ID_IDENTITY;");
        conn.createStatement().executeUpdate("TRUNCATE TABLE hilo_sequence_generator;");

    }

    @After
    public void tearDown() throws Exception {
        generatedIdDao = null;
        conn.close();
    }

    @Test
    public void saveAndGetUUID() throws Exception {
        IdUuid uuid = new IdUuid("saveAndGetUUID");
        IdUuid uuid2 = new IdUuid("saveAndGetUUID2");
        String IdHibernate1;
        String IdHibernate2;
        String idJDBC1;
        String idJDBC2;

        IdHibernate1 = generatedIdDao.saveAndGetUUID(uuid);
        IdHibernate2 = generatedIdDao.saveAndGetUUID(uuid2);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM ID_UUID;");
        resultSet.next();
        idJDBC1 = resultSet.getString(1);
        resultSet.next();
        idJDBC2 = resultSet.getString(1);
        resultSet.close();

        assertNotNull(IdHibernate1);
        assertNotNull(IdHibernate2);
        assertEquals(IdHibernate1, idJDBC1);
        assertEquals(IdHibernate2, idJDBC2);
        assertNotEquals(idJDBC1, idJDBC2);

        System.out.println("UUID 1 - " + IdHibernate1);
        System.out.println("UUID 2 - " + IdHibernate2);
    }

    @Test
    public void saveAndGetHiLo() throws Exception {
        conn.createStatement().executeUpdate("INSERT INTO hilo_sequence_generator values(2);");
        IdHiLo idHiLo = new IdHiLo("saveAndGetHiLo");
        IdHiLo idHiLo2 = new IdHiLo("saveAndGetHiLo2");
        long idFirstSaveHibernate;
        long idSecondSaveHibernate;
        long idFirstSaveJDBC;
        long idSecondSaveJDBC;

        idFirstSaveHibernate = generatedIdDao.saveAndGetHiLo(idHiLo);
        idSecondSaveHibernate = generatedIdDao.saveAndGetHiLo(idHiLo2);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM ID_HILO");

        resultSet.next();
        idFirstSaveJDBC = resultSet.getLong(1);
        resultSet.next();
        idSecondSaveJDBC = resultSet.getLong(1);
        resultSet.close();

        assertEquals(idFirstSaveJDBC, 6);
        assertEquals(idSecondSaveJDBC, 7);
        assertEquals(idFirstSaveHibernate, idFirstSaveJDBC);
        assertEquals(idSecondSaveHibernate, idSecondSaveJDBC);

        System.out.println("Id 1 from ID_HILO - " + idFirstSaveJDBC);
        System.out.println("Id 2 from ID_HILO - " + idSecondSaveJDBC);

    }

    @Test
    public void saveAndGetIdentity() throws Exception {
        IdIdentity idIdentity1 = new IdIdentity("saveAndGetIdentity");
        IdIdentity idIdentity2 = new IdIdentity("saveAndGetIdentity2");
        long idFirstSaveHibernate;
        long idSecondSaveHibernate;
        long idFirstSaveJDBC;
        long idSecondSaveJDBC;

        idFirstSaveHibernate = generatedIdDao.saveAndGetIdentity(idIdentity1);
        idSecondSaveHibernate = generatedIdDao.saveAndGetIdentity(idIdentity2);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM ID_IDENTITY");

        resultSet.next();
        idFirstSaveJDBC = resultSet.getLong(1);
        resultSet.next();
        idSecondSaveJDBC = resultSet.getLong(1);
        resultSet.close();

        assertEquals(idFirstSaveJDBC, 1);
        assertEquals(idSecondSaveJDBC, 2);
        assertEquals(idFirstSaveHibernate, idFirstSaveJDBC);
        assertEquals(idSecondSaveHibernate, idSecondSaveJDBC);

        System.out.println("Id 1 from ID_IDENTITY - " + idFirstSaveJDBC);
        System.out.println("Id 2 from ID_IDENTITY - " + idSecondSaveJDBC);
    }

    @Test
    public void saveAndGetIdSequence() throws Exception {
        conn.createStatement().executeUpdate("INSERT INTO hibernate_sequence values(2);");
        IdSequence idSequence1 = new IdSequence("saveAndGetIdSequence1");
        IdSequence idSequence2 = new IdSequence("saveAndGetIdSequence2");
        long idFirstSaveHibernate;
        long idSecondSaveHibernate;
        long idFirstSaveJDBC;
        long idSecondSaveJDBC;

        idFirstSaveHibernate = generatedIdDao.saveAndGetIdSequence(idSequence1);
        idSecondSaveHibernate = generatedIdDao.saveAndGetIdSequence(idSequence2);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM ID_SEQUENCE");

        resultSet.next();
        idFirstSaveJDBC = resultSet.getLong(1);
        resultSet.next();
        idSecondSaveJDBC = resultSet.getLong(1);
        resultSet.close();

        assertEquals(idFirstSaveJDBC, 2);
        assertEquals(idSecondSaveJDBC, 3);
        assertEquals(idFirstSaveHibernate, idFirstSaveJDBC);
        assertEquals(idSecondSaveHibernate, idSecondSaveJDBC);

        System.out.println("Id 1 from ID_SEQUENCE - " + idFirstSaveJDBC);
        System.out.println("Id 2 from ID_SEQUENCE - " + idSecondSaveJDBC);
    }

    @Test
    public void testSaveAndGetIdGuid() throws Exception {
        IdGUID guid1 = new IdGUID("testSaveAndGetIdGuid1");
        IdGUID guid2 = new IdGUID("testSaveAndGetIdGuid2");
        String IdHibernate1;
        String IdHibernate2;
        String idJDBC1;
        String idJDBC2;


        IdHibernate1 = generatedIdDao.saveAndGetIdGuid(guid1);
        IdHibernate2 = generatedIdDao.saveAndGetIdGuid(guid2);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM ID_GUID;");
        resultSet.next();
        idJDBC1 = resultSet.getString(1);
        resultSet.next();
        idJDBC2 = resultSet.getString(1);
        resultSet.close();

        assertNotNull(IdHibernate1);
        assertNotNull(IdHibernate2);
        assertEquals(IdHibernate1, idJDBC1);
        assertEquals(IdHibernate2, idJDBC2);
        assertNotEquals(idJDBC1, idJDBC2);

        System.out.println("GUID 1 - " + IdHibernate1);
        System.out.println("GUID 2 - " + IdHibernate2);
    }
}
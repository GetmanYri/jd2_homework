package getman.homework.data.dao.DaoForTask8;

import getman.homework.data.pojo.forTask8.perSubclass.Employee;
import getman.homework.data.pojo.forTask8.perSubclass.Engineer;
import getman.homework.data.pojo.forTask8.perSubclass.Manager;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountMasterSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountVisaSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.ClientSingleTable;
import getman.homework.data.util.DataTestSource;
import getman.homework.data.util.HibernateTestSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.UUID;

import static org.junit.Assert.*;

public class HierarchyDaoImplTest {
    HierarchyDaoImpl hierarchyDao;
    Connection conn;

    @Before
    public void setUp() throws Exception {
        hierarchyDao = new HierarchyDaoImpl(HibernateTestSessionFactory.getSessionFactory());
        conn = DataTestSource.getConnection();
        conn.createStatement().executeUpdate(
                "TRUNCATE TABLE hierarchy_single_table;"
        );
        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS = 0;");
        conn.createStatement().execute("truncate table employee_sub");
        conn.createStatement().execute("truncate table engineer");
        conn.createStatement().execute("truncate table manager");

        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS = 1;");
    }

    @After
    public void tearDown() throws Exception {
        hierarchyDao = null;
        conn.close();
    }

    @Test
    public void saveSingleTable() throws Exception {
        ClientSingleTable client = new ClientSingleTable(
                null,
                12,
                "SaveClient",
                "SingleTable"
        );
        BankAccountMasterSingleTable master = new BankAccountMasterSingleTable(
                null,
                13,
                "SaveBankAccountMaster",
                "SingleTable",
                123456789
        );
        BankAccountVisaSingleTable visa = new BankAccountVisaSingleTable(
                null,
                14,
                "SaveBankAccountVisa",
                "SingleTable",
                123456798
        );
        hierarchyDao.saveSingleTable(client);
        hierarchyDao.saveSingleTable(master);
        hierarchyDao.saveSingleTable(visa);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT count(*) FROM hierarchy_single_table");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(3, actualCount);
        resultSet.close();
        resultSet = conn.createStatement().executeQuery(
                "SELECT " +
                        "PERSON_ID," +
                        "AGE," +
                        "NAME," +
                        "SURNAME," +
                        "ACCOUNT_MASTERCARD," +
                        "CARD_TYPE," +
                        "ACCOUNT_VISA " +
                        "FROM hierarchy_single_table " +
                        "WHERE P_TYPE='C';");

        while (resultSet.next()) {
            assertNotNull(resultSet.getString(1));
            assertEquals(12, resultSet.getInt(2));
            assertEquals("SaveClient", resultSet.getString(3));
            assertEquals("SingleTable", resultSet.getString(4));
            assertEquals(resultSet.getLong(5), 0);
            assertNull(resultSet.getString(6));
            assertEquals(resultSet.getLong(7), 0);
        }
        resultSet.close();

        resultSet = conn.createStatement().executeQuery(
                "SELECT " +
                        "PERSON_ID," +
                        "AGE," +
                        "NAME," +
                        "SURNAME," +
                        "ACCOUNT_MASTERCARD," +
                        "CARD_TYPE," +
                        "ACCOUNT_VISA " +
                        "FROM hierarchy_single_table " +
                        "WHERE P_TYPE='M';");

        while (resultSet.next()) {
            assertNotNull(resultSet.getString(1));
            assertEquals(13, resultSet.getInt(2));
            assertEquals("SaveBankAccountMaster", resultSet.getString(3));
            assertEquals("SingleTable", resultSet.getString(4));
            assertEquals(resultSet.getLong(5), 123456789);
            assertEquals(resultSet.getString(6), "MasterCard");
            assertEquals(resultSet.getLong(7), 0);
        }
        resultSet.close();

        resultSet = conn.createStatement().executeQuery(
                "SELECT " +
                        "PERSON_ID," +
                        "AGE," +
                        "NAME," +
                        "SURNAME," +
                        "ACCOUNT_MASTERCARD," +
                        "CARD_TYPE," +
                        "ACCOUNT_VISA " +
                        "FROM hierarchy_single_table " +
                        "WHERE P_TYPE='V';");

        while (resultSet.next()) {
            assertNotNull(resultSet.getString(1));
            assertEquals(14, resultSet.getInt(2));
            assertEquals("SaveBankAccountVisa", resultSet.getString(3));
            assertEquals("SingleTable", resultSet.getString(4));
            assertEquals(resultSet.getLong(5), 0);
            assertEquals(resultSet.getString(6), "Visa");
            assertEquals(resultSet.getLong(7), 123456798);
        }
        resultSet.close();

    }

    @Test
    public void getClientById() throws Exception {
        String id = UUID.randomUUID().toString();
        ClientSingleTable client;
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`hierarchy_single_table`" +
                        "(`P_TYPE`," +
                        "`PERSON_ID`," +
                        "`AGE`," +
                        "`NAME`," +
                        "`SURNAME`) " +
                        "VALUES " +
                        "('C'," +
                        "'" + id + "'," +
                        "15," +
                        "'getClientById'," +
                        "'Client');"
        );

        client = hierarchyDao.getClientById(id);

        assertEquals(id, client.getId());
        assertEquals(15, (long) client.getAge());
        assertEquals("getClientById", client.getName());
        assertEquals("Client", client.getSurname());

    }

    @Test
    public void getVisaClientById() throws Exception {
        String id = UUID.randomUUID().toString();
        BankAccountVisaSingleTable visa;
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`hierarchy_single_table`" +
                        "(`P_TYPE`," +
                        "`PERSON_ID`," +
                        "`AGE`," +
                        "`NAME`," +
                        "`CARD_TYPE`," +
                        "`ACCOUNT_VISA`," +
                        "`SURNAME`) " +
                        "VALUES " +
                        "('V'," +
                        "'" + id + "'," +
                        "15," +
                        "'getVisaClientById'," +
                        "'Visa'," +
                        "1561446," +
                        "'Visa');"
        );

        visa = hierarchyDao.getVisaClientById(id);

        assertEquals(id, visa.getId());
        assertEquals(15, (long) visa.getAge());
        assertEquals("getVisaClientById", visa.getName());
        assertEquals("Visa", visa.getSurname());
        assertEquals(1561446, visa.getAccountVisa());
    }

    @Test
    public void getMasterClientById() throws Exception {
        String id = UUID.randomUUID().toString();
        BankAccountMasterSingleTable master;
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`hierarchy_single_table`" +
                        "(`P_TYPE`," +
                        "`PERSON_ID`," +
                        "`AGE`," +
                        "`NAME`," +
                        "`CARD_TYPE`," +
                        "`ACCOUNT_MASTERCARD`," +
                        "`SURNAME`) " +
                        "VALUES " +
                        "('M'," +
                        "'" + id + "'," +
                        "16," +
                        "'getMasterClientById'," +
                        "'Master'," +
                        "1561446," +
                        "'Master');"
        );

        master = hierarchyDao.getMasterClientById(id);

        assertEquals(id, master.getId());
        assertEquals(16, (long) master.getAge());
        assertEquals("getMasterClientById", master.getName());
        assertEquals("Master", master.getSurname());
        assertEquals(1561446, master.getAccountMasterCard());
    }

    @Test
    public void saveSubclass() throws Exception {
        String idEngineer;
        String idManager;
        Employee employee = new Employee(null, "saveSubclass", "employee");
        Engineer engineer = new Engineer(
                null,
                "saveSubclass",
                "engineer",
                123,
                "builder"
        );
        Manager manager = new Manager(
                null,
                "saveSubclass",
                "manager",
                12364,
                "service"
        );


        hierarchyDao.savePerSubclass(employee);
        idEngineer = hierarchyDao.savePerSubclass(engineer);
        idManager = hierarchyDao.savePerSubclass(manager);

        ResultSet resultSet = conn.createStatement().executeQuery(
                "SELECT " +
                        "EMPLOYEE_ID," +
                        "NAME," +
                        "SURNAME " +
                        "FROM employee_sub;"
        );

        resultSet.next();
        assertNotNull(resultSet.getString(1));
        assertEquals("saveSubclass", resultSet.getString(2));
        assertEquals("employee", resultSet.getString(3));

        resultSet.next();
        assertEquals(idEngineer, resultSet.getString(1));
        assertEquals("saveSubclass", resultSet.getString(2));
        assertEquals("engineer", resultSet.getString(3));

        resultSet.next();
        assertEquals(idManager, resultSet.getString(1));
        assertEquals("saveSubclass", resultSet.getString(2));
        assertEquals("manager", resultSet.getString(3));
        resultSet.close();

        resultSet = conn.createStatement().executeQuery(
                "SELECT " +
                        "EMPLOYEE_ID," +
                        "COST_PER_HOUR," +
                        "SPECIALIZATION " +
                        "FROM engineer;"
        );
        resultSet.next();
        assertEquals(idEngineer, resultSet.getString(1));
        assertEquals(123, resultSet.getInt(2));
        assertEquals("builder", resultSet.getString(3));
        resultSet.close();

        resultSet = conn.createStatement().executeQuery(
                "SELECT " +
                        "EMPLOYEE_ID," +
                        "PLAN," +
                        "DEPARTMENT " +
                        "FROM manager;"
        );
        resultSet.next();
        assertEquals(idManager, resultSet.getString(1));
        assertEquals(12364, resultSet.getInt(2));
        assertEquals("service", resultSet.getString(3));
        resultSet.close();

    }

    @Test
    public void getEmployeeById() throws Exception {
        String id = UUID.randomUUID().toString();
        Employee employee;
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`employee_sub`" +
                        "(`EMPLOYEE_ID`," +
                        "`NAME`," +
                        "`SURNAME`)" +
                        "VALUES " +
                        "('" + id + "'," +
                        "'getEmployeeById'," +
                        "'employee');"
        );

        employee = hierarchyDao.getEmployeeById(id);

        assertEquals(id, employee.getId());
        assertEquals("getEmployeeById", employee.getName());
        assertEquals("employee", employee.getSurName());
    }

    @Test
    public void getEngineerById() throws Exception {
        Engineer engineer;
        String id = UUID.randomUUID().toString();
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`employee_sub`" +
                        "(`EMPLOYEE_ID`," +
                        "`NAME`," +
                        "`SURNAME`)" +
                        "VALUES " +
                        "('" + id + "'," +
                        "'getEmployeeById'," +
                        "'employee');"
        );
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`engineer`\n" +
                        "(`COST_PER_HOUR`," +
                        "`SPECIALIZATION`," +
                        "`EMPLOYEE_ID`) " +
                        "VALUES " +
                        "(123," +
                        "'getEngineerById'," +
                        "'" + id + "');"
        );

        engineer = hierarchyDao.getEngineerById(id);

        assertEquals(123, engineer.getCostPerHour());
        assertEquals("getEngineerById", engineer.getSpecialization());
        assertEquals(id, engineer.getId());
        assertEquals("getEmployeeById", engineer.getName());
        assertEquals("employee", engineer.getSurName());
    }

    @Test
    public void getManagerById() throws Exception {
        Manager manager;
        String id = UUID.randomUUID().toString();
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`employee_sub`" +
                        "(`EMPLOYEE_ID`," +
                        "`NAME`," +
                        "`SURNAME`)" +
                        "VALUES " +
                        "('" + id + "'," +
                        "'getEmployeeById'," +
                        "'employee');"
        );
        conn.createStatement().executeUpdate(
                "INSERT INTO `hibernatenadb_test`.`manager`" +
                        "(`DEPARTMENT`," +
                        "`PLAN`," +
                        "`EMPLOYEE_ID`) " +
                        "VALUES " +
                        "('getManagerById'," +
                        "156," +
                        "'" + id + "');"
        );

        manager = hierarchyDao.getManagerById(id);
        assertEquals(156, manager.getSalesPlan());
        assertEquals("getManagerById", manager.getDepartment());
        assertEquals(id, manager.getId());
        assertEquals("getEmployeeById", manager.getName());
        assertEquals("employee", manager.getSurName());
    }
}
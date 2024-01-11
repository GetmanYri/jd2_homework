package getman.homework.task15.dao;

import getman.homework.task15.DataConfiguration;
import getman.homework.task15.TestDataSource;
import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.dto.UserDto;
import getman.homework.task15.pojo.Client;
import getman.homework.task15.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfiguration.class)
public class ClientDaoImplTest {
    @Autowired
    ClientDao clientDao;
    Connection connection;

    @org.junit.Before
    public void setUp() throws Exception {
        connection = TestDataSource.getConnection();
        connection.createStatement().execute("DELETE FROM T_CLIENT");
        connection.createStatement().execute("DELETE FROM T_USER");

    }

    @org.junit.After
    public void tearDown() throws Exception {
        connection.createStatement().execute("DELETE FROM T_CLIENT");
        connection.createStatement().execute("DELETE FROM T_USER");
        connection.close();
    }

    @org.junit.Test
    public void createClient() throws SQLException {
        ClientDto saveClient = new ClientDto(null, "John", "testSaveClient");
        UserDto saveUser = new UserDto(null, "user", "createClientTest");
        Client readClient = null;
        User readUser = null;
        int countClient;
        int countUser;
        String savedId;

        savedId = clientDao.createClient(saveClient, saveUser);

        ResultSet resultSet = connection.createStatement().executeQuery(" SELECT * from T_CLIENT");
        while (resultSet.next()) {
            String readId = resultSet.getString("client_id");
            String name = resultSet.getString("name");
            String surName = resultSet.getString("surName");
            String userId = resultSet.getString("user_id");
            readClient = new Client(readId, name, surName);
        }

        resultSet = connection.createStatement().executeQuery(" SELECT * from T_USER");
        while (resultSet.next()) {
            String readId = resultSet.getString("user_id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            readUser = new User(readId, login, password);
        }


        resultSet = connection.createStatement().executeQuery("Select count(*) from T_CLIENT");
        resultSet.next();
        countClient = resultSet.getInt(1);

        resultSet = connection.createStatement().executeQuery("Select count(*) from T_USER");
        resultSet.next();
        countUser = resultSet.getInt(1);
        resultSet.close();

        assertNotNull(savedId);
        assertEquals(countClient, 1);
        assertNotNull(readClient);
        assertEquals(savedId, readClient.getId());
        assertEquals("John", readClient.getName());
        assertEquals("testSaveClient", readClient.getSurname());

        assertEquals(countUser, 1);
        assertNotNull(readUser);
        assertEquals("user", readUser.getLogin());
        assertEquals("createClientTest", readUser.getPassword());

    }

    @org.junit.Test
    public void getClientById() throws SQLException {


        String id = UUID.randomUUID().toString();
        int count;
        ClientDto client;
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_CLIENT`\n" +
                        "(`CLIENT_ID`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`)\n" +
                        "VALUES\n" +
                        "('" + id + "',\n" +
                        "'Max',\n" +
                        "'testGetClientById');"
        );

        ResultSet resultSet = connection.createStatement().executeQuery("Select count(*) from T_CLIENT");
        resultSet.next();
        count = resultSet.getInt(1);
        resultSet.close();
        assertEquals(count, 1);

        client = clientDao.getClientById(id);
        assertNotNull(client);


        assertEquals("Max", client.getName());
        assertEquals("testGetClientById", client.getSurname());


    }

    @Test
    public void getClients() throws SQLException {


        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_USER`\n" +
                        "(`USER_ID`,\n" +
                        "`LOGIN`,\n" +
                        "`PASSWORD`)\n" +
                        "VALUES\n" +
                        "('" + userId + "',\n" +
                        "'user',\n" +
                        "'testGetClients');"
        );
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_USER`\n" +
                        "(`USER_ID`,\n" +
                        "`LOGIN`,\n" +
                        "`PASSWORD`)\n" +
                        "VALUES\n" +
                        "('" + userId2 + "',\n" +
                        "'user',\n" +
                        "'testGetClients');"
        );
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_CLIENT`\n" +
                        "(`CLIENT_ID`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`,\n" +
                        "`USER_ID`)\n" +
                        "VALUES\n" +
                        "('" + id1 + "',\n" +
                        "'Max',\n" +
                        "'testGetClients',\n" +
                        "'" + userId + "');"
        );
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_CLIENT`\n" +
                        "(`CLIENT_ID`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`,\n" +
                        "`USER_ID`)\n" +
                        "VALUES\n" +
                        "('" + id2 + "',\n" +
                        "'Alex',\n" +
                        "'testGetClients2',\n" +
                        "'" + userId2 + "');"
        );


        List<ClientDto> clients = clientDao.getClients();

        assertEquals(clients.size(), 2);
        assertEquals(clients.get(0).getUserDto().getLogin(), "user");
    }


    @Test
    public void updateClient() throws SQLException {
        String id1 = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        Client readClient = null;
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_USER`\n" +
                        "(`USER_ID`,\n" +
                        "`LOGIN`,\n" +
                        "`PASSWORD`)\n" +
                        "VALUES\n" +
                        "('" + userId + "',\n" +
                        "'user',\n" +
                        "'testGetClients');"
        );
        connection.createStatement().executeUpdate(
                "INSERT INTO `homework_spring_hibernate`.`T_CLIENT`\n" +
                        "(`CLIENT_ID`,\n" +
                        "`NAME`,\n" +
                        "`SURNAME`,\n" +
                        "`USER_ID`)\n" +
                        "VALUES\n" +
                        "('" + id1 + "',\n" +
                        "'Max',\n" +
                        "'Colin',\n" +
                        "'" + userId + "');"
        );

        clientDao.updateClient(new ClientDto(id1, "Test", "updateClientTest"));

        ResultSet resultSet = connection.createStatement().executeQuery(" SELECT * from T_CLIENT");
        while (resultSet.next()) {
            String readId = resultSet.getString("client_id");
            String name = resultSet.getString("name");
            String surName = resultSet.getString("surName");
            readClient = new Client(readId, name, surName);
        }
        assertNotNull(readClient);
        assertEquals(readClient.getId(), id1);
        assertEquals(readClient.getName(), "Test");
        assertEquals(readClient.getSurname(), "updateClientTest");
    }
}
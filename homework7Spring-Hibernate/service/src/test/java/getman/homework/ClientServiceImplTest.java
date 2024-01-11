package getman.homework;

import getman.homework.model.Client;
import getman.homework.model.User;
import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class ClientServiceImplTest {
    @Autowired
    ClientService clientService;


    @Test

    public void updateClient() {
       String id="111";
        assertNotNull(clientService);

        Client client = clientService.getClient(id);

        assertEquals(client.getId(), "111");
        assertEquals(client.getName(), "Max");
        assertEquals(client.getSurname(), "Mock1");

    }

}
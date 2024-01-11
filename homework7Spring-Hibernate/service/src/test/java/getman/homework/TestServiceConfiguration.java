package getman.homework;

import getman.homework.task15.dao.ClientDao;
import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.dto.UserDto;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

@Configuration
@Import(ServiceConfiguration.class)
public class TestServiceConfiguration {
    @Bean
    @Primary
    public ClientDao dao() {


        ClientDao mock = Mockito.mock(ClientDao.class);
        when(mock.getClientById("111")).thenReturn(
                new ClientDto("111", "Max", "Mock1")
                );
       return mock;

    }




}


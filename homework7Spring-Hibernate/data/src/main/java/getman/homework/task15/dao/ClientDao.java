package getman.homework.task15.dao;

import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.dto.UserDto;
import getman.homework.task15.pojo.Client;

import java.util.List;

public interface ClientDao {
    String createClient(ClientDto clientDto, UserDto userDto);
    ClientDto getClientById(String id);
    List<ClientDto> getClients();
     void updateClient(ClientDto clientDto);

}

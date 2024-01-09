package getman.homework.task15.dao;

import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.pojo.Client;

import java.util.List;

public interface ClientDao {
    String createClient(ClientDto clientDto);
    ClientDto getClientById(String id);
    List<ClientDto> getClients();

}

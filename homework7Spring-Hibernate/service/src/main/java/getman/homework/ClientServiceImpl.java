package getman.homework;

import getman.homework.model.Client;
import getman.homework.model.User;
import getman.homework.task15.dao.ClientDao;
import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao clientDao;

    @Transactional(readOnly = true)
    public Client getClient(String id) {
        ClientDto clientDto = clientDao.getClientById(id);

        return new Client(
                clientDto.getId(),
                clientDto.getName(),
                clientDto.getSurname()
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
   // @Override
    public void updateClient(Client client, User user) {

        List<ClientDto> clients = clientDao.getClients()
                .stream()
                .filter(clientDto -> clientDto.getUserDto().getLogin().equalsIgnoreCase(user.getLogin()))
                .toList();

        clients.get(0).setSurname(client.getSurname());

        clientDao.updateClient(clients.get(0));
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveNewClient(Client client, User user) {
        ClientDto clientDto = new ClientDto(
                client.getId(),
                client.getName(),
                client.getSurname()
        );
        UserDto userDto=new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword()
        );

        clientDao.createClient(clientDto, userDto);
    }
}



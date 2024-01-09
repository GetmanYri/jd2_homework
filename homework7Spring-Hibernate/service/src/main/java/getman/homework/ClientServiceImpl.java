package getman.homework;

import getman.homework.model.Client;
import getman.homework.task15.dao.ClientDao;
import getman.homework.task15.dto.ClientDto;
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

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public String idByName(String name) {

        List<ClientDto> clients = clientDao.getClients()
                .stream()
                .filter(clientDto -> clientDto.getName().equals(name))
                .toList();

        if (clients.size() == 0) {
            return null;
        }
        return clients.get(0).getId();

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveNewClient(Client client) {
        clientDao.createClient(new ClientDto(
                client.getId(),
                client.getName(),
                client.getSurname()
        ));
    }
}



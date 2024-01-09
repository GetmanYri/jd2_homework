package getman.homework;

import getman.homework.model.Client;

public interface ClientService {

    String idByName(String name);
    void saveNewClient(Client client);
    Client getClient(String id);
}

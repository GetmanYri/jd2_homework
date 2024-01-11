package getman.homework;

import getman.homework.model.Client;
import getman.homework.model.User;

public interface ClientService {

    //String idByName(String name);
    void saveNewClient(Client client, User user);
    Client getClient(String id);
    public void updateClient(Client client, User user);
}

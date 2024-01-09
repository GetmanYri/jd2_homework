package getman.homework.task15.dao;

import getman.homework.task15.dto.ClientDto;
import getman.homework.task15.pojo.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {
    SessionFactory sessionFactory;

    @Autowired
    public ClientDaoImpl(SessionFactory session) {
        this.sessionFactory = session;
    }

    @Override
    public String createClient(ClientDto clientDto) {
        String id;
        final Session session = sessionFactory.getCurrentSession();

        Client client = new Client(
                clientDto.getId(),
                clientDto.getName(),
                clientDto.getSurname()
        );
        id = (String) session.save(client);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto getClientById(String id) {
        final Session session = sessionFactory.getCurrentSession();
        Client client = session.get(Client.class, id);

        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getSurname()
        );
    }

    public List<ClientDto> getClients() {
        List<ClientDto> list;
        final Session session = sessionFactory.getCurrentSession();


        String hql = "FROM Client";
        Query<Client> query = session.createQuery(hql, Client.class);

        list = query.getResultList()
                .stream()
                .map(client -> new ClientDto(
                        client.getId(),
                        client.getName(),
                        client.getSurname()))
                .collect(Collectors.toList());


        return list;
    }


}

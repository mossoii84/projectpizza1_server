package projectpizza1_server.demo.serviceImpl.service;


import projectpizza1_server.demo.model.Client;
import java.util.List;

public interface ServiceClient {

    List<Client> findAll();
    Client findByName(String name);

    Client findById(Long id);
    Client save(Client client);
    void delete(Client client);

}

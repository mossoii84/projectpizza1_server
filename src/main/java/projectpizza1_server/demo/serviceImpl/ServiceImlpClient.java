package projectpizza1_server.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectpizza1_server.demo.model.Client;
import projectpizza1_server.demo.repository.RepositoryClient;
import projectpizza1_server.demo.serviceImpl.service.ServiceClient;
import java.util.List;


@Service
public class ServiceImlpClient implements ServiceClient {

    @Autowired
    private RepositoryClient repositoryClient;

    @Override
    public List<Client> findAll() { return repositoryClient.findAll(); }

    @Override
    public Client findByName(String name) { return repositoryClient.findByName(name).get(); }

    @Override
    public Client findById(Long id) { return repositoryClient.findById(id).get(); }

    @Override
    public Client save(Client client) { return repositoryClient.save(client); }

    @Override
    public void delete(Client client) { repositoryClient.delete(client); }
}

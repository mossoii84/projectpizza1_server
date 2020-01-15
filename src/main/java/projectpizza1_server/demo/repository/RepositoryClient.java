package projectpizza1_server.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectpizza1_server.demo.model.Client;

import java.util.Optional;

public interface RepositoryClient extends JpaRepository<Client,Long> {


    Optional<Client> findByName(String name);
}

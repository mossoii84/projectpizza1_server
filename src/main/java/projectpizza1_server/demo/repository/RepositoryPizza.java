package projectpizza1_server.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectpizza1_server.demo.model.Pizza;

public interface RepositoryPizza extends JpaRepository<Pizza,Long> {
}

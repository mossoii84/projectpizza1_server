package projectpizza1_server.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectpizza1_server.demo.model.Pizza;

@Repository
public interface ImageRepository extends JpaRepository<Pizza, Long> {
}
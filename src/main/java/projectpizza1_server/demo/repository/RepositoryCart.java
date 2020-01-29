package projectpizza1_server.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectpizza1_server.demo.model.Cart;

import java.util.List;

public interface RepositoryCart extends JpaRepository<Cart,Long> {

    void deleteInBatch(Iterable<Cart> carts);
}

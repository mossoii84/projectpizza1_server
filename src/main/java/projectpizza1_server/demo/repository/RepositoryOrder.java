package projectpizza1_server.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectpizza1_server.demo.model.Orders;

import java.util.List;

public interface RepositoryOrder extends  JpaRepository<Orders,Long> {


    //List<Orders> findAllByBuyIsFalse(); //keywords
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
}

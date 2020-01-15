package projectpizza1_server.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectpizza1_server.demo.model.Orders;
import projectpizza1_server.demo.repository.RepositoryOrder;
import projectpizza1_server.demo.serviceImpl.service.ServiceOrder;

import javax.persistence.criteria.Order;
import java.util.List;

@Service
public class ServiceImlpOrder implements ServiceOrder {

    @Autowired
    private RepositoryOrder repositoryOrder;

    @Override
    public List<Orders> findAll() {
        return repositoryOrder.findAll();
    }

    @Override
    public Orders findById(Long id) {
        return repositoryOrder.findById(id).get();
    }

    @Override
    public Orders save(Orders orders) {
        return repositoryOrder.save(orders);
    }

    @Override
    public void delete(Orders order) {
      repositoryOrder.delete(order);
    }
}

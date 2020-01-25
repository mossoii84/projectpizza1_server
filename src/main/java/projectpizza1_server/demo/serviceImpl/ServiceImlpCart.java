package projectpizza1_server.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectpizza1_server.demo.model.Cart;
import projectpizza1_server.demo.model.Pizza;
import projectpizza1_server.demo.repository.RepositoryCart;
import projectpizza1_server.demo.serviceImpl.service.ServiceCart;

import java.util.List;

@Service
public class ServiceImlpCart implements ServiceCart {

    @Autowired
    private RepositoryCart repositoryCart;


    @Override
    public List<Cart> findAll() {
        return repositoryCart.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return repositoryCart.findById(id).get(); }

    @Override
    public Cart save(Cart cart) {
        return repositoryCart.save(cart) ;
    }

    @Override
    public void delete(Cart cart) { repositoryCart.delete(cart);}

}

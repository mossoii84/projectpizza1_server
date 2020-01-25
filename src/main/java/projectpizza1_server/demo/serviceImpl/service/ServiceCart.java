package projectpizza1_server.demo.serviceImpl.service;

import projectpizza1_server.demo.model.Cart;
import projectpizza1_server.demo.model.Pizza;

import java.util.List;

public interface ServiceCart {
    List<Cart> findAll();
    Cart findById(Long id);
    Cart save(Cart cart);
    void delete(Cart cart);

}

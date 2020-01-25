package projectpizza1_server.demo.serviceImpl.service;

import projectpizza1_server.demo.model.Pizza;

import java.util.List;

public interface ServicePizza {

    List<Pizza> findAll();
    Pizza findById(Long id);
    Pizza save(Pizza pizza);
    void delete(Pizza pizza);
}

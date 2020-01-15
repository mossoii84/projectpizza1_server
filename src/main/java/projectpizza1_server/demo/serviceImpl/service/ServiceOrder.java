package projectpizza1_server.demo.serviceImpl.service;


import projectpizza1_server.demo.model.Orders;

import java.util.List;

public interface ServiceOrder {

    List<Orders> findAll();
    Orders findById(Long id);
    Orders save(Orders orders);
    void delete(Orders orders);
}

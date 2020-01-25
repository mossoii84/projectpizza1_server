package projectpizza1_server.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projectpizza1_server.demo.model.Cart;
import projectpizza1_server.demo.model.Client;
import projectpizza1_server.demo.model.Orders;
import projectpizza1_server.demo.repository.RepositoryClient;
import projectpizza1_server.demo.repository.RepositoryOrder;
import projectpizza1_server.demo.serviceImpl.service.ServiceCart;
import projectpizza1_server.demo.serviceImpl.service.ServiceOrder;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/order")
@CrossOrigin(maxAge = 3600)
public class ControllerOrder {

    private ServiceOrder serviceOrder;
    @Autowired
    private ServiceCart serviceCart;
    @Autowired
    private RepositoryClient repositoryClient;
    @Autowired
    private RepositoryOrder repositoryOrder;

    @Autowired
    public ControllerOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @GetMapping("/allOrder")
    public List<Orders> getAllOrders(Principal principal){
        Client client=repositoryClient.findByName(principal.getName()).get();
        List<Orders> orders=client.getOrdersList();
        serviceOrder.findAll();
        return  orders;}

    @PostMapping()
    public Orders creatOrder(@RequestBody Orders orders){
    return serviceOrder.save(orders);
    }


}

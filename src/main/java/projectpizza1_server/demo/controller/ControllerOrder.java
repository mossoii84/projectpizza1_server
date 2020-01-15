package projectpizza1_server.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projectpizza1_server.demo.model.Orders;
import projectpizza1_server.demo.repository.RepositoryOrder;

import java.util.List;

@RestController
@RequestMapping("api/order")
@CrossOrigin(maxAge = 3600)
public class ControllerOrder {

@Autowired
private RepositoryOrder repositoryOrder;


    @GetMapping("/orderby")
    public List<Orders> getAllOrders(){
    return repositoryOrder.findAll(); }


    @PostMapping()
    public Orders creatOrder(@RequestBody Orders orders){
    return repositoryOrder.save(orders);
    }




}

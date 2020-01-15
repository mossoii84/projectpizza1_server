package projectpizza1_server.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projectpizza1_server.demo.model.Client;
import projectpizza1_server.demo.model.Orders;
import projectpizza1_server.demo.model.Pizza;
import projectpizza1_server.demo.repository.RepositoryClient;
import projectpizza1_server.demo.repository.RepositoryOrder;
import projectpizza1_server.demo.repository.RepositoryPizza;


import javax.persistence.criteria.Order;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "/api")
public class ControllerPizza {


    private RepositoryClient repositoryClient;
    private RepositoryPizza repositoryPizza;
    private RepositoryOrder repositoryOrder;
    @Autowired
    public ControllerPizza(RepositoryClient repositoryClient, RepositoryPizza repositoryPizza, RepositoryOrder repositoryOrder) {
        this.repositoryClient = repositoryClient;
        this.repositoryPizza = repositoryPizza;
        this.repositoryOrder = repositoryOrder;
    }


    @GetMapping("/pizza")
    public List<Pizza> findAllPizza(){
        return repositoryPizza.findAll();
    }






        //Здесь так как у нас уже все есть в БД, мы получаем Id пиццы в c и дальше уже
        //добавляем ее в уже ордер и потом ордер в кастомер, для этого у нас есть привязка по @One...
        //Principal principal- мы получаем данные актуального пользователя(который уже зашел)
        @GetMapping(value = "/pizza/neworder/{id}") //получаем в Url id нашей пиццы при нажатии на кнопку
        public Pizza addPizzaToOrder(@PathVariable(value = "id") Long id, Principal principal)
        {
            Client client=repositoryClient.findByName(principal.getName()).get();

            Orders orders; //объявил экземпляр класса (чтобы мы могли где то это инициализировать)
            List<Orders> ordersBuy=client.getOrdersList();

            if(ordersBuy.size()!=0){
                orders=ordersBuy.get(ordersBuy.size()-1);
            }else{
                orders=new Orders();
            }

            Pizza pizza=repositoryPizza.findById(id).get();//находим пиццу по ID и ниже заносим
            orders.setClient(client);
            orders.getPizzaList().add(pizza);//короткий способ,добавить пиццу в ордер
            Orders savePizzaInOrders=repositoryOrder.save(orders);

            client.getOrdersList().add(savePizzaInOrders);
            repositoryClient.save(client); //сохраняем ордер в кастомере
            return pizza;
        }




//        //тут делаем если сами вводим id заказа
//        @GetMapping(value = "/pizza/toorder/{pizza_id}/{order_id}")
//        public Orders addPizzaToOrder(@PathVariable Long pizza_id,
//                                      @PathVariable Long order_id,
//                                      Principal principal){
//        Client client=repositoryClient.findByName(principal.getName()).get();
//        Orders order=repositoryOrder.findById(order_id).get();
//        Pizza pizza=repositoryPizza.findById(pizza_id).get();
//
//        order.getPizzaList().add(pizza);//в полученый ордер,добавляем в его строку пиццу
//           //это более длинный год этого - order.getPizzaList().add(pizza)
////           List<Pizza> orderPizzaList=order.getPizzaList();
////           orderPizzaList.add(pizza);
////           order.setPizzaList(orderPizzaList);
//
//        Orders saveOrders=repositoryOrder.save(order);
//        client.getOrdersList().add(saveOrders);
//          repositoryClient.save(client);
//           return saveOrders; //то что выведится в постмане на экран
//       }










//
//    @GetMapping("/pizza/{id}")
//    public Pizza getPizzaById(@PathVariable (value = "id") long id){
//       return repositoryPizza.findById(id).get();
//    }
//
//    @PostMapping("/pizza")
//    public Pizza getPostPizza(@RequestBody Pizza pizza){
//        return repositoryPizza.save(pizza);
//    }
//
//
//   @DeleteMapping("/pizza/{id}")
//    public void deleteByIdPizza(@PathVariable (value = "id") long id){
//        Pizza pizza=repositoryPizza.findById(id).get();
//       repositoryPizza.delete(pizza);
//   }


//    @GetMapping(value = "/pizza/neworder/{id}") //получаем в Url id нашей пиццы при нажатии на кнопку
//    public Pizza addPizzaToOrder(@PathVariable(value = "id") Long id, Principal principal)
//    {
//        Client client=repositoryClient.findByName(principal.getName()).get();
//        Orders orders=new Orders();
//        Pizza pizza=repositoryPizza.findById(id).get();//находим пиццу по ID и ниже заносим
//
//        orders.getPizzaList().add(pizza);//короткий способ,добавить пиццу в ордер
//
//        Orders savePizzaInOrders=repositoryOrder.save(orders);
//
//        client.getOrdersList().add(savePizzaInOrders);
//        repositoryClient.save(client); //сохраняем ордер в кастомере
//        return pizza;
//    }




}




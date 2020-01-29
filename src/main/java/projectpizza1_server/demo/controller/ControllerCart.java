package projectpizza1_server.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projectpizza1_server.demo.model.Cart;
import projectpizza1_server.demo.model.Client;
import projectpizza1_server.demo.model.Orders;
import projectpizza1_server.demo.model.Pizza;
import projectpizza1_server.demo.repository.RepositoryCart;
import projectpizza1_server.demo.repository.RepositoryClient;
import projectpizza1_server.demo.repository.RepositoryOrder;
import projectpizza1_server.demo.serviceImpl.service.ServiceCart;
import projectpizza1_server.demo.serviceImpl.service.ServicePizza;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/cart")
@CrossOrigin(maxAge = 3600)
public class ControllerCart {

    private ServiceCart serviceCart;
    private RepositoryClient repositoryClient;
    private RepositoryOrder repositoryOrder;
    private ServicePizza servicePizza;
    private RepositoryCart repositoryCart;
    @Autowired
    public ControllerCart(ServiceCart serviceCart,
                          RepositoryClient repositoryClient,
                          RepositoryOrder repositoryOrder,
                          ServicePizza servicePizza,
                          RepositoryCart repositoryCart
                          ){
        this.serviceCart = serviceCart;
        this.repositoryClient=repositoryClient;
        this.repositoryOrder=repositoryOrder;
        this.servicePizza=servicePizza;
        this.repositoryCart=repositoryCart;
    }

    //Добавляем Пиццу в Cart, для этого у нас есть зависимость @Many...
    //Principal principal- мы получаем данные актуального пользователя(который уже зашел)
        @PostMapping(value = "/creeOrder") //получаем в Url id нашей пиццы при нажатии на кнопку
        public Cart addPizzaToOrder(@RequestBody Cart carts, Principal principal)
        { //чтобы это работало нужно создать кнопку пост - оплатить в Cart
            Client client=repositoryClient.findByName(principal.getName()).get();
            Orders orders=new Orders();
            List<Cart> cartList=serviceCart.findAll();
            orders.setClient(client);
            orders.setCartList(cartList);
            orders.setPizzaList(carts.getPizzaList());
            //дата
            Date dateNow = new Date();
            orders.setDateCreatOrder(dateNow);

            carts.setOrders(orders);

            repositoryOrder.save(orders);
            repositoryClient.save(client); //сохраняем клиента с ордер и carts
            //удаляем корзину
            repositoryCart.deleteInBatch(cartList);
            return carts;
        }

        // Получить список корзины по Клиенты,для этого путь поиска должен быть от клиента вниз
    @GetMapping("/ListCart")
    public List<Cart> getAllCart(Principal principal){
        Client client=repositoryClient.findByName(principal.getName()).get();
        List<Cart> carts=client.getCarts();
        repositoryClient.findAll();
        return carts;
    }



    @DeleteMapping(value = "/deleteCart/{id_pizza}")
    public void deletePizzaInCart(Principal principal,
                                  @PathVariable(value="id_pizza") long id_pizza){
        Client client=repositoryClient.findByName(principal.getName()).get();
        List<Cart> carts=client.getCarts();
        Cart cart;
        //мы ищем последную корзину и вней уже удаляем пиццу
        // (логика что у нас много корзин, неоплаченых), но тут она нам не нужна
        if(carts.size()!=0){
            cart=carts.get(carts.size()-1);
        }else{
            cart=new Cart();
        }
        cart.getPizzaList().remove(servicePizza.findById(id_pizza));
        serviceCart.save(cart);

           //мои старый вариант, без Principal
//        @PathVariable(value="id_cart") long id_cart,
//        Cart carts=serviceCart.findById(id_cart);
//        Pizza pizza=servicePizza.findById(id_pizza);
//        carts.getPizzaList().remove(pizza);
//        serviceCart.save(carts);
    }


}
//    @PostMapping(value = "/creeOrder") //получаем в Url id нашей пиццы при нажатии на кнопку
//    public Cart addPizzaToOrder(@RequestBody Cart carts, Principal principal)
//    { //чтобы это работало нужно создать кнопку пост - оплатить в Cart
//        Client client=repositoryClient.findByName(principal.getName()).get();
//        Orders orders=new Orders();
//        List<Cart> cartList=serviceCart.findAll();
//        orders.setCartList(cartList);
//        orders.setClient(client);
//
//        //дата
//        Date dateNow = new Date();
//        orders.setDateCreatOrder(dateNow);
//
//        carts.setOrders(orders);
//
//        repositoryOrder.save(orders);
//        serviceCart.save(carts);
//        repositoryClient.save(client); //сохраняем клиента с ордер и carts
//        return carts;
//    }
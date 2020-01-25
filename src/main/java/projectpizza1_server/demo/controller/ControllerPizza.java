package projectpizza1_server.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectpizza1_server.demo.model.*;
import projectpizza1_server.demo.repository.RepositoryClient;
import projectpizza1_server.demo.repository.RepositoryPizza;
import projectpizza1_server.demo.serviceImpl.FileStorage;
import projectpizza1_server.demo.serviceImpl.service.ServiceCart;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(value = "/api/pizza")
public class ControllerPizza {


    private RepositoryClient repositoryClient;
    private RepositoryPizza repositoryPizza;
    private ServiceCart serviceCart;
    //для UploadImage
    private FileStorage storage;


    @Autowired
    public ControllerPizza(RepositoryClient repositoryClient, RepositoryPizza repositoryPizza,
                            FileStorage storage,ServiceCart serviceCart) {
        this.repositoryClient = repositoryClient;
        this.repositoryPizza = repositoryPizza;
        this.storage = storage;
        this.serviceCart=serviceCart;
    }


    @GetMapping("/pizzaInCart")
    public List<Pizza> findAllPizza(){
        return repositoryPizza.findAll();
    }


    //Здесь так как у нас уже все есть в БД, мы получаем Id пиццы(по кнопка добавить пиццу) в c и дальше уже
    //добавляем ее в уже ордер и потом ордер в кастомер, для этого у нас есть привязка по @One...
    //Principal principal- мы получаем данные актуального пользователя(который уже зашел)
    @GetMapping (value = "/addPizzaInCart/{id}") //получаем в Url id нашей пиццы при нажатии на кнопку
        public Pizza addPizzaToOrder(@PathVariable(value = "id") Long id, Principal principal)
        {
            Client client=repositoryClient.findByName(principal.getName()).get();
            Cart carts; //объявил экземпляр класса (чтобы мы могли где то это инициализировать)
            List<Cart> cartsBuy=client.getCarts();//получаем список нашего заказа в клиенте,

          //далее проверка, если ячейка ordersBuy содержащий список ордеров не пустая значит пишем в него
            // Else же пустая, значит создаем новый Ордер и добавляем пиццы в него
          if(cartsBuy.size()!=0){
              carts=cartsBuy.get(cartsBuy.size()-1);
            }else{
              carts=new Cart();
            }

            Pizza pizza=repositoryPizza.findById(id).get();//находим пиццу по ID и ниже заносим
            carts.setClient(client);
            carts.getPizzaList().add(pizza);//короткий способ,добавить пиццу в ордер
            Cart savePizzaInCart=serviceCart.save(carts);

            client.getCarts().add(savePizzaInCart); //добавляем список пицц в клиент
            repositoryClient.save(client);
            return pizza;
        }


           //отправляем image
    @PostMapping("/files/pizzaImg")
    public Pizza saveImageAndImage(@ModelAttribute PizzaDTO pizzaDTO) {
            //путь по которому потом будем доставать наше фото
           //чтобы потом получалось доставать правильно наши фото для Get,
           //нам нужно правильно указать путь здесь и в нижней функции GetMapping
            String path = "http://localhost:8080/api/pizza/files/";
            System.out.println(pizzaDTO.getFile().getOriginalFilename());
            storage.store(pizzaDTO.getFile());
            Pizza pizza = new Pizza();

            pizza.setName(pizzaDTO.getName());
            pizza.setSize(pizzaDTO.getSize());
            pizza.setPrice(pizzaDTO.getPrice());
            pizza.setImage(path + pizzaDTO.getFile().getOriginalFilename());
            return repositoryPizza.save(pizza);
        }
    //получаем image
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource resource = storage.getFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; personfilename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}



    // добавление пиццы в корзину,создается массив обьектов в Cart
//            @GetMapping(value = "/addInCart/{id}") //получаем в Url id нашей пиццы при нажатии на кнопку
//        public Pizza addPizzaToCart(@PathVariable(value = "id") Long id)
//        {
//
//            Pizza pizza=repositoryPizza.findById(id).get();//находим пиццу по ID и ниже заносим
//          //  Cart cart; //объявил экземпляр класса (чтобы мы могли где то это инициализировать)
//            Cart cart=new Cart(); //решили создать новый Карт для записи в него
//            cart.getPizzaList().add(pizza);//короткий способ,добавить пиццу в ордер
//
//            repositoryCart.save(cart);//сохраняем ордер в кастомере
//            return pizza;
//        }


    //Здесь так как у нас уже все есть в БД, мы получаем Id пиццы(по кнопка добавить пиццу) в c и дальше уже
    //добавляем ее в уже ордер и потом ордер в кастомер, для этого у нас есть привязка по @One...
    //Principal principal- мы получаем данные актуального пользователя(который уже зашел)
//        @GetMapping(value = "/pizza/neworder/{id}") //получаем в Url id нашей пиццы при нажатии на кнопку
//        public Pizza addPizzaToOrder(@PathVariable(value = "id") Long id, Principal principal)
//        {
//            Client client=repositoryClient.findByName(principal.getName()).get();
//            Orders orders; //объявил экземпляр класса (чтобы мы могли где то это инициализировать)
//            List<Orders> ordersBuy=client.getOrdersList();//получаем список нашего заказа в клиенте,
//
//          //далее проверка, если ячейка ordersBuy содержащий список ордеров не пустая значит пишем в него
//            // Else же пустая, значит создаем новый Ордер и добавляем пиццы в него
//          if(ordersBuy.size()!=0){
//                orders=ordersBuy.get(ordersBuy.size()-1);
//            }else{
//                orders=new Orders();
//            }
//            Pizza pizza=repositoryPizza.findById(id).get();//находим пиццу по ID и ниже заносим
//            orders.setClient(client);
//            orders.getPizzaList().add(pizza);//короткий способ,добавить пиццу в ордер
//            Orders savePizzaInOrders=repositoryOrder.save(orders);
//
//            client.getOrdersList().add(savePizzaInOrders);
//            repositoryClient.save(client); //сохраняем ордер в кастомере
//            return pizza;
//        }







package projectpizza1_server.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

@Table(name = "mypizza")
@ToString
public class Pizza implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String size;
    private int price;
    private String image; //сюда мы передадим MultipartFile

  //  @ManyToMany(mappedBy = "pizzaList")
  //  private List<Orders> orders = new ArrayList<>();//set - нельзя одинаковые ид добавлять
}

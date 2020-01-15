package projectpizza1_server.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "mypizza")
public class Pizza implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String size;
    private int price;

  //  @ManyToMany(mappedBy = "pizzaList")
  //  private List<Orders> orders = new ArrayList<>();//set - нельзя одинаковые ид добавлять
}

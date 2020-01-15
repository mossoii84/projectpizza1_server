package projectpizza1_server.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "myorders")
public class Orders implements Serializable {
        private static final long serialVersionUID = 2L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String address;
        private Boolean buy;

        //    @JsonFormat(pattern = "dd/MM/yyyy")
       //    private LocalDate dateCreated;

        @ManyToOne
        @JoinColumn(name = "client_id")
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private Client client;


        @ManyToMany
        @JoinTable(name = "My_Pizza_InOrder",
                joinColumns = {@JoinColumn(name = "Order_id")},
                inverseJoinColumns = {@JoinColumn(name = "Pizza_id")})
        private List<Pizza> pizzaList=new ArrayList<>();


}

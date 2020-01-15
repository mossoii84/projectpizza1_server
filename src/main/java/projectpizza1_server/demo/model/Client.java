package projectpizza1_server.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "myclient")
public class Client implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String cardNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "client")
    private List<Orders> ordersList= new ArrayList<>();



    public Client(String name, String card_number) { }



}

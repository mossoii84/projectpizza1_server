package projectpizza1_server.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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


        //дата создания заказа
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        private Date dateCreatOrder;


        @ManyToOne
        @JoinColumn(name = "client_id_in_ordes")
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private Client client;


        @JsonIgnore
        @OneToMany(fetch = FetchType.LAZY,
                cascade =  CascadeType.ALL,
                mappedBy = "orders")
        private List<Cart> cartList= new ArrayList<>();

}

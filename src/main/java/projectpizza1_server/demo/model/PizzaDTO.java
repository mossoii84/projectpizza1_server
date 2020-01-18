package projectpizza1_server.demo.model;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PizzaDTO {
    private Long id;
    private String name;
    private String size;
    private int price;
      private MultipartFile file;


}
package projectpizza1_server.demo.security.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    @NaturalId
    private Roles userRole;

    public Role(Roles userRole) {
        this.userRole = userRole;
    }

    public Role() {
    }
}

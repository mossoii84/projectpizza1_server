package projectpizza1_server.demo.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

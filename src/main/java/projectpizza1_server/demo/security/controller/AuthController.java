package projectpizza1_server.demo.security.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projectpizza1_server.demo.model.Client;
import projectpizza1_server.demo.repository.RepositoryClient;
import projectpizza1_server.demo.security.jwt.JwtTokenProvider;
import projectpizza1_server.demo.security.model.*;
import projectpizza1_server.demo.security.repository.RoleRepository;
import projectpizza1_server.demo.security.repository.UserRepository;
import projectpizza1_server.demo.serviceImpl.service.ServiceClient;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtTokenProvider tokenProvider;
    private ServiceClient serviceClient;
    private RepositoryClient repositoryClient;

    @Autowired
    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder,
                          JwtTokenProvider provider,
                          RepositoryClient repositoryClient) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.tokenProvider = provider;
        this.repositoryClient =repositoryClient;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateJwtToken(authentication);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrincipal.getUsername(), userPrincipal.getAuthorities()));
    }

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody RegForm regForm) {
        if (userRepository.existsUserByUsername(regForm.getUsername()))
            return ResponseEntity.badRequest().body("This username is already taken! Choose another one!");
        User user = new User(regForm.getUsername(),
                encoder.encode(regForm.getPassword()));
        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleRepository.findRoleByUserRole(Roles.ROLE_USER));
        user.setUserRoles(defaultRoles);
        userRepository.save(user);

        //дублируем usera из security в client apin
         Client client=new Client();
//       Client client = new Client(regForm.getUsername(), "Card number in AuthController");
         client.setName(regForm.getUsername());
         client.setCardNumber("Card number in AuthController");
         repositoryClient.save(client);
        return ResponseEntity.ok().body("User registered successfully!");
    }
}

package projectpizza1_server.demo.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projectpizza1_server.demo.security.model.User;
import projectpizza1_server.demo.security.model.UserPrincipal;
import projectpizza1_server.demo.security.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with name\"" + s + "\" not found"));
        return UserPrincipal.build(user);
    }
}

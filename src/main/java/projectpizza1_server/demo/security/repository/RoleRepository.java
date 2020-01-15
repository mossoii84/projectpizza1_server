package projectpizza1_server.demo.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectpizza1_server.demo.security.model.Role;
import projectpizza1_server.demo.security.model.Roles;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByUserRole(Roles role);
}

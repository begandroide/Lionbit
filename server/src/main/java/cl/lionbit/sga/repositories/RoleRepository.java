package cl.lionbit.sga.repositories;

import cl.lionbit.sga.constans.Roles;
import cl.lionbit.sga.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(Roles role);
}

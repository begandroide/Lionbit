package cl.lionbit.sga.repositories;

import cl.lionbit.sga.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
}

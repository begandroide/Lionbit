package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Student> findAll();


    
}
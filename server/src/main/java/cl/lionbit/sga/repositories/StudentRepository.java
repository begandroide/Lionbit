package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
    //PagingAndSortingRepository<Student, Long> posee los métodos para realizar la paginación y hereda los métodos de
    // CrudRepository (Revisar su utilización)
    // Page <Student> findAll(Pageable pageable)

    Page<Student> findByFirstNameContainingIgnoreCase(String name, Pageable pageable);

    List<Student> findAll();


    
}
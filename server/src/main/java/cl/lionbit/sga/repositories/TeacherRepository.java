package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.entities.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>{
    Page<Teacher> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Teacher> findAll();
    
}
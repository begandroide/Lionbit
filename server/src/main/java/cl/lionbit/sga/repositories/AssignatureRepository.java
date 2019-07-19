package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.entities.Assignature;

@Repository
public interface AssignatureRepository extends CrudRepository<Assignature, Long>{
    Page<Assignature> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Assignature> findAll();
    
}
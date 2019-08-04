package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.entities.Semester;

@Repository
public interface SemesterRepository extends CrudRepository<Semester, Long>{
    Page<Semester> findAll( Pageable pageable);

    List<Semester> findAll();
    
    Semester findByActive(Boolean active);
}
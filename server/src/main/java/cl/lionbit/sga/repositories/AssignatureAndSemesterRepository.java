package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.entities.AssignatureAndSemester;

@Repository
public interface AssignatureAndSemesterRepository extends CrudRepository<AssignatureAndSemester, Long>{

    Page<AssignatureAndSemester> findBySemesterId(Long Id,Pageable pageable);
    
    Page<AssignatureAndSemester> findBySemesterId(Pageable pageable);

    List<AssignatureAndSemester> findAll();
    
}
package cl.lionbit.sga.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.lionbit.sga.entities.TeacherHasACourse;

@Repository
public interface TeacherAssignatureRepository extends CrudRepository<TeacherHasACourse, Long>{

    Page<TeacherHasACourse> findAll(Pageable pageable);

    List<TeacherHasACourse> findAll();

    List<TeacherHasACourse> findByTeacherIdAndAssignatureId(Long teacherId,Long assignatureId);
    
}
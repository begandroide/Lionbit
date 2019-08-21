package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.entities.AssignatureAndSemester;

public interface AssignatureAndSemesterService {

	List<AssignatureAndSemester> findAll();

	Page<AssignatureAndSemester> findPaginated(String filter, Pageable pageable);

	AssignatureAndSemester findOne(Long id);

	AssignatureAndSemester create(AssignatureAndSemester assignature);

	AssignatureAndSemester update(Long id, AssignatureAndSemester assignature);

	String delete(Long id);
}
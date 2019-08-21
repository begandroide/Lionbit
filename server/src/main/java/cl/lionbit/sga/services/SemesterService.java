package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.entities.Semester;

public interface SemesterService {

	Boolean checkIfExist(Semester semester);

	List<Semester> findAll();

	Page<Semester> findPaginated(String filter, Pageable pageable);

	Semester findOne(Long id);

	Semester findActive();

	Semester create(Semester assignature);

	Semester update(Long id, Semester assignature);

	String delete(Long id);
}
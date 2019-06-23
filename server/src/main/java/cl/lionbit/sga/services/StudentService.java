package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.entities.Student;

public interface StudentService {
	List<Student> findAll();

	Page<Student> findPaginated(String filter, Pageable pageable);

	Student findOne(Long id);

	Student create(Student course);

	Student update(Long id, Student course);

	String delete(Long id);
}
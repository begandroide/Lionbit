package cl.lionbit.sga.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.entities.Student;

public interface StudentService {
	List<Student> findAll();

	Page<Student> findPaginated(String filter, Pageable pageable);

	Student findOne(Long id);

	Student create(Student student);

	Student update(Long id, Student student);

	String delete(Long id);
}
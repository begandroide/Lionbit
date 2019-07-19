package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.models.Teacher;;

public interface TeacherService {
	List<Teacher> findAll();

	Page<Teacher> findPaginated(String filter, Pageable pageable);

	Teacher findOne(Long id);

	Teacher create(Teacher course);

	Teacher update(Long id, Teacher course);

	String delete(Long id);
}
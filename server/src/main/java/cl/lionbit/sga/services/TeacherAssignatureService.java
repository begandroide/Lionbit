package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.entities.TeacherHasACourse;

public interface TeacherAssignatureService {
	List<TeacherHasACourse> findAll();

	Page<TeacherHasACourse> findPaginated(String filter, Pageable pageable);

	TeacherHasACourse findOne(Long id);

	TeacherHasACourse create(TeacherHasACourse assignature);

	TeacherHasACourse update(Long id, TeacherHasACourse assignature);

	String delete(Long id);

	public Boolean CheckIfExistForTeacherIdAndAssignatureId(Long teacherId, Long assignatureId);
}
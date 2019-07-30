package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.entities.TeacherHasACourse;
import cl.lionbit.sga.repositories.TeacherAssignatureRepository;

@Service
public class TeacherAssignatureServiceImpl implements TeacherAssignatureService {

	@Autowired
	private TeacherAssignatureRepository repository;

	public TeacherAssignatureServiceImpl() {

	}

	@Override
	public List<TeacherHasACourse> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Page<TeacherHasACourse> findPaginated(String filter, Pageable pageable) {
		Page<TeacherHasACourse> test = repository.findAll(pageable);

		// Page<TeacherHasACourse> test = this.repository.findByCourseID(filter, pageable);
		return test;
	}

	@Override
	public TeacherHasACourse findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public TeacherHasACourse create(TeacherHasACourse TeacherHasACourse) {

		return this.repository.save(TeacherHasACourse);
	}

	public Boolean CheckIfExistForTeacherIdAndAssignatureId(Long teacherId, Long assignatureId){

		List<TeacherHasACourse> exists = this.repository.findByTeacherIdAndAssignatureId(teacherId, assignatureId);

		if(exists.isEmpty()) return false;

		return true;
	}

	@Override
	public TeacherHasACourse update(Long id, TeacherHasACourse TeacherHasACourse) {
		TeacherHasACourse toUpdate = this.repository.findById(id).get();

        toUpdate.setCreateAt(TeacherHasACourse.getCreateAt());
        toUpdate.setSemesterTaught(TeacherHasACourse.getSemesterTaught());
        toUpdate.setYear(TeacherHasACourse.getYear());
		return this.repository.save(toUpdate);
    }

	@Override
	public String delete(Long id) {
		this.repository.deleteById(id);
		return "Deleted TeacherHasACourse with id" + id;
	}

}
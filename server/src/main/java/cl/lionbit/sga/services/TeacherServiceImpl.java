package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.entities.Teacher;
import cl.lionbit.sga.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository repository;

	public TeacherServiceImpl() {

	}

	@Override
	public List<Teacher> findAll() {
		return (List<Teacher>) this.repository.findAll();
	}

	@Override
	public Page<Teacher> findPaginated(String filter, Pageable pageable) {
		Page<Teacher> test = this.repository.findByNameContainingIgnoreCase(filter, pageable);
		return test;
	}

	@Override
	public Teacher findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public Teacher create(Teacher teacher) {
		return this.repository.save(teacher);
	}

	@Override
	public Teacher update(Long id, Teacher teacher) {
		Teacher toUpdate = this.repository.findById(id).get();

		toUpdate.setName(teacher.getName());
		toUpdate.setLastname(teacher.getLastname());
		toUpdate.setRut(teacher.getRut());
		return this.repository.save(toUpdate);
    }

	@Override
	public String delete(Long id) {
		this.repository.deleteById(id);
		return "Deleted teacher with id" + id;
	}

}
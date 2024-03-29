package cl.lionbit.sga.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.entities.Student;
import cl.lionbit.sga.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	public StudentServiceImpl() {

	}

	@Override
	public List<Student> findAll() {
		return (List<Student>) this.repository.findAll();
	}

	@Override
	public Page<Student> findPaginated(String filter, Pageable pageable) {
		Page<Student> test = this.repository.findByFirstNameContainingIgnoreCase(filter, pageable);
		return test;
	}

	@Override
	public Student findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public Student create(Student student) {
		return this.repository.save(student);
	}

	@Override
	public Student update(Long id, Student student) {
		Student toUpdate = this.repository.findById(id).get();

		toUpdate.setLastName(student.getLastName());
		toUpdate.setFirstName(student.getFirstName());
		toUpdate.setRol_usm(student.getRol_usm());
		return this.repository.save(toUpdate);
    }

	@Override
	public String delete(Long id) {
		this.repository.deleteById(id);
		return "Deleted Student with id" + id;
	}

}
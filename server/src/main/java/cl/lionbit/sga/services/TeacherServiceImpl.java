package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.models.Teacher;
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
		return this.repository.findByNameContainingIgnoreCase(filter, pageable);
	}

	@Override
	public Teacher findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public Teacher create(Teacher student) {
		return this.repository.save(student);
	}

	@Override
	public Teacher update(Long id, Teacher student) {
                Teacher toUpdate = this.repository.findById(id).get();

                toUpdate.setName(student.getName());
                toUpdate.setRut(student.getRut());
                return this.repository.save(toUpdate);
                }

	@Override
	public String delete(Long id) {
                this.repository.deleteById(id);
                return "Deleted Student with id" + id;
                        }

}
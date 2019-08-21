package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.entities.Semester;
import cl.lionbit.sga.repositories.SemesterRepository;;

@Service
public class SemesterServiceImpl implements SemesterService {

	@Autowired
	private SemesterRepository repository;

	public SemesterServiceImpl() {

	}
	
	@Override
	public Boolean checkIfExist(Semester semester) {
		List<Semester> others = this.repository.findByNumberSemesterAndYearSemester(semester.getNumberSemester(),semester.getYearSemester());
		if(others.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public List<Semester> findAll() {
		return (List<Semester>) this.repository.findAll();
	}

	@Override
	public Page<Semester> findPaginated(String filter, Pageable pageable) {
		Page<Semester> test = this.repository.findAll( pageable);
		return test;
	}

	@Override
	public Semester findOne(Long id) {
		return this.repository.findById(id).get();
	}
	
	@Override
	public Semester findActive() {
		return this.repository.findByActive(true);
	}

	@Override
	public Semester create(Semester assignature) {
		return this.repository.save(assignature);
	}

	@Override
	public Semester update(Long id, Semester assignature) {
		Semester toUpdate = this.repository.findById(id).get();

		toUpdate.setActive(assignature.getActive());
		toUpdate.setInCourse(assignature.getInCourse());
		toUpdate.setNumberSemester(assignature.getNumberSemester());
		toUpdate.setYearSemester(assignature.getYearSemester());
		
		return this.repository.save(toUpdate);
    }

	@Override
	public String delete(Long id) {
		this.repository.deleteById(id);
		return "Deleted assignature with id" + id;
	}

}
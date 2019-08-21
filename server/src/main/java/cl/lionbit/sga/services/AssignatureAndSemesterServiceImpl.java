package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.entities.AssignatureAndSemester;
import cl.lionbit.sga.repositories.AssignatureAndSemesterRepository;;

@Service
public class AssignatureAndSemesterServiceImpl implements AssignatureAndSemesterService {

	@Autowired
	private AssignatureAndSemesterRepository repository;

	public AssignatureAndSemesterServiceImpl() {

	}

	@Override
	public List<AssignatureAndSemester> findAll() {
		return (List<AssignatureAndSemester>) this.repository.findAll();
	}

	@Override
	public Page<AssignatureAndSemester> findPaginated(String filter, Pageable pageable) {
		long use = 0;
		if(filter == ""){
			use = Long.parseLong(filter);
		}
		Page<AssignatureAndSemester> test = this.repository.findBySemesterId(use,pageable);
		return test;
	}

	@Override
	public AssignatureAndSemester findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public AssignatureAndSemester create(AssignatureAndSemester assignature) {
		return this.repository.save(assignature);
	}

	@Override
	public AssignatureAndSemester update(Long id, AssignatureAndSemester assignature) {
		AssignatureAndSemester toUpdate = this.repository.findById(id).get();

        toUpdate.setNumberParalelos(assignature.getNumberParalelos());
        toUpdate.setNumberStudentsByParalelo(assignature.getNumberStudentsByParalelo());
		return this.repository.save(toUpdate);
    }

	@Override
	public String delete(Long id) {
		this.repository.deleteById(id);
		return "Deleted assignature semester with id" + id;
	}

}
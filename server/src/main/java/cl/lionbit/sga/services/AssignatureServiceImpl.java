package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.lionbit.sga.entities.Assignature;
import cl.lionbit.sga.repositories.AssignatureRepository;;

@Service
public class AssignatureServiceImpl implements AssignatureService {

	@Autowired
	private AssignatureRepository repository;

	public AssignatureServiceImpl() {

	}

	@Override
	public List<Assignature> findAll() {
		return (List<Assignature>) this.repository.findAll();
	}

	@Override
	public Page<Assignature> findPaginated(String filter, Pageable pageable) {
		Page<Assignature> test = this.repository.findByNameContainingIgnoreCase(filter, pageable); 
		return test;
	}

	@Override
	public Assignature findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public Assignature create(Assignature assignature) {
		return this.repository.save(assignature);
	}

	@Override
	public Assignature update(Long id, Assignature assignature) {
		Assignature toUpdate = this.repository.findById(id).get();

        toUpdate.setName(assignature.getName());
        toUpdate.setSigla(assignature.getSigla());
		return this.repository.save(toUpdate);
    }

	@Override
	public String delete(Long id) {
		this.repository.deleteById(id);
		return "Deleted assignature with id" + id;
	}

}
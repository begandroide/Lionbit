package cl.lionbit.sga.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.lionbit.sga.entities.Assignature;;

public interface AssignatureService {
	List<Assignature> findAll();

	Page<Assignature> findPaginated(String filter, Pageable pageable);

	Assignature findOne(Long id);

	Assignature create(Assignature assignature);

	Assignature update(Long id, Assignature assignature);

	String delete(Long id);
}
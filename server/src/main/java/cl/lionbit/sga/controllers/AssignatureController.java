package cl.lionbit.sga.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.lionbit.sga.models.Assignature;
import cl.lionbit.sga.services.AssignatureService;

@RestController
@RequestMapping("/assignatures")
public class AssignatureController {
	private static Logger logger = LoggerFactory.getLogger(AssignatureController.class);

	@Autowired
	private AssignatureService service;

	public AssignatureController() {

	}

	@GetMapping
	public @ResponseBody Page<Assignature> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page of assignatures");

		return this.service.findPaginated(filter, pageable);

	}

	@GetMapping("/all")
	public @ResponseBody List<Assignature> all() {

		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Assignature>  findById(@PathVariable(value = "id") Long id) {
		Assignature savedAssignature = this.service.findOne(id);

		return new ResponseEntity<>(savedAssignature, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<Assignature> create(@Valid @RequestBody Assignature assignature) {

		Assignature savedAssignature = this.service.create(assignature);

		return new ResponseEntity<>(savedAssignature, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Assignature> update(@PathVariable(value = "id") Long id, @RequestBody Assignature assignature) {
		Assignature updatedAssignature = this.service.update(id, assignature);
		return new ResponseEntity<>(updatedAssignature, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
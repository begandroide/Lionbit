package cl.lionbit.sga.controllers;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
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

import cl.lionbit.sga.entities.AssignatureAndSemester;
import cl.lionbit.sga.services.AssignatureAndSemesterService;

import static cl.lionbit.sga.constans.Paths.ASSIGNATURESEMESTER;
import static cl.lionbit.sga.constans.Paths.VERSION;

@RestController
@RequestMapping(ASSIGNATURESEMESTER)
@Api(value="Assignature management System " + VERSION)
public class AssignatureAndSemesterController {
	private static Logger logger = LoggerFactory.getLogger(AssignatureController.class);


	@Autowired
	private AssignatureAndSemesterService service;

	public AssignatureAndSemesterController() {

	}

	@GetMapping
	public @ResponseBody Page<AssignatureAndSemester> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page of assignatures");

		return this.service.findPaginated(filter, pageable);

	}

	@GetMapping("/all")
	public @ResponseBody List<AssignatureAndSemester> all() {

		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AssignatureAndSemester>  findById(@PathVariable(value = "id") Long id) {
		AssignatureAndSemester savedAssignature = this.service.findOne(id);

		return new ResponseEntity<>(savedAssignature, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<AssignatureAndSemester> create(@Valid @RequestBody AssignatureAndSemester assignature) {

		AssignatureAndSemester savedAssignature = this.service.create(assignature);

		return new ResponseEntity<>(savedAssignature, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AssignatureAndSemester> update(@PathVariable(value = "id") Long id, @RequestBody AssignatureAndSemester assignature) {
		AssignatureAndSemester updatedAssignature = this.service.update(id, assignature);
		return new ResponseEntity<>(updatedAssignature, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
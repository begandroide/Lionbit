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

import cl.lionbit.sga.models.Teacher;;
import cl.lionbit.sga.services.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
	private static Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private TeacherService service;

	public TeacherController() {

	}

	@GetMapping
	public @ResponseBody Page<Teacher> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page");

		return this.service.findPaginated(filter, pageable);

	}

	@GetMapping("/all")
	public @ResponseBody List<Teacher> all() {

		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Teacher>  findById(@PathVariable(value = "id") Long id) {
		Teacher savedBeer = this.service.findOne(id);

		return new ResponseEntity<>(savedBeer, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<Teacher> create(@Valid @RequestBody Teacher Teacher) {

		Teacher savedTeacher = this.service.create(Teacher);

		return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Teacher> update(@PathVariable(value = "id") Long id, @RequestBody Teacher Teacher) {
		Teacher updatedTeacher = this.service.update(id, Teacher);
		return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
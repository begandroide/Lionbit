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

import cl.lionbit.sga.models.Student;
import cl.lionbit.sga.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	private static Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService service;

	public StudentController() {

	}

	@GetMapping
	public @ResponseBody Page<Student> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page");

		return this.service.findPaginated(filter, pageable);

	}

	@GetMapping("/all")
	public @ResponseBody List<Student> all() {

		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student>  findById(@PathVariable(value = "id") Long id) {
		Student savedBeer = this.service.findOne(id);

		return new ResponseEntity<>(savedBeer, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<Student> create(@Valid @RequestBody Student student) {

		Student savedStudent = this.service.create(student);

		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable(value = "id") Long id, @RequestBody Student student) {
		Student updatedStudent = this.service.update(id, student);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
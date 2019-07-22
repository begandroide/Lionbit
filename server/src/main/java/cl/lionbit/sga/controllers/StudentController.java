package cl.lionbit.sga.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import io.swagger.annotations.*;
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

import cl.lionbit.sga.entities.Student;
import cl.lionbit.sga.services.StudentService;

import static cl.lionbit.sga.constans.Paths.STUDENTS;
import static cl.lionbit.sga.constans.Paths.VERSION;

@RestController
@Api(value="Student Management System " + VERSION)
@RequestMapping(STUDENTS)
public class StudentController {
	private static Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService service;

	public StudentController() {

	}

	@GetMapping
	public @ResponseBody Page<Student> home(Pageable pageable, @RequestParam(defaultValue = "4") String filter ) {

		logger.info("Controller Find Page");

		return this.service.findPaginated(filter, pageable);

	}

	@ApiOperation(value = "View a list of available students", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/all")
	public @ResponseBody List<Student> all() {

		return this.service.findAll();
	}

	@ApiOperation(value = "Get an student by Id", response = Student.class)
	@ApiImplicitParam(name = "student ID", value = "Student id from which student object will retrieve",
			required = true, dataType = "Long", paramType="path")
	@ApiResponses(value = {
			@ApiResponse(code = 302, message = "Successfully retrieved an student."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 500, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Student>  findById(@PathVariable(value = "id") Long id) {

		Student savedStudent = this.service.findOne(id);
		return new ResponseEntity<>(savedStudent, HttpStatus.FOUND);
	}

	@ApiOperation(value = "Add an student")
	@PostMapping
	public ResponseEntity<Student> create(
			@ApiParam(value = "Student object", required = true) @Valid @RequestBody Student student) {

		Student savedStudent = this.service.create(student);

		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update an student")
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(
			@ApiParam(value = "Student id", required = true) @PathVariable(value = "id") Long id,
			@ApiParam(value = "Student object", required = true) @Valid @RequestBody Student student) {

		Student updatedStudent = this.service.update(id, student);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an student")
	@DeleteMapping("/{id}")
	public @ResponseBody String delete(
			@ApiParam(value = "Student id for delete", required = true) @PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
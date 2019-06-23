package cl.lionbit.sga.controllers;

import java.util.List;

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

import cl.lionbit.sga.models.Teacher;
import cl.lionbit.sga.services.TeacherService;

@RestController
@Api(value="Teacher Management System", description="Operations pertaining to teacher in SGA")
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

	@ApiOperation(value = "View a list of available teachers", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/all")
	public @ResponseBody List<Teacher> all() {

		return this.service.findAll();
	}


	@ApiOperation(value = "Get an student by Id", response = Teacher.class)
	@ApiResponses(value = {
			@ApiResponse(code = 302, message = "Successfully retrieved an student"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 500, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Teacher>  findById(
			@ApiParam(name = "Teacher ID", value = "Student id from which student object will retrieve", required = true)
			@PathVariable(value = "id") Long id) {

		Teacher savedBeer = this.service.findOne(id);
		return new ResponseEntity<>(savedBeer, HttpStatus.FOUND);
	}

	@ApiOperation(value = "Add an teacher")
	@PostMapping
	public ResponseEntity<Teacher> create(
			@ApiParam(value = "Teacher object", required = true) @Valid @RequestBody Teacher Teacher) {

		Teacher savedTeacher = this.service.create(Teacher);
		return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update an teacher")
	@PutMapping("/{id}")
	public ResponseEntity<Teacher> update(
			@ApiParam(value = "Teacher id", required = true) @PathVariable(value = "id") Long id,
			@ApiParam(value = "Teacher object", required = true) @RequestBody Teacher Teacher) {
		Teacher updatedTeacher = this.service.update(id, Teacher);
		return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete an teacher")
	@DeleteMapping("/{id}")
	public @ResponseBody String delete(
			@ApiParam(value = "Student id for delete", required = true) @PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
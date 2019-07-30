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

import cl.lionbit.sga.entities.TeacherHasACourse;
import cl.lionbit.sga.services.TeacherAssignatureService;

import static cl.lionbit.sga.constans.Paths.TEACHERASSIGNATURES;
import static cl.lionbit.sga.constans.Paths.VERSION;

@RestController
@RequestMapping(TEACHERASSIGNATURES)
@Api(value="Assignature management System " + VERSION)
public class TeacherAssignatureController {
	private static Logger logger = LoggerFactory.getLogger(TeacherAssignatureController.class);

	@Autowired
	private TeacherAssignatureService service;

	public TeacherAssignatureController() {

	}

	@GetMapping
	public @ResponseBody Page<TeacherHasACourse> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page of assignatures");

		return this.service.findPaginated(filter, pageable);

	}

	@GetMapping("/all")
	public @ResponseBody List<TeacherHasACourse> all() {

		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherHasACourse>  findById(@PathVariable(value = "id") Long id) {
		TeacherHasACourse savedAssignature = this.service.findOne(id);

		return new ResponseEntity<>(savedAssignature, HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<TeacherHasACourse> create(@Valid @RequestBody TeacherHasACourse assignature) {
		//validate
		Boolean exist = this.service.CheckIfExistForTeacherIdAndAssignatureId(assignature.getTeacherId(),assignature.getAssignatureId());
		if(!exist){
			TeacherHasACourse savedAssignature = this.service.create(assignature);
			return new ResponseEntity<>(savedAssignature, HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(assignature, HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<TeacherHasACourse> update(@PathVariable(value = "id") Long id, @RequestBody TeacherHasACourse assignature) {
		TeacherHasACourse updatedAssignature = this.service.update(id, assignature);
		return new ResponseEntity<>(updatedAssignature, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
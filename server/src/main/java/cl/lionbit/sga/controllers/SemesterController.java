package cl.lionbit.sga.controllers;

import java.util.List;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.lionbit.sga.entities.Semester;
import cl.lionbit.sga.services.SemesterService;

import static cl.lionbit.sga.constans.Paths.SEMESTER;
import static cl.lionbit.sga.constans.Paths.VERSION;

@RestController
@RequestMapping(SEMESTER)
@Api(value="Assignature management System " + VERSION)
public class SemesterController {
	private static Logger logger = LoggerFactory.getLogger(SemesterController.class);

	@Autowired
	private SemesterService service;

	public SemesterController() {

	}

	@GetMapping
	public @ResponseBody Page<Semester> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page of assignatures");

		return this.service.findPaginated(filter, pageable);

	}

	@GetMapping("/all")
	public @ResponseBody List<Semester> all() {

		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Semester>  findById(@PathVariable(value = "id") Long id) {
		Semester savedAssignature = this.service.findOne(id);

		return new ResponseEntity<>(savedAssignature, HttpStatus.FOUND);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Semester> create(@Valid @RequestBody Semester assignature) {

		assignature.setActive(false);
		assignature.setCreateAt(new Date());
		Semester savedAssignature = this.service.create(assignature);
		return new ResponseEntity<>(savedAssignature, HttpStatus.CREATED);
		// if(!this.service.checkIfExist(assignature)){
		// }else{
		// 	return new ResponseEntity<>(assignature, HttpStatus.FOUND);
		// }

	}

	@PutMapping("/{id}")
	public ResponseEntity<Semester> update(@PathVariable(value = "id") Long id, @RequestBody Semester assignature) {
		Semester updatedAssignature = this.service.update(id, assignature);
		return new ResponseEntity<>(updatedAssignature, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable(value = "id") Long id) {
		return this.service.delete(id);
	}

}
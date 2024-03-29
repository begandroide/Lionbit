package cl.lionbit.sga.controllers;

import cl.lionbit.sga.entities.User;
import cl.lionbit.sga.services.CustomUserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cl.lionbit.sga.constans.Paths.*;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@Api(value="User Management" + VERSION)
@RequestMapping(USERSPATH)
public class UserController {
    
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private CustomUserDetailsService userService;

    @GetMapping
	public @ResponseBody Page<User> home(Pageable pageable, @RequestParam(defaultValue = "") String filter ) {

		logger.info("Controller Find Page of User");

		return this.userService.findPaginated(filter, pageable);

	}

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }

    @GetMapping("/all")
    public @ResponseBody List<User> all() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update( @PathVariable(value = "id") Long id, @Valid @RequestBody User user) {

        User updatedUser = userService.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        return userService.delete(id);
    }
}

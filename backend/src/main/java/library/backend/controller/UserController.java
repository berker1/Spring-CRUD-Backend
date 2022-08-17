package library.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.backend.business.UserService;
import library.backend.exception.ApiError;
import library.backend.model.User;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	
	@PostMapping("users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		
		String firstname = user.getFirstName();
		String lastname = user.getLastName();
		
		if(firstname == null || firstname.isEmpty()) {	
			validationErrors.put("firstname", "username can not be null");
		}
		if(lastname == null || lastname.isEmpty()) {
			validationErrors.put("lastname", "can not be null");
		}
		if(validationErrors.size() > 0) {
			error.setValidationErrors(validationErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}else {
			userService.save(user);
			//return (ResponseEntity<?>) ResponseEntity.ok();
			return ResponseEntity.ok().build();
		}	
		
	}
	
	@GetMapping("users")
	public List<User> getAllUsers() {
		return userService.getAll();
	}
	
	@GetMapping("users/name")
	public User findByName(String name) {
		return userService.getByFirstName(name);
	}
	
}

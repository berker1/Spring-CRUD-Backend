package library.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.backend.exception.ApiError;
import library.backend.model.User;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v1/")
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	
	@PostMapping("auth")
	public ResponseEntity<?> handleAuthentication(@RequestBody User user) {
		if(user.getFirstName() != null || user.getPassword() != null ) {
			return ResponseEntity.ok().build();
		}
		ApiError error = new ApiError(401, "userName or password is null" , "/api/1.0/auth");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	
	@PostMapping("auth/login")
	public ResponseEntity<?> loginAuth(String userName, String password) {
		if(userName != null || password != null ) {
			return ResponseEntity.ok().build();
		}
		ApiError error = new ApiError(401, "userName or password is null" , "/api/1.0/auth");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
}

/*
	@PostMapping("auth")
	public ResponseEntity<?> handleAuthentication(@RequestHeader(name="Authorization") String authorization) {
		if(authorization == null) {
			ApiError error = new ApiError(401, "Unauthorized request" , "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		log.info(authorization);
		return ResponseEntity.ok().build();
	}
}

// şifre ve kullanıcı adını buraya yolla, daha sonrasında direkt 



@PostMapping("auth")
	public ResponseEntity<?> handleAuthentication(@RequestHeader(name="Authorization") String authorization) {
		if(authorization == null) {
			ApiError error = new ApiError(401, "Unauthorized request" , "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		String base64encoded = authorization.split("Basic")[1];
		String decoded = new String (Base64.getDecoder().decode(base64encoded));
		String[] parts = decoded.split(":");
		String username = parts[0];
		String password = parts[1];
		User inDB = userService.getByFirstName(username);
		if(inDB == null) {
			ApiError error = new ApiError(401, "Unauthorized request" , "/api/1.0/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		String hashedPassword = inDB.getPassword();
		return ResponseEntity.ok().build();
	}
*/
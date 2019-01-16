/**
 * 
 */
package com.cts.movie.cruiser.auth.service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.movie.cruiser.auth.service.model.User;
import com.cts.movie.cruiser.auth.service.services.SecurityTokenGenerator;
import com.cts.movie.cruiser.auth.service.services.UserService;

/**
 * @author ubuntu
 *
 */
@RestController
@EnableWebMvc
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator securityTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail) {
		try {
			String userId = loginDetail.getUserId();
			String password = loginDetail.getPassword();

			if (null == userId || null == password) {
				throw new Exception("Username or password cannot be empty");
			}

			User user = userService.findByUserIdAndPassword(userId, password);
			if (null == user) {
				throw new Exception("User with given Id does not exists.");
			}

			String pwd = user.getPassword();
			if (!pwd.equals(password)) {
				throw new Exception("Invalid login credentials. Please check username and password");
			}
			Map<String, String> map = securityTokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}

}

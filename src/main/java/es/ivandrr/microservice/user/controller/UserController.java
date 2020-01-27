package es.ivandrr.microservice.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.ivandrr.microservice.user.model.User;
import es.ivandrr.microservice.user.service.UserService;

@RestController
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		this.userService.addUser(user);
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return this.userService.getAllUsers();
	}
	
	@DeleteMapping("/users/{nickname}")
	public void deleteUser(@PathVariable("nickname") String nickname) {
		this.userService.deleteUser(nickname);
	}
	
	@GetMapping("/users/{country}")
	public List<User> getUsers(@PathVariable("country") String country) {
		return this.userService.searchUsersByCountry(country);
	}
	
	@PutMapping("/users/{userToModify}")
	public void modifyUser(@PathVariable("userToModify") String userToModify, @RequestBody User user) {
		this.userService.modifyUser(userToModify, user);
	}
	
}

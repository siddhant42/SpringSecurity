package com.example.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@GetMapping(path="/{username}")
	public User getUserById(@PathVariable("username")String username) {
		return userService.getUserByUsername(username);
	}

	@PostMapping
	public String registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}

	@PutMapping(path ="/{username}")
	public void updateUser(@PathVariable("username")String username,@RequestBody User user) {
		userService.updateUser(username,user);
	}
	
	@DeleteMapping(path="/{username}")
	public void deleteuser(@PathVariable("username")String username) {
		userService.deleteuser(username);
	}

}

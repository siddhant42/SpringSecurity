package com.example.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@GetMapping(path="/{id}")
	public User getUserById(@PathVariable("id")int id) {
		return userService.getUserById(id);
	}
//	@GetMapping(path="/{email}")
//	public User getUserByEmail(@PathVariable("email") String email) {
//		return userService.getUserByEmail(email);
//	}
	@PostMapping
	public void registerNewUser(@RequestBody User user) {
		userService.registerNewUser(user);
	}
//	@PutMapping(path ="/{id}")
//	public void updateUser(@PathVariable("id")int id,@RequestBody User user) {
//		userService.updateUser(id,user);
//	}
	@PutMapping(path ="/{email}")
	public void updateUser(@PathVariable("email")String email,@RequestBody User user) {
		userService.updateUser(email,user);
	}
//	@PutMapping(path ="/password/{id}")
//	public void updatePassword(@PathVariable("id")int id, @RequestBody User user) {
//		UserDetails userDetails = new UserDetailsImpl(user);
//		userService.updatePassword(userDetails, user.getPassword());
//	}
}

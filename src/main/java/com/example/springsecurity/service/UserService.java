package com.example.springsecurity.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import static com.example.springsecurity.utils.CommonUtils.*;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public void registerNewUser(@RequestBody User user) {
		if(user.getPassword()!=null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		Role role = roleService.getDefaultRole();
		user.setRoles(new ArrayList<>(Arrays.asList(role)));
		userRepo.saveAndFlush(user);
	}

//	public void updateUser(int id, User user) {
//			User user1 = userRepo.getOne(id);
//			merge(user1,user);
//	}
	public void updateUser(String email, User user) {
		Optional<User> user1 = userRepo.findByEmail(email);
		user1.ifPresent(x->merge(x,user));
	}

	public User getUserById(int id) {
		User user = userRepo.findById(id).orElseThrow(()-> new IllegalStateException("user does not exist"));
		return user;
	}
	
	private void merge(User user1,User user2) {
//		copyNonNullProperties(user1,user);
		if(user2.getEmail()!=null) 
			user1.setEmail(user2.getEmail());
		if(user2.getPassword()!=null) 
			user1.setPassword(passwordEncoder.encode(user2.getPassword()));
		if(user2.getIsActive()!=null) 
			user1.setIsActive(user2.getIsActive());
		userRepo.saveAndFlush(user1);
	}

//	@Override
//	public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
//		Optional<User> user = userRepo.findByEmail(userDetails.getUsername());
//		user.ifPresent(x->x.setPassword(passwordEncoder.encode(newPassword)));
//		userRepo.save(user.get());
//		return new UserDetailsImpl(user.get());
//	}
	


//	public User getUserByEmail(String email){
//		return userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user does not exist"));
//	}
	
}

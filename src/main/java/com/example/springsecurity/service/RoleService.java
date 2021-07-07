package com.example.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsecurity.entity.Role;
import com.example.springsecurity.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepo;
	
	public Role getDefaultRole() {
		return roleRepo.findByName("ROLE_USER");
	}
}

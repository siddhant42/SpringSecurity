package com.example.springsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    	Optional<User> user = userRepository.findByEmail(email);

    	user.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));

//    	return user.map(UserDetailsImpl::new).get();
    	return new UserDetailsImpl(user.get());
    }
}

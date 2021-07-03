package com.ensup.myresto.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ensup.myresto.domaine.User;
import com.ensup.myresto.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto registrationDto);
	
	User findByEmail(String email);
}

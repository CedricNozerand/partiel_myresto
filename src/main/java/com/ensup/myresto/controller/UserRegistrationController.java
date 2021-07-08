package com.ensup.myresto.controller;

import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensup.myresto.service.UserService;
import com.ensup.myresto.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registrationUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		
		if(Pattern.matches("[a-zA-Z]+", registrationDto.getFirstName())) {
			if(Pattern.matches("[a-zA-Z]+", registrationDto.getLastName())) {
				if(Pattern.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", registrationDto.getEmail())) {
					if(Pattern.matches("[0-9]{10}", registrationDto.getPhoneNumber())) {
						if(registrationDto.getPassword().equals(registrationDto.getPasswordConfirmation())) {
							userService.save(registrationDto);
							return "redirect:/?success";
						}
					}
				}
			}
		}
		return "/registration";
	}
}

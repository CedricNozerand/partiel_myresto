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

	/**
	 * Constructeur
	 * @param userService 
	 */
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	/**
	 * Récupère l'objet UserRegistrationDto 
	 * @return UserRegistrationDto
	 */
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	/**
	 * Affiche la page de création de compte
	 * @return L'URL de la page registration
	 */
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	/**
	 * Enregistre un utilisateur en base de données
	 * @param registrationDto: Objet qui représente un enregistrement
	 * @return L'URL de la page registration
	 */
	@PostMapping
	public String registrationUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		
		if(Pattern.matches("[\\p{L}]+", registrationDto.getFirstName())) {
			if(Pattern.matches("[\\p{L}]+", registrationDto.getLastName())) {
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

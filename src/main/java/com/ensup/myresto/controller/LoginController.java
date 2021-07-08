package com.ensup.myresto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	/**
	 * Affiche la page d'authentification
	 * @return L'URL de la page login
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}

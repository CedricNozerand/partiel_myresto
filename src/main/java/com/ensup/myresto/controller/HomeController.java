package com.ensup.myresto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensup.myresto.repository.ProductRepository;

@Controller
public class HomeController {

	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * Affiche la page d'accueil
	 * @param model
	 * @return L'URL de la page home
	 */
	@RequestMapping(value={"", "/", "/home"})
	public String home(Model model) {
		
		model.addAttribute("pizza_list", productRepository.findByType("PIZZA"));
		model.addAttribute("dessert_list", productRepository.findByType("DESSERT"));
		model.addAttribute("boisson_list", productRepository.findByType("BOISSON"));
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 

		return "home";
	}
}

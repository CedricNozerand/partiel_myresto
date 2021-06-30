package com.ensup.myresto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ensup.myresto.repository.PizzaRepository;

@Controller
public class HomeController {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("listPizzas", pizzaRepository.findAll());
		return "home";
	}
}

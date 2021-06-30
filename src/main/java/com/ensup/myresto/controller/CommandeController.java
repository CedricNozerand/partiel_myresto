package com.ensup.myresto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ensup.myresto.repository.PizzaRepository;

@Controller
public class CommandeController {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@GetMapping("/showcommande/{name}")
	public String showCommande(@PathVariable(name = "name") String name, Model model) {
		model.addAttribute("pizza", pizzaRepository.findByName(name));
		return "/home";
	}
}

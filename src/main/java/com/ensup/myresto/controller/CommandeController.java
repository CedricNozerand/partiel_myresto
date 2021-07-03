package com.ensup.myresto.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.repository.CommandRepository;
import com.ensup.myresto.repository.ProductRepository;
import com.ensup.myresto.repository.UserRepository;

@Controller
public class CommandeController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CommandRepository commandRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/addToCommande/{name}")
	public String showCommande(@PathVariable(name = "name") String name, Model model) {
		Product product = productRepository.findByName(name);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		Collection<Product> products = new ArrayList<Product>();
		products.add(product);
		
		Command command = new Command(formatter.format(date), userRepository.findByEmail("cedric.nozerand@gmail.com"), products);
		
		commandRepository.save(command);
		
		model.addAttribute("listPizzas", productRepository.findAll());
		return "home";
	}
	
	@GetMapping("/commande")
	public String commande() {
		return "commande";
	}
}

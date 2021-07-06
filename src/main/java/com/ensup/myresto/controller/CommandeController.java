package com.ensup.myresto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.CommandStatus;
import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.service.CommandService;
import com.ensup.myresto.service.ProductService;
import com.ensup.myresto.service.UserService;


@Controller
public class CommandeController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommandService commandService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/addToCommande/{name}")
	public String addToCommand(@PathVariable(name = "name") String productName, Model model) {
		
		Product product = productService.getProductByName(productName);
		
		Date date = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		// TODO : Get the actual current connected user
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
		Command command = commandService.getActiveCommandOrCreateOne(userService.findByEmail(email));
		
		command.getProducts().add(product);
		command.setDate(date);
		
		commandService.save(command);
		
		model.addAttribute("successMessage", "Le produit " + product.getName() + " a été ajouté à la commande.");
		
		model.addAttribute("pizza_list", productService.findByType("PIZZA"));
		model.addAttribute("dessert_list", productService.findByType("DESSERT"));
		model.addAttribute("boisson_list", productService.findByType("BOISSON"));
		
		return "home";
	}
	
	@GetMapping("/commande")
	public String commande(Model model) {

		// TODO : Get the actual current connected user
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
		Command command = commandService.getActiveCommandOrCreateOne(userService.findByEmail(email));
		
		model.addAttribute("command", command);
		
		return "commande";
	}
	
	@GetMapping("/validateCommand/{commandId}")
	public String showCommande(@PathVariable(name = "commandId") Long commandId, Model model) {
		
		Command command = commandService.getCommandById(commandId);
		
		command.setStatus(CommandStatus.Paid);
		
		commandService.save(command);
		
		model.addAttribute("successMessage", "La commande n° " + command.getId() + " a bien été enregistrée.");
		
		model.addAttribute("products", productService.getAllProducts());
		
		return "home";
	}
	
	@GetMapping("/allCommande")
	public String getAllCommands(Model model){
		model.addAttribute("commandList", commandService.getAllCommands());
		return "listeCommandes";
	}
	
//	@RequestMapping(value="/allCommand", method=RequestMethod.POST)
//	public String getAllCommands(@RequestParam("select_status") String status, Model model){
//		model.addAttribute("commandList", commandService.getAllCommandsByStatut(status));
//		return "listeCommandes";
//	}
	
	@GetMapping("/changeStatus/{id}")
	public String changeStatus(@PathVariable(name = "id") Long id, Model model){
		Command commandFound = commandService.getCommandById(id);
		commandService.changeStatus(commandFound);
		model.addAttribute("commandList", commandService.getAllCommands());
		return "listeCommandes";
	}
}

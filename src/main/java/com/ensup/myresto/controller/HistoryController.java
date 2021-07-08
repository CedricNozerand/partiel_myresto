package com.ensup.myresto.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.User;
import com.ensup.myresto.service.CommandService;
import com.ensup.myresto.service.UserService;

@Controller
public class HistoryController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommandService commandService;

	/**
	 * Affiche l'historique des commandes de l'utilisateur qui est connecté
	 * @param model
	 * @return L'URL de la page historique
	 */
	@GetMapping("/historique")
	public String showHistory(Model model) {

		// TODO : Get the actual current connected user
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
		User user = userService.findByEmail(email);
		
		Set<Command> unsortedCommands = user.getCommands();
		
		List<Command> sortedCommands = commandService.sort(unsortedCommands);
		model.addAttribute("commands", sortedCommands);
		
		return "historique";
	}
	
	/**
	 * Affiche l'historique de toutes les commandes enregistrées en base de données
	 * @param model
	 * @return L'URL de la page allHistorique
	 */
	@GetMapping("/allHistorique")
	public String showAllHistory(Model model) {
		
		model.addAttribute("list_commands", commandService.getAllCommands());
		
		return "allHistorique";
	}
}

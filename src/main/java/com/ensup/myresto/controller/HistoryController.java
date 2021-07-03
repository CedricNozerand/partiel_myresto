package com.ensup.myresto.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/historique")
	public String showHistory(Model model) {

		// TODO : Get the actual current connected user
		User user = userService.findByEmail("cedric.nozerand@gmail.com");
		
		Set<Command> unsortedCommands = user.getCommands();
		
		List<Command> sortedCommands = commandService.sort(unsortedCommands);
		model.addAttribute("commands", sortedCommands);
		
		return "historique";
	}
}

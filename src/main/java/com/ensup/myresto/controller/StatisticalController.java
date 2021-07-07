package com.ensup.myresto.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.service.CommandService;

@Controller
public class StatisticalController {

	@Autowired
	private CommandService commandService;
	
	@GetMapping("/statistical")
	public String showStatisticals(Model model) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int January = 0, February = 0, March = 0, April = 0, May = 0, June = 0, July = 0, August = 0, September = 0, October = 0, November = 0, December = 0;
		
		List<Command> commands = commandService.getAllCommands();
		List<Command> currentYearsCommands = new ArrayList<Command>();
		
		for(int i = 0; i < commands.size(); i++) {
			if(commands.get(i).getDate().getYear() + 1900 == currentYear) {
				currentYearsCommands.add(commands.get(i));
			}
		}
		
		for(int i = 0; i < currentYearsCommands.size(); i++) {
			switch(currentYearsCommands.get(i).getDate().getMonth()) {
				case 0: 
					January++;
					//products = currentYearsCommands.get(i).getProducts().stream().collect(Collectors.toList());
					break;
					
				case 1: 
					February++;
					break;
					
				case 2: 
					March++;
					break;
					
				case 3: 
					April++;
					break;
					
				case 4: 
					May++;
					break;
					
				case 5: 
					June++;
					break;
					
				case 6: 
					July++;
					break;
					
				case 7: 
					August++;
					break;
					
				case 8: 
					September++;
					break;
					
				case 9: 
					October++;
					break;
					
				case 10: 
					November++;
					break;
					
				case 11: 
					December++;
					break;
			}
		}
		
		model.addAttribute("January", January);
		model.addAttribute("February", February);
		model.addAttribute("March", March);
		model.addAttribute("April", April);
		model.addAttribute("May", May);
		model.addAttribute("June", June);
		model.addAttribute("July", July);
		model.addAttribute("August", August);
		model.addAttribute("September", September);
		model.addAttribute("October", October);
		model.addAttribute("November", November);
		model.addAttribute("December", December);
		
		int totalCommand = January + February + March +  April + May + June + July + August + September + October + November + December;
		
		model.addAttribute("totalCommand", totalCommand);
		
		return "statistical";
	}
}

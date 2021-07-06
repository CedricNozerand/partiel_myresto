package com.ensup.myresto.service;

import java.util.List;
import java.util.Set;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.User;

public interface CommandService
{
	public Command getActiveCommandOrCreateOne(User user);
	
	public Command save(Command command);

	public Command getCommandById(Long commandId);

	public List<Command> sort(Set<Command> unsortedCommands);

	List<Command> getAllCommandsByStatut(String statut);
	
	List<Command> getAllCommands();


	Command changeStatus(Command command);

	
}

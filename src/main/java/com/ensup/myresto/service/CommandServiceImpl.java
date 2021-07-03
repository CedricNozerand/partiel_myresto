package com.ensup.myresto.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.CommandStatus;
import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.domaine.User;
import com.ensup.myresto.repository.CommandRepository;

@Service
public class CommandServiceImpl implements CommandService
{
	@Autowired
	CommandRepository commandRepository;

	@Override
	public Command getActiveCommandOrCreateOne(User user)
	{
		Set<Command> commands = user.getCommands();
		System.out.println("#######");
		System.out.println("#######");
		System.out.println("#######");
		System.out.println("Commandes de l'utilisateur " + user.getFirstName() + " :");
		System.out.println(commands);
		
		if (commands != null)
		{
			for (Command command : commands)
			{
				System.out.println("#######");
				System.out.println("#######");
				System.out.println("#######");
				System.out.println("Commande : Id = " + command.getId() + ", Status = " + command.getStatus());
				if (command.getStatus() == CommandStatus.Active)
				{
					System.out.println("#######");
					System.out.println("#######");
					System.out.println("#######");
					System.out.println("Commande trouvée !");
					return command;
				}
			}
		}

		System.out.println("#######");
		System.out.println("#######");
		System.out.println("#######");
		System.out.println("Aucune commande trouvée !");
		// If no active command is found, create one
		Collection<Product> products = new ArrayList<Product>();
		Command command = new Command(new Date(), user, products, CommandStatus.Active);
		
		return commandRepository.save(command);
	}

	public Command save(Command command)
	{
		return commandRepository.save(command);
	}

	@Override
	public Command getCommandById(Long commandId)
	{
		return commandRepository.getById(commandId);
	}

	@Override
	public List<Command> sort(Set<Command> unsortedCommands)
	{
		List<Command> activeCommands = new ArrayList<Command>(),
			paidCommands = new ArrayList<Command>(),
			closedCommands = new ArrayList<Command>();
		
		for (Command command : unsortedCommands)
		{
			if (command.getStatus() == CommandStatus.Active)
				activeCommands.add(command);
			else if (command.getStatus() == CommandStatus.Paid)
				paidCommands.add(command);
			else if (command.getStatus() == CommandStatus.Closed)
				closedCommands.add(command);
		}
		
		List<Command> sortedCommands = new ArrayList<Command>();
		
		sortedCommands.addAll(activeCommands);
		sortedCommands.addAll(paidCommands);
		sortedCommands.addAll(closedCommands);
		
		return sortedCommands;
	}
}

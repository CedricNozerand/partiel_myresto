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

/**
 * Classe d'implémentation de l'interface CommandServicce
 * @author fatim
 *
 */
@Service
public class CommandServiceImpl implements CommandService
{
	/**
	 * Injection de la dépendance du repository
	 */
	@Autowired
	CommandRepository commandRepository;

	/**
	 * Recuper la commande active ou crée en une
	 * @param prend en parametre un utilisateur
	 * @return renvoie la commande
	 */
	@Override
	public Command getActiveCommandOrCreateOne(User user)
	{
		Set<Command> commands = user.getCommands();
		System.out.println("Commandes de l'utilisateur " + user.getFirstName() + " :");
		System.out.println(commands);
		
		if (commands != null)
		{
			for (Command command : commands)
			{

				System.out.println("Commande : Id = " + command.getId() + ", Status = " + command.getStatus());
				if (command.getStatus() == CommandStatus.Active)
				{
					System.out.println("Commande trouvée !");
					return command;
				}
			}
		}

		System.out.println("Aucune commande trouvée !");
		// If no active command is found, create one
		Collection<Product> products = new ArrayList<Product>();
		Command command = new Command(new Date(), user, products, CommandStatus.Active);
		
		return commandRepository.save(command);
	}

	/**
	 * Ajoute une commande 
	 * @param prend en parametre un objet de type Command
	 * @return retourne la commande créer
	 */
	@Override
	public Command save(Command command)
	{
		return commandRepository.save(command);
	}
	
	/**
	 * Elle permet de rechercher une commande par son id
	 * @param prend en parametre un id de type Long
	 * @return retourne la commande trouvée
	 */
	@Override
	public Command getCommandById(Long commandId)
	{
		return commandRepository.getById(commandId);
	}
	
	
	/**
	 * Trie les commandes par status
	 * @param prend en parametre une liste de commande non triée
	 */
	@Override
	public List<Command> sort(Set<Command> unsortedCommands)
	{
		List<Command> activeCommands = new ArrayList<Command>(),
			paidCommands = new ArrayList<Command>(),
			closedCommands = new ArrayList<Command>(),
			inProcessCommands = new ArrayList<Command>();
		
		for (Command command : unsortedCommands)
		{
			if (command.getStatus() == CommandStatus.Active)
				activeCommands.add(command);
			else if (command.getStatus() == CommandStatus.Paid)
				paidCommands.add(command);
			else if (command.getStatus() == CommandStatus.InProcess)
				inProcessCommands.add(command);
			else if (command.getStatus() == CommandStatus.Closed)
				closedCommands.add(command);
		}
		
		List<Command> sortedCommands = new ArrayList<Command>();
		
		sortedCommands.addAll(activeCommands);
		sortedCommands.addAll(paidCommands);
		sortedCommands.addAll(inProcessCommands);
		sortedCommands.addAll(closedCommands);
		
		return sortedCommands;
	}


	/**
	 * Récupere la liste de tous des commandes (payé,en cours,terminé)
	 *  pour l'administration
	 *  @return renvoi la liste des commandes
	 */
	@Override
	public List<Command> getAllCommands()
	{
		List<Command> listCommandsPaidProcessClosed = new ArrayList<Command>();
		for (Command command : commandRepository.findAll()) {
			if(command.getStatus() == CommandStatus.Paid)
				listCommandsPaidProcessClosed.add(command);
			else if (command.getStatus() == CommandStatus.InProcess)
				listCommandsPaidProcessClosed.add(command);
			else if (command.getStatus() == CommandStatus.Closed)
				listCommandsPaidProcessClosed.add(command);
		}
		return listCommandsPaidProcessClosed;
	}
	
	/**
	 * Change le status d'une commande pour l'administration
	 * @param prend en parametre une commande
	 * @return retourne la meme commande avec le status modifié en fonction du status précédant
	 */
	@Override
	public Command changeStatus(Command command) {
		switch (command.getStatus())
		{
		case Paid:
			command.setStatus(CommandStatus.InProcess);
			break;
			
		case InProcess:
			command.setStatus(CommandStatus.Closed);
			break;
			
			default:
				break;
		}
		
		return commandRepository.save(command);
	}


}

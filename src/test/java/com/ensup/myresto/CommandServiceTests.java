package com.ensup.myresto;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ensup.myresto.domaine.Command;
import com.ensup.myresto.domaine.CommandStatus;
import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.domaine.Role;
import com.ensup.myresto.domaine.User;
import com.ensup.myresto.service.CommandService;
import com.ensup.myresto.repository.CommandRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommandServiceTests
{
	@Autowired
	private CommandService commandService;
	
	@MockBean
	private CommandRepository commandRepository;
	
	/**
	 * Vérifie qu'une que la commande avec le statut Active est bien récupérée lorsqu'elle existe.
	 */
	@Test
	public void getActiveCommandOrCreateOne_getActive()
	{
		User user = new User("", "", "", "", "", "", new ArrayList<Role>());
		Command command = new Command(new Date(), user, new ArrayList<Product>(), CommandStatus.Active);
		
		Set<Command> commands = new HashSet<Command>();
		
		commands.add(command);
		
		user.setCommands(commands);
		
	    Mockito.when(commandRepository.save(Mockito.any(Command.class))).thenAnswer(m -> m.getArguments()[0]);
		
		Command returnCommand = commandService.getActiveCommandOrCreateOne(user);
		
		verify(commandRepository, never()).save(Mockito.any(Command.class));
		
		Assert.assertTrue(returnCommand.toString().equals(command.toString()));
	}
	
	/**
	 * Vérifie qu'une commande avec le statut Active est bien créée lorsqu'aucune commande avec le statut Active n'existe.
	 */
	@Test
	public void getActiveCommandOrCreateOne_createOne()
	{
		User user = new User("", "", "", "", "", "", new ArrayList<Role>());
		
		// Create multiple command with different status (no Active status)
		Command command = new Command(new Date(), user, new ArrayList<Product>(), CommandStatus.Closed);
		Command command2 = new Command(new Date(), user, new ArrayList<Product>(), CommandStatus.InProcess);
		Command command3 = new Command(new Date(), user, new ArrayList<Product>(), CommandStatus.Paid);
		
		Set<Command> commands = new HashSet<Command>();
		
		commands.add(command);
		commands.add(command2);
		commands.add(command3);
		
		user.setCommands(commands);
		
	    Mockito.when(commandRepository.save(Mockito.any(Command.class))).thenAnswer(m -> m.getArguments()[0]);
		
		Command returnCommand = commandService.getActiveCommandOrCreateOne(user);
		
		verify(commandRepository, times(1)).save(Mockito.any(Command.class));
		
		Assert.assertEquals(returnCommand.getStatus(), CommandStatus.Active);
	}

	/**
	 * Vérifie que la méthode save() du CommandRepository est bien appelée.
	 */
	@Test
	public void save()
	{
		Command command = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.Closed);
		
	    Mockito.when(commandRepository.save(Mockito.any(Command.class))).thenAnswer(m -> m.getArguments()[0]);
		
		Command returnCommand = commandService.save(command);
		
		verify(commandRepository, times(1)).save(Mockito.any(Command.class));
		
		Assert.assertEquals(returnCommand.getStatus(), CommandStatus.Closed);
	}
	
	/**
	 * Vérifie que la méthode getById() du CommandRepository est bien appelée.
	 */
	@Test
	public void getCommandById()
	{
		Command command = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.Closed);
		
	    Mockito.when(commandRepository.getById(Mockito.anyLong())).thenReturn(command);
		
		Command returnCommand = commandService.getCommandById(1L);
		
		verify(commandRepository, times(1)).getById(Mockito.anyLong());
		
		Assert.assertEquals(returnCommand.getStatus(), CommandStatus.Closed);
	}
	
	/**
	 * Vérifie que la méthode sort() du CommandService trie les commandes dans le bon ordre.
	 */
	@Test
	public void sort()
	{
		Command command = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.Active);
		Command command2 = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.Paid);
		Command command3 = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.InProcess);
		Command command4 = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.Closed);
		
		Set<Command> unsortedCommands = new HashSet<Command>();
		
		// Add in disorder
		unsortedCommands.add(command2);
		unsortedCommands.add(command4);
		unsortedCommands.add(command3);
		unsortedCommands.add(command);
		
		List<Command> sortedCommands = commandService.sort(unsortedCommands);
		
		Assert.assertEquals(sortedCommands.get(0).getStatus(), command.getStatus());
		Assert.assertEquals(sortedCommands.get(1).getStatus(), command2.getStatus());
		Assert.assertEquals(sortedCommands.get(2).getStatus(), command3.getStatus());
		Assert.assertEquals(sortedCommands.get(3).getStatus(), command4.getStatus());
	}
	
	/**
	 * Vérifie que la méthode findAll() du CommandRepository est bien appelée.
	 */
	@Test
	public void getAllCommands()
	{
	    Mockito.when(commandRepository.findAll()).thenReturn(new ArrayList<Command>());
		
		commandService.getAllCommands();
		
		verify(commandRepository, times(1)).findAll();
	}
	
	/**
	 * Vérifie que le statut est bien "incrémenté" lorsqu'on appelle la méthode changeStatus().
	 */
	@Test
	public void changeStatus()
	{
		Command command = new Command(new Date(), null, new ArrayList<Product>(), CommandStatus.Paid);
		
		commandService.changeStatus(command);
		
		Assert.assertEquals(command.getStatus(), CommandStatus.InProcess);
		
		commandService.changeStatus(command);
		
		Assert.assertEquals(command.getStatus(), CommandStatus.Closed);
	}
	
	/**
	 * Vérifie que la méthode getAllCommandByStatut() du CommandRepository est bien appelée.
	 */
	@Test
	public void getAllCommandsByStatut()
	{
	    Mockito.when(commandRepository.getAllCommandByStatut(Mockito.anyString())).thenReturn(new ArrayList<Command>());
		
		commandService.getAllCommandsByStatut("test");
		
		verify(commandRepository, times(1)).getAllCommandByStatut(Mockito.anyString());
	}
}

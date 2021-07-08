package com.ensup.myresto;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.ensup.myresto.domaine.Role;
import com.ensup.myresto.domaine.User;
import com.ensup.myresto.repository.UserRepository;
import com.ensup.myresto.service.UserService;
import com.ensup.myresto.web.dto.UserRegistrationDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests
{
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	/**
	 * Vérifie que la méthode save() du UserRepository est bien appelée.
	 */
	@Test
	public void save()
	{
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto("test name", "", "", "", "", "", "");
		
	    Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
		
	    userService.save(userRegistrationDto);
		
		verify(userRepository, times(1)).save(Mockito.any(User.class));
	}
	
	/**
	 * Vérifie que le UserDetails retourné par la méthode findByEmail() du UserRepository correspond au User passé en argument.
	 */
	@Test
	public void loadUserByUsername_userExists()
	{
		User user = new User("test name", "", "test email", "", "", "test password", new ArrayList<Role>());
		
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
		
		UserDetails returnUserDetails = userService.loadUserByUsername("test email");
		
		Assert.assertTrue(user.getEmail().equals(returnUserDetails.getUsername()));
		
		verify(userRepository, times(1)).findByEmail(Mockito.anyString());
	}
	
	/**
	 * Vérifie qu'un User inexistant à l'email passé en argument de la méthode findByEmail() lève bien une exception.
	 */
	@Test
	public void loadUserByUsername_userNotExists()
	{
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		
		Assert.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("test email"));
		
		verify(userRepository, times(1)).findByEmail(Mockito.anyString());
	}
	
	/**
	 * Vérifie que la méthode findByEmail() du UserRepository est bien appelée.
	 */
	@Test
	public void findByEmail()
	{
	    Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new User());
		
	    userService.findByEmail("test email");
		
		verify(userRepository, times(1)).findByEmail(Mockito.anyString());
	}
}

package com.ensup.myresto.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensup.myresto.domaine.Role;
import com.ensup.myresto.domaine.User;
import com.ensup.myresto.repository.UserRepository;
import com.ensup.myresto.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/**
	 * Enregistre un utilisateur de type membre
	 * @param registrationDto:  prend en parametre registrationDto
	 * @return retourne l'utilisateur enregister
	 */
	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), 
				registrationDto.getEmail(), 
				registrationDto.getAdress(), 
				registrationDto.getPhoneNumber(), 
				passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(new Role("MEMBER")));
		return userRepository.save(user);
	}

	/**
	 * Récupere un utilisateur en fonction de son username
	 * @param username : prend en parametre un username de type string
	 * @return retourne un userDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Email ou mot de passe incorrect");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	/**
	 * Récupere les roles qu'un utilisateur a
	 * @param roles
	 * @return retourne une collection de role
	 */
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	/**
	 * Récupere un utilisateur en fonction de son email
	 * @param  email : prend en paramétre  un email de type String
	 * @return retrourne l'utilisateur trouvé
	 */
	@Override
	public User findByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
}

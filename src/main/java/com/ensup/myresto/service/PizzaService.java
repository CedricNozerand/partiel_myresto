package com.ensup.myresto.service;

import java.util.List;

import com.ensup.myresto.domaine.Pizza;

public interface PizzaService {

	List<Pizza> getAllPizzas();
	
	Pizza getPizzaByID(long id);
	
	Pizza getPizzaByName(String name);
}

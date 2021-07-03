package com.ensup.myresto.service;

import java.util.List;

import com.ensup.myresto.domaine.Product;

public interface PizzaService {

	List<Product> getAllPizzas();
	
	Product getPizzaByID(long id);
	
	Product getPizzaByName(String name);
}

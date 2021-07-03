package com.ensup.myresto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.repository.ProductRepository;

public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private ProductRepository pizzaRepository;
	
	@Override
	public List<Product> getAllPizzas() {
		return pizzaRepository.findAll();
	}

	@Override
	public Product getPizzaByID(long id) {
		Optional<Product> optional = pizzaRepository.findById(id);
		Product pizza = null;
		if(optional.isPresent()) {
			pizza = optional.get();
		}else {
			throw new RuntimeException("La pizza #" + id + " est introuvable");
		}
		return pizza;
	}

	@Override
	public Product getPizzaByName(String name) {
		Product pizza = pizzaRepository.findByName(name);
		if(pizza != null) {
			return pizza;
		}
		throw new RuntimeException("La pizza " + name + " est introuvable");
	}
}

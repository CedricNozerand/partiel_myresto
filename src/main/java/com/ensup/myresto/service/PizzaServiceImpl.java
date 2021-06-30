package com.ensup.myresto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensup.myresto.domaine.Pizza;
import com.ensup.myresto.repository.PizzaRepository;

public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
	}

	@Override
	public Pizza getPizzaByID(long id) {
		Optional<Pizza> optional = pizzaRepository.findById(id);
		Pizza pizza = null;
		if(optional.isPresent()) {
			pizza = optional.get();
		}else {
			throw new RuntimeException("La pizza #" + id + " est introuvable");
		}
		return pizza;
	}

	@Override
	public Pizza getPizzaByName(String name) {
		Pizza pizza = pizzaRepository.findByName(name);
		if(pizza != null) {
			return pizza;
		}
		throw new RuntimeException("La pizza " + name + " est introuvable");
	}
}

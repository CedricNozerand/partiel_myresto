package com.ensup.myresto.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductByID(long id) {
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if(optional.isPresent()) {
			product = optional.get();
		}else {
			throw new RuntimeException("La produit #" + id + " est introuvable");
		}
		return product;
	}

	@Override
	public Product getProductByName(String name) {
		Product product = productRepository.findByName(name);
		if(product != null) {
			return product;
		}
		throw new RuntimeException("La product " + name + " est introuvable");
	}

	@Override
	public List<Product> findByType(String type)
	{
		return productRepository.findByType(type);
	}
	
	@Override
	public Set<Product> getAllProductCommand(Long id){
		return productRepository.findAllProductByCommand(id);
	}
}

package com.ensup.myresto.service;

import java.util.List;

import com.ensup.myresto.domaine.Product;

public interface ProductService
{
	List<Product> getAllProducts();
	
	Product getProductByID(long id);
	
	Product getProductByName(String name);
}

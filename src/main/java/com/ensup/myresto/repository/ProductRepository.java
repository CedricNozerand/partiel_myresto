package com.ensup.myresto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensup.myresto.domaine.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	//@Query("SELECT p FROM pizza WHERE p.name = ?")
	Product findByName(String name);
}

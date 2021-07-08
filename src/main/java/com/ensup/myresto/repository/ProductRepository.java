package com.ensup.myresto.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ensup.myresto.domaine.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	//@Query("SELECT p FROM pizza WHERE p.name = ?")
	Product findByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.type = :type")
	List<Product> findByType(String type);
	
	@Query(value = "select p.id, p.name,p.price,p.description,p.type,p.image from product p , command c ,command_products cp where p.id = cp.products_id and c.id = cp.command_id and c.id = ?1 ",nativeQuery = true)
	Set<Product> findAllProductByCommand(Long id);
}

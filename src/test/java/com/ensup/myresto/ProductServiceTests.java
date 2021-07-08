package com.ensup.myresto;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ensup.myresto.domaine.Product;
import com.ensup.myresto.repository.ProductRepository;
import com.ensup.myresto.service.ProductService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests
{
	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	/**
	 * Vérifie que la méthode getAllProducts() du ProductRepository est bien appelée.
	 */
	@Test
	public void getAllProducts()
	{
	    Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<Product>());
		
	    productService.getAllProducts();
		
		verify(productRepository, times(1)).findAll();
	}
	
	/**
	 * Vérifie qu'un utilisateur qui existe est bien récupéré en appelant la méthode findById() du ProductRepository.
	 */
	@Test
	public void getProductByID_productExists()
	{
		Product product = new Product("test product", "", "", 0, "");
		
		Optional<Product> optional = Optional.of(product);
		
	    Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(optional);
	    
	    Product returnProduct = productService.getProductByID(1);
	    
	    Assert.assertTrue(product.getName().equals(returnProduct.getName()));
		
		verify(productRepository, times(1)).findById(Mockito.anyLong());
	}
	
	/**
	 * Vérifie qu'un utilisateur qui n'existe pas lève bien une RuntimeException en appelant la méthode findById() du ProductRepository.
	 */
	@Test
	public void getProductByID_productNotExists()
	{
		Optional<Product> optional = Optional.empty();
		
	    Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(optional);
	    
	    Assert.assertThrows(RuntimeException.class, () -> productService.getProductByID(1));
		
		verify(productRepository, times(1)).findById(Mockito.anyLong());
	}
	
	/**
	 * Vérifie qu'un utilisateur qui existe est bien récupéré en appelant la méthode findByName() du ProductRepository.
	 */
	@Test
	public void getProductByName_productExists()
	{
		Product product = new Product("test product", "", "", 0, "");
		
	    Mockito.when(productRepository.findByName(Mockito.anyString())).thenReturn(product);
	    
	    Product returnProduct = productService.getProductByName("test product");
	    
	    Assert.assertTrue(product.getName().equals(returnProduct.getName()));
		
		verify(productRepository, times(1)).findByName(Mockito.anyString());
	}
	
	/**
	 * Vérifie qu'un utilisateur qui n'existe pas lève bien une RuntimeException en appelant la méthode findByName() du ProductRepository.
	 */
	@Test
	public void getProductByName_productNotExists()
	{
	    Mockito.when(productRepository.findByName(Mockito.anyString())).thenReturn(null);
	    
	    Assert.assertThrows(RuntimeException.class, () -> productService.getProductByName("test product"));
		
		verify(productRepository, times(1)).findByName(Mockito.anyString());
	}
	
	/**
	 * Vérifie que la méthode findByType() du ProductRepository est bien appelée.
	 */
	@Test
	public void findByType()
	{
	    Mockito.when(productRepository.findByType(Mockito.anyString())).thenReturn(new ArrayList<Product>());
		
	    productService.findByType("type");
		
		verify(productRepository, times(1)).findByType(Mockito.anyString());
	}
}

package com.ensup.myresto.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="product", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Product {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private String image;
	private double price;
	private String type;
	
	public Product() {
		
	}

	public Product(String name, String description, String image, double price, String type) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

package com.ensup.myresto.domaine;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date date;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@Enumerated(EnumType.STRING)
	private CommandStatus status;

	@ManyToMany
	private Collection<Product> products;

	public Command() {
		
	}
	
	public Command(Date date, User user, Collection<Product> products, CommandStatus status) {
		super();
		this.date = date;
		this.user = user;
		this.products = products;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public CommandStatus getStatus()
	{
		return status;
	}

	public void setStatus(CommandStatus status)
	{
		this.status = status;
	}
	
	public Collection<Product> getProducts()
	{
		return products;
	}

	public void setProducts(Collection<Product> products)
	{
		this.products = products;
	}
	
	public double getPrice()
	{
		double price = 0d;
		
		for (Product product : products)
		{
			price += product.getPrice();
		}
		
		return price;
	}
}

package com.ensup.myresto.domaine;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String date;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private Collection<Product> products;
	
	public Command() {
		
	}
	
	public Command(String date, User user, Collection<Product> products) {
		super();
		this.date = date;
		this.user = user;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

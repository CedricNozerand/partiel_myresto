package com.ensup.myresto.web.dto;

public class UserRegistrationDto {

	private String firstName;
	private String lastName;
	private String email;
	private String adress;
	private String phoneNumber;
	private String password;
	private String passwordConfirmation;
	
	public UserRegistrationDto() {
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String adress, String phoneNumber, String password, String passwordConfirmation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}

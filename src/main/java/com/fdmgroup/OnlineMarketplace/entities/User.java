package com.fdmgroup.OnlineMarketplace.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String email;
	
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Item> itemsOnForSale;
	
	@OneToMany
	private List<Review> reviewsMade;
	
	public User() {
		super();
	}

	public User(String username, String password, String firstName, String lastName, LocalDate dob, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
	}

	public User(String username, String password, String firstName, String lastName, LocalDate dob, String email,
			List<Item> itemsOnForSale, List<Review> reviewsMade) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.itemsOnForSale = itemsOnForSale;
		this.reviewsMade = reviewsMade;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Item> getItemsOnForSale() {
		return itemsOnForSale;
	}

	public void setItemsOnForSale(List<Item> itemsOnForSale) {
		this.itemsOnForSale = itemsOnForSale;
	}

	public List<Review> getReviewsMade() {
		return reviewsMade;
	}

	public void setReviewsMade(List<Review> reviewsMade) {
		this.reviewsMade = reviewsMade;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

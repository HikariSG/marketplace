package com.fdmgroup.OnlineMarketplace.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long addressId;
	
	@NonNull
	private String street;
	
	@NonNull
	private String houseNumber;
	
	@NonNull
	private String city;
	
	@NonNull
	private String country;
	
	@NonNull
	private int zipCode;

	public Address(@NonNull String street, @NonNull String houseNumber, @NonNull String city, @NonNull String country,
			@NonNull int zipCode) {
		super();
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}
}

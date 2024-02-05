package com.fdmgroup.OnlineMarketplace.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@NonNull
	@Column(nullable = false, unique = true)
	private String username;
	
	@NonNull
	@Column(nullable = false)
	private String password;
	
	@NonNull
	@Column(nullable = false)
	private String firstName;
	
	@NonNull
	@Column(nullable = false)
	private String lastName;
	
	@NonNull
	@Column(nullable = false)
	private LocalDate dob;
	
	@NonNull
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
    		flags = Pattern.Flag.CASE_INSENSITIVE)
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull
	private Roles role = Roles.ROLE_USER;
	
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Item> itemsOnForSale;
	
	@OneToMany
	private List<Review> reviewsMade;

	public User(@NonNull String username, @NonNull String password, @NonNull String firstName, @NonNull String lastName,
			@NonNull LocalDate dob,
			@NonNull @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE) String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
	}	
}

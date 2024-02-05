package com.fdmgroup.OnlineMarketplace.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long transactionId;
	
	@NonNull
	private LocalDate transactionDate;
	
	@NonNull
	@OneToOne
	private User seller;
	
	@NonNull
	@OneToOne
	private User buyer;
	
	@NonNull
	@OneToOne
	private Item item;
	
	@NonNull
	private String paymentMethod;
	
	@NonNull
	private BigDecimal transactionPrice;
}

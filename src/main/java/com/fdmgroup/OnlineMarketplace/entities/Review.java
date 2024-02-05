package com.fdmgroup.OnlineMarketplace.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long reviewId;
	
	@NonNull
	private String reviewContent;	
	private double reviewRating;
}

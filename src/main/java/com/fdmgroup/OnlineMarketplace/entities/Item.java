package com.fdmgroup.OnlineMarketplace.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemId;
	
	@NonNull
	@Column (nullable = false, unique = true)
	private String itemName;
	
	private BigDecimal startPrice;
	private BigDecimal buyOutPrice;
	
	@NonNull
	@Column (nullable = false)
	private String itemDescription;
	
	@Column (nullable = false)
	private LocalDate listDate = LocalDate.now();
	
	@Column (nullable = false)
	private ItemStatus itemStatus = ItemStatus.Active;

	public Item(@NonNull String itemName, BigDecimal startPrice, BigDecimal buyOutPrice,
			@NonNull String itemDescription) {
		super();
		this.itemName = itemName;
		this.startPrice = startPrice;
		this.buyOutPrice = buyOutPrice;
		this.itemDescription = itemDescription;
	}
}

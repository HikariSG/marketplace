package com.fdmgroup.OnlineMarketplace.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemId;
	
	private String itemName;
	private BigDecimal startPrice;
	private BigDecimal buyOutPrice;
	private String itemDescription;
	
	public Item() {
		super();

	}

	public Item(String itemName, BigDecimal startPrice, BigDecimal buyOutPrice, String itemDescription) {
		super();
		this.itemName = itemName;
		this.startPrice = startPrice;
		this.buyOutPrice = buyOutPrice;
		this.itemDescription = itemDescription;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getBuyOutPrice() {
		return buyOutPrice;
	}

	public void setBuyOutPrice(BigDecimal buyOutPrice) {
		this.buyOutPrice = buyOutPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
}

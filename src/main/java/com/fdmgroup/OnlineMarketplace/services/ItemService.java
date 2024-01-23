package com.fdmgroup.OnlineMarketplace.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.OnlineMarketplace.entities.Item;
import com.fdmgroup.OnlineMarketplace.exceptions.ItemNotFoundException;
import com.fdmgroup.OnlineMarketplace.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo; 
	
	private Logger logger = LogManager.getLogger();
	
	public List<Item> findAllItems() {
		return itemRepo.findAll();
	}
	
	public Item findByItemId(long itemId) {
		Item existingItem = itemRepo.findById(itemId)
						 .orElseThrow(() -> {
							 logger.error("Item with itemId: " + itemId + " not found");
							 return new ItemNotFoundException ("Item not found");
						 });
		
		return existingItem;
	}
	
	public Item addItem(Item item) {
		return itemRepo.save(item);
	}
	
	public Item updateItemDetails(Map<String, Object> updates, long itemId) {
		Item existingItem = itemRepo.findById(itemId)
							 .orElseThrow(() -> {
								 logger.error("Item with itemId: " + itemId + " not found");
								 return new ItemNotFoundException ("Item not found");
							 });
		
		if (updates.containsKey("itemName")) {
			existingItem.setItemName((String)updates.get("itemName"));
			logger.info("Updated item name for item " + itemId);
		}
		
		if (updates.containsKey("itemDescription")) {
			existingItem.setItemDescription((String)updates.get("itemDescription"));
			logger.info("Updated item description for item " + itemId);
		}
		
		if (updates.containsKey("startPrice")) {
			existingItem.setStartPrice(BigDecimal.valueOf((double)updates.get("startPrice")));
			logger.info("Updated start price for item " + itemId);
		}
		
		if (updates.containsKey("buyOutPrice")) {
			existingItem.setStartPrice(BigDecimal.valueOf((double)updates.get("buyOutPrice")));
			logger.info("Updated buy out price for item " + itemId);
		}
		
		return existingItem;
	}
	
	public Map<String, Boolean> deleteItemByItemId(long itemId){
		Item existingItem = itemRepo.findById(itemId)
							 .orElseThrow(() -> {
								 logger.error("Item with itemId: " + itemId + " not found");
								 return new ItemNotFoundException ("Item not found");
							 });
		
		itemRepo.delete(existingItem);
		logger.info("Successfully deleted item id " + itemId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

}

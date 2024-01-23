package com.fdmgroup.OnlineMarketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.OnlineMarketplace.entities.Item;
import com.fdmgroup.OnlineMarketplace.services.ItemService;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public List<Item> getAllItems(){
		return itemService.findAllItems();
	}
	
	@GetMapping("/{id}")
	public Item getItemById(long itemId) {
		return itemService.findByItemId(itemId);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Item> addNewItem(@RequestBody Item item){
		Item savedItem = itemService.addItem(item);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id})")
						.buildAndExpand(savedItem.getItemId()).toUri();
		
		return ResponseEntity.created(location).body(savedItem);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Item> updateItem(@RequestBody Map<String, Object> updates, 
											@PathVariable (value = "id") long itemId){
		Item updatedItem = itemService.updateItemDetails(updates, itemId);
		
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteItem (@PathVariable(value = "id") long itemId){
		return itemService.deleteItemByItemId(itemId);
	}
}
	
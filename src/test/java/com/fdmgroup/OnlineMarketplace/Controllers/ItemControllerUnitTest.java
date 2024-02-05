package com.fdmgroup.OnlineMarketplace.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.OnlineMarketplace.controllers.ItemController;
import com.fdmgroup.OnlineMarketplace.entities.Item;
import com.fdmgroup.OnlineMarketplace.services.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ItemService mockItemService;
	
	@InjectMocks
	private ItemController itemController;
	
	private Item testItem;
	private Logger logger = LogManager.getLogger();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		testItem = new Item("testItem", BigDecimal.valueOf(1.99), 
							BigDecimal.valueOf(20.99), "test product");
	}
	
	@Test
	public void testGetAllItems() throws Exception{
		List<Item> itemList = new ArrayList<>();
		itemList.add(testItem);
		
		when(mockItemService.findAllItems())
			.thenReturn(itemList);
		
		mockMvc.perform(get("/api/v1/items"))
			   .andExpect(status().isOk())	
			   .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
	}
	
	@Test
	public void testGetItemById() throws Exception{
		when(mockItemService.findByItemId(1))
			.thenReturn(testItem);

		mockMvc.perform(get("/api/v1/items/" + 1))
			   .andExpect(status().isOk())
			   .andExpect(MockMvcResultMatchers.jsonPath("$.itemName").value("testItem"));
	}
	
	@Test
	public void testAddItem() throws Exception{
		when (mockItemService.addItem(any(Item.class)))
			.thenReturn(testItem);
		
		Item newItem = new Item();
		
		mockMvc.perform(post("/api/v1/items/add")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(newItem)))
			   .andExpect(status().isCreated())
			   .andExpect(MockMvcResultMatchers.jsonPath("$.itemName").value("testItem"));
			
	}
	
	@Test
	public void testUpdateItemById() throws Exception{
		Map<String, Object> testMap = new HashMap<>();
		
		when (mockItemService.updateItemDetails(testMap, 1L))
			.thenReturn(testItem);
		
		mockMvc.perform(patch("/api/v1/items/" + 1)
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(testMap)))
			   .andExpect(status().isOk());
	}
	
}

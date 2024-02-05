package com.fdmgroup.OnlineMarketplace.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdmgroup.OnlineMarketplace.controllers.ItemController;
import com.fdmgroup.OnlineMarketplace.entities.Item;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationtest {
	
	@Autowired
	private ItemController itemController;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private Logger logger = LogManager.getLogger();
	
	@Test
	public void testAddNewItem_validItem_thenStatusCreated() throws Exception {	
		logger.info("Testing add valid item");
		
		Item newItem = new Item("testValidItem", BigDecimal.valueOf(1.99), 
								 BigDecimal.valueOf(20.00), "testing item");

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/items/add", 
											newItem, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		logger.info("Testing add valid item success");
	}
	
	@Test
	public void testAddItem_missingItemName_thenFail() throws Exception {
		logger.info("Testing failure of adding item when missing item name");
		
		Item missingItemName = new Item();
		missingItemName.setItemDescription("test");
		missingItemName.setBuyOutPrice(BigDecimal.valueOf(100.0));
		missingItemName.setStartPrice(BigDecimal.valueOf(20.0));
		
		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/items/add", 
											missingItemName, String.class);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
		logger.info("Testing of failure to add item with missing item name success");
	}
}

package com.fdmgroup.OnlineMarketplace;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.OnlineMarketplace.entities.Address;
import com.fdmgroup.OnlineMarketplace.entities.Item;
import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.repositories.AddressRepository;
import com.fdmgroup.OnlineMarketplace.repositories.ItemRepository;
import com.fdmgroup.OnlineMarketplace.repositories.TransactionRepository;
import com.fdmgroup.OnlineMarketplace.repositories.UserRepository;
import com.fdmgroup.OnlineMarketplace.services.UserService;


@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@Autowired
	UserService userService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// Address
		Address address1 = new Address("Happy Street 1", "11B", "Singapore", "Singapore", 310011);
		addressRepo.save(address1);
		
		Address address2 = new Address("Woolands Street 2", "200", "Singapore", "Singapore", 700200);
		addressRepo.save(address2);
		
		// Users
		User user1 = new User("user1", "password123", "John", "Smith", 
							  LocalDate.of(1990, 1, 1), "user1@gmail.com");
		user1.setAddress(address1);		
		userService.addUser(user1);
		
		User user2 = new User("user2", "password456", "Jane", "Smith", 
							  LocalDate.of(1999, 2, 2), "user2@outlook.com");
		user2.setAddress(address2);
		userService.addUser(user2);
				
		// Items
		Item item1 = new Item("Iphone 9", BigDecimal.valueOf(499.99), 
								BigDecimal.valueOf(499.99), "Brand new, unopened");
		itemRepo.save(item1);
	}
}

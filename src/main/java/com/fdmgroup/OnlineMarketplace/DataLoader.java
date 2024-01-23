package com.fdmgroup.OnlineMarketplace;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.OnlineMarketplace.entities.Address;
import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.repositories.AddressRepository;
import com.fdmgroup.OnlineMarketplace.repositories.UserRepository;


@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user1 = new User("user1", "password123", "John", "Smith", LocalDate.of(1990, 1, 1), "user1@gmail.com");
		
		Address address1 = new Address("Happy Street 1", "11B", "Singapore", "Singapore", 310011);
		addressRepo.save(address1);
		user1.setAddress(address1);
		
		userRepo.save(user1);
	}
}

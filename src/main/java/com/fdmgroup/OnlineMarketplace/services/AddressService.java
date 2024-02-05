package com.fdmgroup.OnlineMarketplace.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.OnlineMarketplace.entities.Address;
import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.exceptions.UserNotFoundException;
import com.fdmgroup.OnlineMarketplace.repositories.AddressRepository;
import com.fdmgroup.OnlineMarketplace.repositories.UserRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	UserRepository userRepo;
	
	private Logger logger = LogManager.getLogger();	
	
	public Address findAddress(long userId) {
		User existingUser = userRepo.findById(userId).orElseThrow(() -> {
							logger.error("User with userId " + userId + " not found");
							return new UserNotFoundException("User not found");
						});
		
		logger.info("Retrieved address of user: " + userId);
		
		return addressRepo.findByUser(existingUser);
	}
	
}
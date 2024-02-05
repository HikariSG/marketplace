package com.fdmgroup.OnlineMarketplace.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.OnlineMarketplace.entities.Address;
import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.exceptions.DuplicateEmailException;
import com.fdmgroup.OnlineMarketplace.exceptions.DuplicateUsernameException;
import com.fdmgroup.OnlineMarketplace.exceptions.MissingUsernameException;
import com.fdmgroup.OnlineMarketplace.exceptions.UserNotFoundException;
import com.fdmgroup.OnlineMarketplace.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = LogManager.getLogger(UserService.class);
	
	public User findByUserId(long userId) {
		User existingUser = userRepo.findById(userId).orElseThrow(() -> {
							logger.error("User with userId " + userId + " not found");
							return new UserNotFoundException("User not found");
						});

		logger.info("Found user with user id " + userId);
		return existingUser;
	}

	public List<User> findAllUsers() {
		List<User> userList = userRepo.findAll();
		return userList;
	}
	
	public User addUser(User user){		
		
		String str = user.getUsername();
		
		if (userRepo.findByUsername(user.getUsername()).isPresent()){
			throw new DuplicateUsernameException("Username is already in use");
		}
		
		if (userRepo.findByEmail(user.getEmail()).isPresent()) {
			throw new DuplicateEmailException("Email is already in use");
		}
		
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		System.out.println("Hashed: " + hashedPassword);
		user.setPassword(hashedPassword);
		try {
			userRepo.save(user);
		} catch(MissingUsernameException e) {
			logger.error("Missing username");
		}
		
		logger.info("Saved new user");
		return userRepo.save(user);
	}
	
	public User updateUserDetails(Map<String, Object> updates, long userId) {
		User existingUser = userRepo.findById(userId).orElseThrow(() -> {
							logger.error("User with userId " + userId + " not found");
							return new UserNotFoundException("User not found");
						});
		
		if (updates.containsKey("password")) {
			existingUser.setPassword((String)updates.get("password"));
			logger.info("Updating password for user id " + userId);
		}
		
		if (updates.containsKey("email")) {
			existingUser.setEmail((String)updates.get("email"));
			logger.info("Updating email for user id " + userId);
		}
		
		if (updates.containsKey("firstName")) {
			existingUser.setFirstName((String)updates.get("firstName"));
			logger.info("Updating first name for user id " + userId);
		}
		
		if (updates.containsKey("lastName")) {
			existingUser.setLastName((String)updates.get("lastName"));
			logger.info("Updating last name for user id " + userId);
		}
		
		if (updates.containsKey("address")) {
			existingUser.setAddress((Address)updates.get("address"));
			logger.info("Updating address for user id " + userId);
		}
		
		return userRepo.save(existingUser);
	}

	public Map<String, Boolean> deleteUserByUserId(long userId) {
		User existingUser = userRepo.findById(userId).orElseThrow(() -> {
							logger.error("User with userId " + userId + " not found");
							return new UserNotFoundException("User not found");
						});
		
		userRepo.delete(existingUser);
		logger.info("Successfully deleted user id " + userId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}

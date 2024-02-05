package com.fdmgroup.OnlineMarketplace.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.services.UserService;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testAddNewUser_validUser_thenReturnUser() {
		User testValidUser = new User("test1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test1@gmail.com");
		
		User savedUser = userService.addUser(testValidUser);
		
		assertEquals(savedUser.getUsername(), testValidUser.getUsername());
	}
}

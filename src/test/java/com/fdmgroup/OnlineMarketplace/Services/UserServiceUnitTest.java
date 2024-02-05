package com.fdmgroup.OnlineMarketplace.Services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.exceptions.MissingUsernameException;
import com.fdmgroup.OnlineMarketplace.repositories.UserRepository;
import com.fdmgroup.OnlineMarketplace.services.UserService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	private User testUser;
	private Logger logger = LogManager.getLogger();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);	
		testUser = new User("test1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test@gmail.com");
	}
	
	@Test
	public void testAddUser_withValidUser_returnSuccess() {
		logger.info("Testing add user with valid parameters");
		
		when(userRepository.save(any(User.class)))
			 .thenReturn(testUser);
		
		User savedUser = userService.addUser(testUser);
		
		assertNotNull(savedUser);
		
		
		logger.info("Testing add user with valid parameters successful");
	}
	
	@Ignore
	public void testAddUser_withMissingUsername_thenReturnFailure() {
		logger.info("Testing add user with missing username");
		
		User missingUsername = new User();
		
		when(userRepository.save(any(User.class)))
		 .thenReturn(missingUsername);
		
		assertThrows(MissingUsernameException.class, () -> {
            userService.addUser(missingUsername);
        });
	}
	
	@Test
	public void findUser_withValidUserId_thenReturnUser() {
		logger.info("Testing find user with existing user Id");
		
		when(userRepository.findById(1L))
			.thenReturn(Optional.of(testUser));
		
		User savedUser = userService.findByUserId(1L);
		
		assertNotNull(savedUser);
	}
	
	@Test
	public void findAllUsers_returnListOfUsers() {
		logger.info("Testing find all users");
		
		List<User>userList = new ArrayList<>();
		userList.add(testUser);
		
		when(userRepository.findAll())
			.thenReturn(userList);
		
		assertEquals(1, userService.findAllUsers().size());
	}
	
	@Test
	public void updateUser_withValidUserId_returnUser() {
		logger.info("Testing updates user");
		
		when(userRepository.findById(1L))
			 .thenReturn(Optional.of(testUser));
		
		when(userRepository.save(any(User.class)))
			 .thenReturn(testUser);
		
		String newUsername = "updatedUsername";
		
		Map<String, Object> updates = new HashMap<>();
		updates.put("username", newUsername);
		testUser.setUsername(newUsername);
		
		User updatedUser = userService.updateUserDetails(updates, 1L);
		
		assertEquals(newUsername, updatedUser.getUsername());
	}
	
	@Test 
	public void testDeleteUser_withValidUserId(){
		logger.info("Testing delete user with existing userId");
		
		doNothing().when(userRepository).delete(any(User.class));
		
		when(userRepository.findById(1L))
		 .thenReturn(Optional.of(testUser));
		
		userService.deleteUserByUserId(1L);
		
		verify(userRepository, times(1)).delete(testUser);
	}
}

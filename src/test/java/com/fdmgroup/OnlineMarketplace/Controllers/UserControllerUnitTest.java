package com.fdmgroup.OnlineMarketplace.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
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
import com.fdmgroup.OnlineMarketplace.controllers.UserController;
import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService mockUserService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@InjectMocks
	private UserController userController;
	
	private User testValidUser;
	private Logger logger = LogManager.getLogger();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		testValidUser = new User("test1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test@gmail.com");		
	}

	@Test
	public void testAddNewUser_sucess() throws Exception{	
		
		when (mockUserService.addUser(any(User.class)))
			.thenReturn(testValidUser);
		
		User newUser = new User("test2", "testPW", "testFN", 
								"testLN", LocalDate.of(1990, 10, 10), "test@gmail.com");
		
		mockMvc.perform(post("/api/v1/users/add")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(newUser)))
			   .andExpect(status().isCreated());
		
		logger.info("Expected status 200 for valid user creation");
	}
	
	@Ignore
	public void testAddNewUser_duplicateUsername_fail() throws Exception{
		
		User newUser = new User("user1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test@gmail.com");
		
		when (mockUserService.addUser(any(User.class)))
		  .thenReturn(newUser);
		
		mockMvc.perform(post("/api/v1/users/add")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(newUser)))
			   .andExpect(status().isCreated());
		
		mockMvc.perform(post("/api/v1/users/add")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(objectMapper.writeValueAsString(newUser)))
				   .andExpect(status().isInternalServerError());
	}
	
	@Ignore
	public void testAddNewUser_missingUsername_fail() throws Exception{
		User testUser = new User();
		
		when (mockUserService.addUser(any(User.class)))
			 .thenReturn(testUser);
		
		mockMvc.perform(post("/api/v1/users/add")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(testUser)))
			   .andExpect(status().isInternalServerError());
	}
	
	@Ignore
	public void testAddNewUser_invalidEmail_fail() throws Exception{
		User wrongEmailUser = new User ("user1", "testPW", "testFN", 
							 "testLN", LocalDate.of(1990, 10, 10), "testgmail.com");
		
		when (mockUserService.addUser(any(User.class)))
			 .thenReturn(wrongEmailUser);
		
		mockMvc.perform(post("/api/v1/users/add")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(wrongEmailUser)))
			   .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testFindUser() throws Exception{
		when (mockUserService.findByUserId(1))
		 	.thenReturn(testValidUser);
		
		mockMvc.perform(get("/api/v1/users/" + 1)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("test1"));
	}
	
	@Test 
	public void testFindAllUser() throws Exception{
		logger.info("Testing find all users");
		
		List<User> userList = new ArrayList<>();
		userList.add(testValidUser);
		
		when (mockUserService.findAllUsers())
			.thenReturn(userList);
		
		mockMvc.perform(get("/api/v1/users"))
				.andExpect(status().isOk())	
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
		
		logger.info("Test passed for testing find all users");	
	}
	
	@Test
	public void testUpdateUser() throws Exception{
		logger.info("Testing update user");
		
		Map<String, Object> map = new HashMap<>();
		
		when (mockUserService.updateUserDetails(map, 1L))
			  .thenReturn(testValidUser);
		
		mockMvc.perform(patch("/api/v1/users/" + 1)
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(map)))
			   .andExpect(status().isOk());
		
		logger.info("Testing update user complete");
	}
	
	@Test
	public void testDeleteUser_validUserId_success() throws Exception{
		logger.info("Testing update delete user");
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		when (mockUserService.deleteUserByUserId(1L))
			.thenReturn(response);
		
		mockMvc.perform(delete("/api/v1/users/" + 1))
			   .andExpect(status().isOk());
		
		logger.info("Testing update delete user complete");
	}
	
}

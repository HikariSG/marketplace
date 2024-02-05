package com.fdmgroup.OnlineMarketplace.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdmgroup.OnlineMarketplace.controllers.UserController;
import com.fdmgroup.OnlineMarketplace.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
	
	@Autowired
	private UserController userController;
	
	@Autowired 
	TestRestTemplate restTemplate;
	
	@Test
	public void testAddNewUser_validUser_thenStatusCreated() throws Exception {	
		User testValidUser = new User("test1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test1@gmail.com");	

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testValidUser, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
		assertEquals(1, 1);
	}
	
	@Test
	public void testAddNewUser_invalidUserMissingUsername_thenFail() throws Exception {	
		User testInvalidUser = new User();
		testInvalidUser.setFirstName("testFN");
		testInvalidUser.setLastName("testLN");
		testInvalidUser.setDob(LocalDate.of(1990, 10, 10));
		testInvalidUser.setEmail("testUsername@gmail.com");

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testInvalidUser, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_invalidUserMissingFirstName_thenFail() throws Exception {	
		User testInvalidUser = new User();
		testInvalidUser.setUsername("missingFirstName");
		testInvalidUser.setLastName("testLN");
		testInvalidUser.setDob(LocalDate.of(1990, 10, 10));
		testInvalidUser.setEmail("testUsername@gmail.com");

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testInvalidUser, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_invalidUserMissingLastName_thenFail() throws Exception {	
		User testInvalidUser = new User();
		testInvalidUser.setUsername("missingLastName");
		testInvalidUser.setFirstName("testLN");
		testInvalidUser.setDob(LocalDate.of(1990, 10, 10));
		testInvalidUser.setEmail("testUsername@gmail.com");

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testInvalidUser, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_invalidUserMissingDOB_thenFail() throws Exception {	
		User testInvalidUser = new User();
		testInvalidUser.setUsername("missingDOB");
		testInvalidUser.setFirstName("testLN");
		testInvalidUser.setLastName("testLN");
		testInvalidUser.setEmail("testUsername@gmail.com");

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testInvalidUser, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_invalidUserMissingEmail_thenFail() throws Exception {	
		User testInvalidUser = new User();
		testInvalidUser.setUsername("missingDOB");
		testInvalidUser.setFirstName("testLN");
		testInvalidUser.setLastName("testLN");
		testInvalidUser.setDob(LocalDate.of(1990, 10, 10));

		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testInvalidUser, String.class);
        
		// Assert the response status code
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_duplicateUsername_thenFail() throws Exception{
		User testValidUser = new User("test2", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test2@gmail.com");	
		
		User duplicateUsernameUser = new User("test1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test2@gmail.com");	
		
		restTemplate.postForEntity("/api/v1/users/add", testValidUser, String.class);
		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", testValidUser, String.class);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_invalidEmailFormat_thenFail() throws Exception{
		User wrongEmailUser = new User("test3", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test3gmailcom");	
		
		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", wrongEmailUser, String.class);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testAddNewUser_duplicateEmail_thenFail() throws Exception{
		User testUser = new User("test4", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test4@gmail.com");	
		
		User duplicateEmail = new User("test41", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test4@gmail.com");	
		
		restTemplate.postForEntity("/api/v1/users/add", testUser, String.class);
		ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/users/add", duplicateEmail, String.class);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testFindUserById_success() throws Exception {
		
	}
	
}

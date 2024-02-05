package com.fdmgroup.OnlineMarketplace.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdmgroup.OnlineMarketplace.entities.User;
import com.fdmgroup.OnlineMarketplace.repositories.UserRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryUnitTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void init() {
		userRepository.save(new User("test1", "testPW", "testFN", 
				"testLN", LocalDate.of(1990, 10, 10), "test1@gmail.com"));
	}
	
	@AfterEach
	public void destory() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testGetAllUser() {
		List<User> userList = userRepository.findAll();
		
		assertEquals(1, userList.size());
	}
}

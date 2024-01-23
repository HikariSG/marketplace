package com.fdmgroup.OnlineMarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.OnlineMarketplace.entities.Address;
import com.fdmgroup.OnlineMarketplace.entities.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("select a from User u join Address a where u = :existingUser")
	Address findByUser(User existingUser);

}

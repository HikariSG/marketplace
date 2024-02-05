package com.fdmgroup.OnlineMarketplace.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.OnlineMarketplace.entities.User;

import lombok.NonNull;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

}

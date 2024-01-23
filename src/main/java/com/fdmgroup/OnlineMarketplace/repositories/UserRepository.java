package com.fdmgroup.OnlineMarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.OnlineMarketplace.entities.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

}

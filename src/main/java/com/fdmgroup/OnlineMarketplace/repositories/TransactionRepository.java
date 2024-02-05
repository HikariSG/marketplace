package com.fdmgroup.OnlineMarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.OnlineMarketplace.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long>{

}

package com.fdmgroup.OnlineMarketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.OnlineMarketplace.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}

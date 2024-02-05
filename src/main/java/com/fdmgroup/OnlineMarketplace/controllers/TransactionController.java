package com.fdmgroup.OnlineMarketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.OnlineMarketplace.entities.Transaction;
import com.fdmgroup.OnlineMarketplace.services.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping
	public List<Transaction> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
	
	@GetMapping("/{id}")
	public Transaction getTransactionById(@PathVariable(value = "id") long transactionId) {
		return transactionService.getTransactionById(transactionId);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Transaction> addNewTransaction(@RequestBody Transaction transaction){
		Transaction newTransaction = transactionService.saveTransaction(transaction);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(newTransaction.getTransactionId()).toUri();

		return ResponseEntity.created(location).body(newTransaction);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Map<String, Object> updates,
														 @PathVariable(value = "id") long transactionId){
		Transaction updatedTransaction = transactionService.updateTransaction(updates, transactionId);
		
		return ResponseEntity.ok(updatedTransaction);
	}
}

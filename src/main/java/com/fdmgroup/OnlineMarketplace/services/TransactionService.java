package com.fdmgroup.OnlineMarketplace.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.OnlineMarketplace.entities.Transaction;
import com.fdmgroup.OnlineMarketplace.exceptions.TransactionNotFoundException;
import com.fdmgroup.OnlineMarketplace.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired 
	private TransactionRepository transactionRepo;
	
	private Logger logger = LogManager.getLogger();
	
	public List<Transaction> getAllTransactions() {
		logger.info("Retrieving all transactions");
		
		return transactionRepo.findAll();
	}
	
	public Transaction getTransactionById(long transactionId) {
		Transaction existingTransaction = transactionRepo.findById(transactionId)
											.orElseThrow(() -> {
												logger.error("Transaction not found for Id: " + transactionId);
												return new TransactionNotFoundException("Invalid transaction Id");
											});
		
		return existingTransaction;
	}

	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	public Transaction updateTransaction(Map<String, Object> updates, long transactionId) {
		Transaction existingTransaction = transactionRepo.findById(transactionId)
										  .orElseThrow(()-> {
											  logger.error("Transaction not found for Id: " + transactionId);
												return new TransactionNotFoundException("Invalid transaction Id");
										  });
		
		if (updates.containsKey("paymentMethod")) {
			logger.info("Updating payment method for transaction id " + transactionId);
			existingTransaction.setPaymentMethod((String)updates.get("paymentMethod"));
		}
		
		if (updates.containsKey("transactionPrice")) {
			logger.info("Updating transacion price for transaction id " + transactionId);
			existingTransaction.setTransactionPrice(BigDecimal.valueOf((Double)updates.get("transactionPrice")));
		}
		
		return transactionRepo.save(existingTransaction);
	}
	
	public Map<String, Boolean> deleteTransaction(long transactionId){
		Transaction existingTransaction = transactionRepo.findById(transactionId)
				  .orElseThrow(()-> {
					  logger.error("Transaction not found for Id: " + transactionId);
						return new TransactionNotFoundException("Invalid transaction Id");
				  });
		
		transactionRepo.delete(existingTransaction);
		logger.info("Successfully deleted transaction of transactionId " + transactionId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		
		return response;
	}

}

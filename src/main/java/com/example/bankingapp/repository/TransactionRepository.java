package com.example.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Accounts, Integer>{

	void save(Transactions transaction);
	
	 @Query("SELECT t.transactionId, t.status, t.fromAccount.accountNumber, t.toAccount.accountNumber, t.transactionAmount, t.timestamp " +
	           "FROM Transactions t " +
	           "WHERE t.fromAccount.accountNumber = :accountNumber OR t.toAccount.accountNumber = :accountNumber")
	    List<Object[]> findByAccountNumber(@Param("accountNumber") Long accountNumber);

	
	

}

package com.example.bankingapp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.Transactions;
import com.example.bankingapp.service.AccountService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("bankingapp")

public class AppController {
	
	@Autowired
	private AccountService accountservice;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Web Application";
	}
	
	@PostMapping("/create_account")
	public String addAccount(@RequestBody Accounts account) {
		
		if(accountservice.addAccount(account)) {
			
			return "New Account is Created Successfully";
		}
		return "User already exist";
		
	}
	@PutMapping("update_account/{account_number}")
	public String updatedAccountDetails(@PathVariable("account_number") long account_number, @RequestBody Accounts a) {
		
		String account_holder_name = a.getAccountHolderName();
		Date date_of_birth = a.getDateOfBirth();
		String account_holder_email = a.getEmail();
		String phone_number = a.getMobileNumber();
		String address = a.getAddress();
		int updatedRow = accountservice.updatedAccountDetails(account_number, account_holder_name, date_of_birth,account_holder_email, phone_number, address);
		if(updatedRow >= 1) {
			return "Account Details updated successfully";
		}
		return "Invalid Account Number";
		
	}
	
	@GetMapping("/get_account_details_by_account_number/{account_number}")
	public Accounts getAccountDetailsByAccountNumber(@PathVariable("account_number")long accountNumber) {
		return accountservice.getAccountDetailsByAccountNumber(accountNumber);
	}
	
	@PutMapping("deposit_money/{account_number}")
	public Accounts deposit_money(@PathVariable("account_number")Long account_number, @RequestBody HashMap<String, Double> request) {
		double deposit_balance = request.get("deposit_balance");
		return accountservice.deposit_money(account_number, deposit_balance);
	}
	
	@PutMapping("withdraw_money/{account_number}")
	public Accounts withdraw_money(@PathVariable("account_number")Long account_number, @RequestBody HashMap<String, Double> request) {
		double withdraw_balance = request.get("withdraw_balance");
		return accountservice.withdraw_money(account_number, withdraw_balance);
	}
	
	@GetMapping("/account_balance/{account_number}")
	public String getAccountBalanceByAccountNumber(@PathVariable("account_number") long accountNumber) {
		double total_balance = accountservice.totalBalance(accountNumber);
		if(total_balance > 0) {
			return "Account Balance : " + total_balance;
		}
		return "Invalid Account Number";
	}
	
	@PostMapping("/transfer_money/{toAccount}")

	public String transferMoney(@PathVariable ("toAccount") long toAccount,@RequestBody Map<String, Object> requestBody) {
		
		long fromAccountNumber = ((Number) requestBody.get("accountNumber")).longValue();
	    double transferAmount = ((Number) requestBody.get("transactionAmount")).doubleValue();

		boolean result = accountservice.transferMoney(fromAccountNumber, toAccount, transferAmount);
		System.out.println(fromAccountNumber + "  " + toAccount + "  " + transferAmount );
			
		if (result) {
	        return "Transfer successful";
	    } else {
	        return "Transfer failed";
	    }
	}
	
	@GetMapping("/show_transaction_history/{account_number}")
	
	public List<Object[]> getTransactionHistory(@PathVariable("account_number") Long accountNumber){
		return accountservice.getTransactionHistory(accountNumber);
	}
	
	@GetMapping("/download_transaction_history/{account_number}")
    public void downloadTransactionHistory(@PathVariable("account_number") Long accountNumber, HttpServletResponse response) throws Exception {
		
		try {
			
		List<Object[]> transactions = accountservice.getTransactionHistory(accountNumber);
        byte[] csvData = accountservice.generateCsv(transactions);

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=transaction_history.csv");
        response.getOutputStream().write(csvData);
        response.getOutputStream().flush();
        
        String fileName = "transaction_history_" + System.currentTimeMillis() + ".csv";
        String filePath = "C:\\Users\\KABITA KUMARI\\Desktop\\transaction history download from Postman/" + fileName;
        accountservice.saveCsvLocally(csvData, filePath);
        
        System.out.println("CSV file saved successfully at: " + filePath);
		} 
		catch (Exception e) {
	        e.printStackTrace();
	        System.err.println("Failed to save CSV file: " + e.getMessage());
		}
    }
	
	@DeleteMapping("/delete_account/{account_number}")
	public String deleteByAccountNumber(@PathVariable("account_number") Long accountNumber) {
		int deletedCount = accountservice.deleteByAccountNumber(accountNumber);

	    if (deletedCount > 0) {
	        return "Successfully deleted " + deletedCount + " account number : " + accountNumber;
	    } 
		
		return "Invalid Account Number";
	}
	

}

package com.example.bankingapp.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.Login;
import com.example.bankingapp.model.RegisterUser;
import com.example.bankingapp.model.Transactions;

public interface AccountService {

	boolean addAccount(Accounts account);

	Accounts getAccountDetailsByAccountNumber(long accountNumber);

	int updatedAccountDetails(long account_number, String account_holder_name, Date date_of_birth, String account_holder_email,
			String phone_number, String address);

	Accounts deposit_money(Long account_number, double deposit_balance);

	Accounts withdraw_money(Long account_number, double withdraw_balance);


	double totalBalance(long accountNumber);

	boolean transferMoney(long fromAccountNumber, long toAccountNumber, double amount);

	List<Object[]> getTransactionHistory(Long accountNumber);
//
//	byte[] generateCsv(List<Object[]> transactions) throws Exception;
//
//	void saveCsvLocally(byte[] csvData, String filePath) throws IOException;

	int deleteByAccountNumber(Long accountNumber);

	boolean registerUser(RegisterUser registerUser) throws AccountServiceException;

	String login(Login login) throws AccountServiceException;

	byte[] generatePdf(List<Object[]> transactions) throws IOException;

	void savePdfLocally(byte[] pdfData, String filePath) throws IOException;
	
	
}

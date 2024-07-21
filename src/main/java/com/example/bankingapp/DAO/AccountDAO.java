package com.example.bankingapp.DAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.Login;
import com.example.bankingapp.model.RegisterUser;
import com.example.bankingapp.model.Transactions;
import com.example.bankingapp.service.AccountServiceException;

public interface AccountDAO {

	boolean registerUser(RegisterUser registerUser);
	
	String login(Login login) throws AccountServiceException;
	
	boolean addAccount(Accounts account);

	Accounts getAccountdetailsByAccountNumber(long accountNumber);

	int updatedAccountDetails(long account_number, String account_holder_name, Date date_of_birth, String account_holder_email,
			String phone_number, String address);

	Accounts deposit_money(Long account_number, double deposit_balance);

	Accounts withdraw_money(Long account_number, double withdraw_balance);

	double totalBalance(long accountNumber);

	String transferMoney(long fromAccountNumber, long toAccountNumber, double amount);

	List<Object[]> getTransactionHistory(Long accountNumber);



//	byte[] generateCsv(List<Object[]> transactions) throws Exception;
//
//	void saveCsvLocally(byte[] csvData, String filePath) throws IOException;
	
	public int deleteByAccountNumber(Long accountNumber);

	byte[] generatePdf(List<Object[]> transactions) throws IOException;

	void savePdfLocally(byte[] pdfData, String filePath) throws IOException;
	

	

}

package com.example.bankingapp.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapp.DAO.AccountDAO;
import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.Login;
import com.example.bankingapp.model.RegisterUser;


@Service
public class AccounctServiceImple implements AccountService {
	
	@Autowired
	public AccountDAO accountDao;
	
	@Override
	public boolean registerUser(RegisterUser registerUser)throws AccountServiceException {
		// TODO Auto-generated method stub
		return accountDao.registerUser(registerUser);
	}


	@Override
	public String login(Login login) throws AccountServiceException{
		// TODO Auto-generated method stub
		return accountDao.login(login);
	}

	@Override
	public boolean addAccount(Accounts account) {
		// TODO Auto-generated method stub
		return accountDao.addAccount(account);
	}

	@Override
	public int updatedAccountDetails(long account_number, String account_holder_name, Date date_of_birth,
			String account_holder_email, String phone_number, String address) {
		// TODO Auto-generated method stub
		return accountDao.updatedAccountDetails(account_number, account_holder_name, date_of_birth, account_holder_email, phone_number, address);
	}


	@Override
	public Accounts getAccountDetailsByAccountNumber(long accountNumber) {
		
		return accountDao.getAccountdetailsByAccountNumber(accountNumber);
	}

	@Override
	public Accounts deposit_money(Long account_number, double deposit_balance) {
		// TODO Auto-generated method stub
		return accountDao.deposit_money(account_number, deposit_balance);
	}

	@Override
	public Accounts withdraw_money(Long account_number, double withdraw_balance) {
		// TODO Auto-generated method stub
		return accountDao.withdraw_money(account_number, withdraw_balance);
	}

	@Override
	public double totalBalance(long accountNumber) {
		// TODO Auto-generated method stub
		return accountDao.totalBalance(accountNumber);
	}

	@Override
	public boolean transferMoney(long fromAccountNumber,long toAccountNumber, double amount) {
		System.out.println(fromAccountNumber + "  " + toAccountNumber + "  " + amount );
        if (fromAccountNumber != 0 && toAccountNumber != 0 && amount > 0) {
            String result = accountDao.transferMoney(fromAccountNumber, toAccountNumber, amount);
            
            return "Transfer successful".equals(result);
        }
        return false;
    }

	@Override
	public List<Object[]> getTransactionHistory(Long accountNumber) {
		// TODO Auto-generated method stub
		return accountDao.getTransactionHistory(accountNumber);
	}

	@Override
	public byte[] generateCsv(List<Object[]> transactions) throws Exception {
		// TODO Auto-generated method stub
		return accountDao.generateCsv(transactions);
	}

	@Override
	public void saveCsvLocally(byte[] csvData, String filePath) throws IOException {
		accountDao.saveCsvLocally(csvData, filePath);
	}

	@Override
	public int deleteByAccountNumber(Long accountNumber) {
		return accountDao.deleteByAccountNumber(accountNumber);
	}



	
	
	


	
	

}

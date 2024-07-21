package com.example.bankingapp.DAO;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.Login;
import com.example.bankingapp.model.RegisterUser;
import com.example.bankingapp.model.Transactions;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.RegisterUsersRepository;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.security.JwtTokenProvider;
import com.example.bankingapp.service.AccountServiceException;

import jakarta.transaction.Transactional;


@Repository
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public AccountRepository Repo;
	
	@Autowired
	public TransactionRepository transactionRepo;
	
	@Autowired
	public RegisterUsersRepository registerUsersRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	 
	@Override
	public String login(Login login) throws AccountServiceException {
		try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsernameOrEmail(), login.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(authentication);

            return jwtTokenProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new AccountServiceException("Invalid credentials");
        }
	}

	@Override
	public boolean registerUser(RegisterUser registerUser) {
		
		if (registerUsersRepository.findByEmail(registerUser.getEmail()).size() != 0) {
            return false; 
        }
		registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
		if(registerUsersRepository.save(registerUser) != null) return true;
		return false;
	}

	@Override
	public boolean addAccount(Accounts account) {
		System.out.println(account.getEmail());
		System.out.println(Repo.findByEmail(account.getEmail()).size());
		
		if(Repo.findByEmail(account.getEmail()).size() != 0 ) return false;
		
		if(Repo.save(account) != null) return true;
		return false;
	}

	
	 @Override
	 @Transactional
	 public int updatedAccountDetails(long accountNumber, String accountHolderName, Date dateOfBirth, String email, String mobileNumber, String address) {
	      return Repo.updateAccountDetails(accountNumber, accountHolderName, dateOfBirth, email, mobileNumber, address);
	 }

	@Override
	public Accounts getAccountdetailsByAccountNumber(long accountNumber) {
		Optional<Accounts>getData = Repo.findByAccountNumber(accountNumber);
		if(getData.isPresent()) {
			return getData.get();	
		}
		return null;
	}


	@Override
	public Accounts deposit_money(Long account_number, double deposit_balance) {
		
		Accounts account = getAccountdetailsByAccountNumber(account_number);
	    if (account != null) {
	   
	        account.setBalance(account.getBalance() + deposit_balance);
	        
	        Repo.save(account);
	       
	        Transactions transaction = new Transactions(0, null, account, deposit_balance, "deposit", new Date());
	        transactionRepo.save(transaction); 

	        return account;
	    }
	    return null;
	}


	@Override
	public Accounts withdraw_money(Long account_number, double withdraw_balance) {
		// TODO Auto-generated method stub
		Accounts account = getAccountdetailsByAccountNumber(account_number);
//		account.setBalance(account.getBalance() - withdraw_balance);
//		return Repo.save(account);
		if (account != null) {
			   
	        account.setBalance(account.getBalance() - withdraw_balance);
	        
	        Repo.save(account);
	       
	        Transactions transaction = new Transactions(0, null, account, withdraw_balance, "withdraw", new Date());
	        transactionRepo.save(transaction); 

	        return account;
	    }
	    return null;
	}
	
	@Override
	public double totalBalance(long accountNumber) {
	    Optional<Accounts> account = Repo.findByAccountNumber(accountNumber);
	    if (account.isPresent()) {
	        return account.get().getBalance();
	    }
	    return 0;
	}


	@Override
	public String transferMoney(long fromAccountNumber, long toAccountNumber, double amount) {
		// TODO Auto-generated method stub
		Optional<Accounts> fromAccount = Repo.findByAccountNumber(fromAccountNumber);
		Optional<Accounts> toAccount = Repo.findByAccountNumber(toAccountNumber);
		
		if(fromAccount.isPresent() && toAccount.isPresent() && amount != 0) {
			Accounts senderAccount = fromAccount.get();
			Accounts receiverAccount = toAccount.get();
			
			if(senderAccount.getBalance()< amount) {
				return "Insufficient Balance";
			}
			senderAccount.setBalance(senderAccount.getBalance()-amount);
			receiverAccount.setBalance(receiverAccount.getBalance()+amount);
			
			Repo.save(senderAccount);
			Repo.save(receiverAccount);
			
			Transactions transaction = new Transactions(0, senderAccount, receiverAccount, amount, "completed", new Date());
			transactionRepo.save(transaction);
			
			return "Transfer successful";

		
		}
	return "Account not found";
	}


	@Override
	public List<Object[]> getTransactionHistory(Long accountNumber) {
		return transactionRepo.findByAccountNumber(accountNumber);
	}


	@Override
	public byte[] generateCsv(List<Object[]> transactions) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader("Transaction ID", "Status", "To Account", "From Account", "Amount", "Timestamp"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Object[] transaction : transactions) {
            printer.printRecord(
                transaction[0],           
                transaction[1],           
                transaction[2],            
                transaction[3],            
                transaction[4],            
                dateFormat.format(transaction[5])   
            );
        }

        printer.flush();
        return out.toByteArray();
	}
	public void saveCsvLocally(byte[] csvData, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(csvData);
        } catch (IOException e) {
            e.printStackTrace();
          
            System.err.println("Error saving CSV file: " + e.getMessage());
        }
    }

	@Override
	public int deleteByAccountNumber(Long accountNumber) {
		
		List<Accounts> accountList = Repo.findAllByAccountNumber(accountNumber);

	    if (!accountList.isEmpty()) {
	        Repo.deleteAllByAccountNumber(accountNumber);
	        return accountList.size();
	    }
		return 0;
		
	}

}

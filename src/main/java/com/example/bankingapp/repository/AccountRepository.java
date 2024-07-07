package com.example.bankingapp.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bankingapp.model.Accounts;
@Repository

public interface AccountRepository extends JpaRepository<Accounts, Long> {

	List<Accounts> findByEmail(String email);

	@Modifying
    @Query("update Accounts a set a.accountHolderName = :accountHolderName, a.dateOfBirth = :dateOfBirth, a.email = :email, a.mobileNumber = :mobileNumber, a.address = :address where a.accountNumber = :accountNumber")
    int updateAccountDetails(@Param("accountNumber") Long accountNumber, 
                             @Param("accountHolderName") String accountHolderName, 
                             @Param("dateOfBirth") Date dateOfBirth, 
                             @Param("email") String email, 
                             @Param("mobileNumber") String mobileNumber, 
                             @Param("address") String address);

	Optional<Accounts> findByAccountNumber(long account_number);

	void save(Optional<Accounts> fromAccount);

	Optional<Accounts> findByAccountNumber(Accounts fromAccountNumber);

	List<Accounts> findAllByAccountNumber(Long accountNumber);

	void deleteAllByAccountNumber(Long accountNumber);

}

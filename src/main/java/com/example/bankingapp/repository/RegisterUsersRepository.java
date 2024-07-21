package com.example.bankingapp.repository;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankingapp.model.Accounts;
import com.example.bankingapp.model.RegisterUser;

@Repository

public interface RegisterUsersRepository extends JpaRepository<RegisterUser, Long> {


	List<RegisterUser> findByEmail(String email);

	Optional<RegisterUser> findByUsernameOrEmail(String username, String email);
}

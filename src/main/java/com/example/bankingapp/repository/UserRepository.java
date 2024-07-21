package com.example.bankingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	

	 Optional<User> findByUsernameOrEmail(String username, String email);

}
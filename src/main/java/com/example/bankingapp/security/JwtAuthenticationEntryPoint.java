package com.example.bankingapp.security;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		System.out.println("Authentication1");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		
		
	}

}

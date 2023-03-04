package com.example.youtubeclone.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.youtubeclone.service.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserRegistrationService userRegistrationService;
	
	@GetMapping("/register")
	public String register(Authentication authentication) {
		Jwt jwt = (Jwt)authentication.getPrincipal();
		
		userRegistrationService.registerUser(jwt.getTokenValue());
		return "User Registration successful";
	}

}

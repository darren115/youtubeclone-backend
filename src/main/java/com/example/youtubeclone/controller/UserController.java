package com.example.youtubeclone.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.youtubeclone.service.UserRegistrationService;
import com.example.youtubeclone.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserRegistrationService userRegistrationService;
	private final UserService userService;
	
	@GetMapping("/register")
	public String register(Authentication authentication) {
		Jwt jwt = (Jwt)authentication.getPrincipal();
		
		userRegistrationService.registerUser(jwt.getTokenValue());
		return "User Registration successful";
	}
	
	@PostMapping("/subscribe/{userId}")
	public boolean subscribeUser(@PathVariable String userId) {
		userService.subscribeUser(userId);
		
		return true;
	}
	
	@PostMapping("/unsubscribe/{userId}")
	public boolean unsubscribeUser(@PathVariable String userId) {
		userService.unsubscribeUser(userId);
		
		return true;
	}
	

}

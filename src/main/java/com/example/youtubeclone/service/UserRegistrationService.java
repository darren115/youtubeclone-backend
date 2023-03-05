package com.example.youtubeclone.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.youtubeclone.dto.UserInfoDTO;
import com.example.youtubeclone.model.User;
import com.example.youtubeclone.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
	
	@Value("${auth0.userInfoEndpoint}")
	private String userInfoEndpoint;
	
	private final UserRepository userRepository;
	
	public String registerUser(String tokenValue) {
		HttpRequest httpRequest = HttpRequest.newBuilder().GET()
		.uri(URI.create(userInfoEndpoint))
		.setHeader("Authorization", String.format("Bearer %s", tokenValue))
		.build();	
		
		HttpClient httpClient = HttpClient.newBuilder().version(Version.HTTP_2).build();
		
		try {
			HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			String body = response.body();
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			UserInfoDTO userInfoDTO = objectMapper.readValue(body, UserInfoDTO.class);
			
			Optional<User> userOp = userRepository.findBySub(userInfoDTO.getSub());
			
			if(userOp.isPresent()) {
				return userOp.get().getId();
			}
			
			User user = new User();
			user.setFirstName(userInfoDTO.getGivenName());
			user.setLastName(userInfoDTO.getFamilyName());
			user.setFirstName(userInfoDTO.getName());
			user.setEmailAddress(userInfoDTO.getEmail());
			user.setSub(userInfoDTO.getSub());
			
			return userRepository.save(user).getId();
			
			
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred while registering user", e);
		} 
	}

}

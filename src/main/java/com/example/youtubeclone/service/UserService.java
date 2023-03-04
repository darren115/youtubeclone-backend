package com.example.youtubeclone.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.example.youtubeclone.model.User;
import com.example.youtubeclone.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public User getCurrentUser() {
		
		String sub = ((Jwt)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getClaim("sub");
		
		return userRepository.findBySub(sub).orElseThrow(()-> new IllegalArgumentException("Cannot find user with sub - " + sub));
	}
	
	public void addToLikedVideos(String videoId) {
		User currentUser = getCurrentUser();
		
		currentUser.addToLikedVideos(videoId);
		
		userRepository.save(currentUser);
	}
	
	public void addToDislikedVideos(String videoId) {
		User currentUser = getCurrentUser();
		
		currentUser.addToDislikedVideos(videoId);
		
		userRepository.save(currentUser);
	}
	
	public boolean ifLikedVideo(String videoId) {
		return getCurrentUser().getLikedVideos().stream().anyMatch(likedVideo->likedVideo.equals(videoId));
	}
	
	public boolean ifDislikedVideo(String videoId) {
		return getCurrentUser().getDislikedVideos().stream().anyMatch(dislikedVideo->dislikedVideo.equals(videoId));
	}
	
	public void removeFromLikedVideos(String videoId) {
		User currentUser = getCurrentUser();
		
		currentUser.removeFromLikedVideos(videoId);
		
		userRepository.save(currentUser);
	}
	
	public void removeFromDislikedVideos(String videoId) {
		User currentUser = getCurrentUser();
		
		currentUser.removeFromDislikedVideos(videoId);
		
		userRepository.save(currentUser);
	}

}

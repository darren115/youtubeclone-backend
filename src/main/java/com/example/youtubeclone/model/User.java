package com.example.youtubeclone.model;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String emailAddress;
	private String sub;
	private Set<String> subscribedToUsers = ConcurrentHashMap.newKeySet();
	private Set<String> subscribers = ConcurrentHashMap.newKeySet();
	private Set<String> videoHistory = ConcurrentHashMap.newKeySet();
	private Set<String> likedVideos = ConcurrentHashMap.newKeySet();
	private Set<String> dislikedVideos = ConcurrentHashMap.newKeySet();
	
	
	public void addToLikedVideos(String videoId) {
		likedVideos.add(videoId);
		
	}

	public void removeFromLikedVideos(String videoId) {
		likedVideos.remove(videoId);
		
	}
	
	public void addToDislikedVideos(String videoId) {
		dislikedVideos.add(videoId);
		
	}

	public void removeFromDislikedVideos(String videoId) {
		dislikedVideos.remove(videoId);
		
	}

	public void addToVideoHistory(String id) {
		videoHistory.add(id);		
	}

	public void addToSubscribedToUsers(String userId) {
		subscribedToUsers.add(userId);
	}

	public void addToSubscribers(String id) {
		subscribers.add(id);
	}

	public void removeFromSubscribedToUsers(String userId) {
		subscribedToUsers.remove(userId);
	}
	
	public void removeFromSubscribers(String id) {
		subscribers.remove(id);
	}
}

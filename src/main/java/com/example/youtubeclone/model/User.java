package com.example.youtubeclone.model;

import java.util.List;
import java.util.Set;

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
	private Set<String> ubscribedToUsers;
	private Set<String> subscribers;
	private List<String> videoHistory;
	private Set<String> likedVideos;
	private Set<String> dislikeVideos;
}

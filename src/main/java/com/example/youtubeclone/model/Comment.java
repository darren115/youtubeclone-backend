package com.example.youtubeclone.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	@Id
	private String id;
	private String text;
	private String authorId;
	private Integer likeCount;
	private Integer dislikeCount;
	private LocalDateTime uploadDate = LocalDateTime.now();
}

package com.example.youtubeclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	
	private String commentText;
	private String authorId;
	private Integer likeCount;
	private Integer dislikeCount;
	private Long uploadDifference;

}

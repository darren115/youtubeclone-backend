package com.example.youtubeclone.dto;

import java.util.Set;

import com.example.youtubeclone.model.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
	
	private String id;
	private String title;
	private String description;
	private Set<String> tags;
	private String videoUrl;
	private VideoStatus videoStatus;
	private Integer viewCount;
	private String thumbnailUrl;
	private Integer likeCount;
	private Integer dislikeCount;
	private Long uploadDate;

}

package com.example.youtubeclone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.youtubeclone.dto.CommentDto;
import com.example.youtubeclone.dto.UploadVideoResponse;
import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.service.VideoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/videos")
public class VideoController {
	
	private final VideoService videoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file) {		
		return videoService.uploadVideo(file);		
	}
	
	@PostMapping("/thumbnail")
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId) {
		
		return videoService.uploadThumbnail(file, videoId);
		
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public VideoDto editVideoMetaData(@RequestBody VideoDto videoDto){
		return videoService.editVideo(videoDto);
	}
	
	@GetMapping("/{videoId}")
	@ResponseStatus(HttpStatus.OK)
	public VideoDto getVideoDetails(@PathVariable String videoId) {
		return videoService.getVideoDetails(videoId);
	}
	
	@PostMapping("/{videoId}/like")
	@ResponseStatus(HttpStatus.OK)
	public VideoDto likeVideo(@PathVariable String videoId) {
		return videoService.likeVideo(videoId);
	}
	
	@PostMapping("/{videoId}/dislike")
	@ResponseStatus(HttpStatus.OK)
	public VideoDto dislikeVideo(@PathVariable String videoId) {
		return videoService.dislikeVideo(videoId);
	}
	
	@PostMapping("/{videoId}/comment")
	@ResponseStatus(HttpStatus.OK)
	public void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto) {
		videoService.addComment(videoId, commentDto);
	}
	
	@GetMapping("/{videoId}/comment")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getComments(@PathVariable String videoId) {
		return videoService.getComments(videoId);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<VideoDto> getAllVideos(){
		return videoService.getAllVideos();
	}
	

}

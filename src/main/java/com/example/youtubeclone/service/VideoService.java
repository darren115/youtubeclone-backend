package com.example.youtubeclone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.youtubeclone.dto.UploadVideoResponse;
import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {
	
	private final S3Service s3Service;
	private final VideoRepository videoRepository;
	
	public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
		
		String videoUrl = s3Service.uploadFile(multipartFile);
		var video = new Video();
		video.setVideoUrl(videoUrl);
		
		var savedVideo = videoRepository.save(video);
		
		return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
		
	}
	
	public String uploadThumbnail(MultipartFile multipartFile, String videoId) {
		
		var savedVideo = getVideoById(videoId);
		
		String thumbnailUrl = s3Service.uploadFile(multipartFile);
		savedVideo.setThumbnailUrl(thumbnailUrl);
		
		videoRepository.save(savedVideo);
		
		return thumbnailUrl;
		
	}
	
	public VideoDto editVideo(VideoDto videoDto) {
		
		var savedVideo = getVideoById(videoDto.getId());
		
		savedVideo.setTitle(videoDto.getTitle());
		savedVideo.setDescription(videoDto.getDescription());
		savedVideo.setTags(videoDto.getTags());
		savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
		savedVideo.setVideostatus(videoDto.getVideoStatus());
		
		videoRepository.save(savedVideo);
		
		return videoDto;
	}
	
	private Video getVideoById(String videoId) {
		return videoRepository.findById(videoId)
				.orElseThrow(()-> new IllegalArgumentException("Cannot find video by id - " + videoId));
	}

}

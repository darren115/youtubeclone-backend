package com.example.youtubeclone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.youtubeclone.dto.UploadVideoResponse;
import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.repository.UserRepository;
import com.example.youtubeclone.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {
	
	private final S3Service s3Service;
	private final VideoRepository videoRepository;
	private final UserService userService;
	
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
		savedVideo.setVideoStatus(videoDto.getVideoStatus());
		
		videoRepository.save(savedVideo);
		
		return videoDto;
	}
	
	private Video getVideoById(String videoId) {
		return videoRepository.findById(videoId)
				.orElseThrow(()-> new IllegalArgumentException("Cannot find video by id - " + videoId));
	}

	public VideoDto getVideoDetails(String videoId) {
		Video savedVideo = getVideoById(videoId);
		
		VideoDto videoDto = new VideoDto();
		videoDto.setId(savedVideo.getId());
		videoDto.setTitle(savedVideo.getTitle());
		videoDto.setDescription(savedVideo.getDescription());
		videoDto.setTags(savedVideo.getTags());
		videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
		videoDto.setVideoUrl(savedVideo.getVideoUrl());
		videoDto.setVideoStatus(savedVideo.getVideoStatus());
		
		return videoDto;
	}

	public VideoDto likeVideo(String videoId) {
		Video savedVideo = getVideoById(videoId);
		
		//Increment like count
		//If user already liked video, then decrement like count
		
		//like 0, dislike 0
		//like 1, dislike 0
		//like 0, dislike 0
		
		//like 0, dislike 1
		//like 1, dislike 0
		
		//if user already dislike video, then increment like count and decrement dislike count
		
		if(userService.ifLikedVideo(videoId)) {
			savedVideo.decrementLikes();
			userService.removeFromLikedVideos(videoId);
		} else if (userService.ifDislikedVideo(videoId)) {
			savedVideo.decrementDislikes();
			userService.removeFromDislikedVideos(videoId);
			savedVideo.incrementLikes();
			userService.addToLikedVideos(videoId);
		} else {
			savedVideo.incrementLikes();
			userService.addToLikedVideos(videoId);
		}		
		
		videoRepository.save(savedVideo);
		
		VideoDto videoDto = new VideoDto();
		videoDto.setId(savedVideo.getId());
		videoDto.setTitle(savedVideo.getTitle());
		videoDto.setDescription(savedVideo.getDescription());
		videoDto.setTags(savedVideo.getTags());
		videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
		videoDto.setVideoUrl(savedVideo.getVideoUrl());
		videoDto.setVideoStatus(savedVideo.getVideoStatus());
		videoDto.setLikeCount(savedVideo.getLikes().get());
		videoDto.setDislikeCount(savedVideo.getDislikes().get());
		
		return videoDto;
	}
	
	public VideoDto dislikeVideo(String videoId) {
		Video savedVideo = getVideoById(videoId);
		
		if(userService.ifDislikedVideo(videoId)) {
			savedVideo.decrementDislikes();
			userService.removeFromDislikedVideos(videoId);
		} else if (userService.ifLikedVideo(videoId)) {
			savedVideo.decrementLikes();
			userService.removeFromLikedVideos(videoId);
			savedVideo.incrementDislikes();
			userService.addToDislikedVideos(videoId);
		} else {
			savedVideo.incrementDislikes();
			userService.addToDislikedVideos(videoId);
		}		
		
		videoRepository.save(savedVideo);
		
		VideoDto videoDto = new VideoDto();
		videoDto.setId(savedVideo.getId());
		videoDto.setTitle(savedVideo.getTitle());
		videoDto.setDescription(savedVideo.getDescription());
		videoDto.setTags(savedVideo.getTags());
		videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
		videoDto.setVideoUrl(savedVideo.getVideoUrl());
		videoDto.setVideoStatus(savedVideo.getVideoStatus());
		videoDto.setLikeCount(savedVideo.getLikes().get());
		videoDto.setDislikeCount(savedVideo.getDislikes().get());
		
		return videoDto;
	}

}

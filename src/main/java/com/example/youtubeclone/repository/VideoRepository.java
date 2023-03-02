package com.example.youtubeclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.youtubeclone.model.Video;

public interface VideoRepository extends MongoRepository<Video, String>{

}

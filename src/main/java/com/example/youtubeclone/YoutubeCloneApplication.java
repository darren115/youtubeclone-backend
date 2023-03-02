package com.example.youtubeclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@SpringBootApplication
public class YoutubeCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeCloneApplication.class, args);
	}
	
	@Bean
    public static AmazonS3Client amazonS3Client() {
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion("eu-west-2")
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

}

package com.hypocritus.asyncApi;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GithubLookupService {
	
	private static final Logger logger = LoggerFactory.getLogger(GithubLookupService.class);
	
	private final RestTemplate restTemplate;
	
	public GithubLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public CompletableFuture<User> findUser (String user) throws InterruptedException{
		logger.info("Searching...");
		String url = "https://api.github.com/users/" + user;
		User fetchedUser = restTemplate.getForObject(url, User.class);
		Thread.sleep(3141);
		return CompletableFuture.completedFuture(fetchedUser);
	}
}

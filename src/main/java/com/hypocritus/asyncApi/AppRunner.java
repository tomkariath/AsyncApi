package com.hypocritus.asyncApi;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	
	private final GithubLookupService lookupService;
	
	public AppRunner(GithubLookupService lookupService) {
		this.lookupService = lookupService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		long startTime = System.currentTimeMillis();
		
		CompletableFuture<User> user1 = lookupService.findUser("tomkariath");
		CompletableFuture<User> user2 = lookupService.findUser("vitaliy-bobrov");
		CompletableFuture<User> user3 = lookupService.findUser("joaomsa");
		
		CompletableFuture.allOf(user1, user2, user3).join();
		
		logger.info(user1.get().toString());
		logger.info(user2.get().toString());
		logger.info(user3.get().toString());
	
		logger.info("Runtime = " + (System.currentTimeMillis() - startTime));
	}
}

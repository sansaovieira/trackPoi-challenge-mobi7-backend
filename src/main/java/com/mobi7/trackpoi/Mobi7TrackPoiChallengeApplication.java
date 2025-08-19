package com.mobi7.trackpoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableCaching
@EnableScheduling
@SpringBootApplication
public class Mobi7TrackPoiChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mobi7TrackPoiChallengeApplication.class, args);
	}

}

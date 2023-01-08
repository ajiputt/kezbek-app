package com.telkomsel.kezbek.consumer.component;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestHttpCall {

	private final RestTemplate restTemplate;

	public RestHttpCall(RestTemplateBuilder restTemplateBuilder) {
		// set connection and read timeouts
		this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500)).build();
	}

	public String getPostsPlainJSON() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		return this.restTemplate.getForObject(url, String.class);
	}

}

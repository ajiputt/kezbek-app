package com.telkomsel.kezbek.consumer.configuration;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		// set connection and read timeouts
		return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(500))
				.setReadTimeout(Duration.ofSeconds(500)).build();
	}
    
}

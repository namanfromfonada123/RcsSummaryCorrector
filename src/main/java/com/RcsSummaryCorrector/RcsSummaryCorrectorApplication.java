package com.RcsSummaryCorrector;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RcsSummaryCorrectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcsSummaryCorrectorApplication.class, args);
	}

	 @Bean
     RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
            .setConnectTimeout(Duration.ofMillis(60000))
            .setReadTimeout(Duration.ofMillis(60000))
            .build();
    }
	
}

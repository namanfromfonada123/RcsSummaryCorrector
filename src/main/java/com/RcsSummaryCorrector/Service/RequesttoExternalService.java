package com.RcsSummaryCorrector.Service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class RequesttoExternalService {

	Logger logger = LoggerFactory.getLogger(RequesttoExternalService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${urlString}")
	private String urlString;

	public void SlackAlert(String error, String Description) {
		if (!urlString.isEmpty()) {

			String dataString ="{\"text\" :\" AppName : Mis -\n error:   " + error
					+ "\n  Description: "+ Description + "  \" }";
			
			logger.info("Inside Slack: "+ dataString);

			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "application/json");
				HttpEntity<Object> httpEntity = new HttpEntity<Object>(dataString, headers);
				logger.info("Trying to Generate Alert over slack !! ");
				ResponseEntity<String> response = restTemplate.exchange(urlString,
						HttpMethod.POST, httpEntity, String.class);
				logger.info("Alert over slack Generated Successfully !! | StatusCode: " + response.getStatusCodeValue());
				

			} catch (Exception e) {
				logger.error("Getting error During Alert Generation !!" + e.getLocalizedMessage() + " " + dataString);
			}
		}else {
			logger.info("Please Configure Slack url conversationaldata_Request_slackUrlApi");
		}
	}

}

package com.in28Minutes.Microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	//Circuit Breaker impl
	//	@GetMapping("/sample-api")
//	public String sampleAPI()
//	{  
//			logger.info("Sample api response recieved");
//		ResponseEntity<String> forEntity=new RestTemplate().getForEntity("http://localhost:8080/", String.class);
//		return forEntity.getBody();
//	}
	
//	@GetMapping("/sample-api")
//	//@Retry(name="default")
//	@Retry(name="DeveloperRequestedRetries")
//	public String sampleAPI()
//	{  
//			logger.info("Sample api response recieved");
//		ResponseEntity<String> forEntity=new RestTemplate().getForEntity("http://localhost:8080/", String.class);
//		return forEntity.getBody();
//	} 
	       
	//fALLBACK approch
	@GetMapping("/sample-api")
	//@Retry(name="default")
	@Retry(name="DeveloperRequestedRetries", fallbackMethod="hardCodedResponse")
	public String sampleAPI()
	{  
			logger.info("Sample api response recieved");
		ResponseEntity<String> forEntity=new RestTemplate().getForEntity("http://localhost:8080/", String.class);
		return forEntity.getBody();
	}
	
	
	public String hardCodedResponse(Exception ex)
	{
		return "Hardcoded response recieved at failed connection";
	}
}

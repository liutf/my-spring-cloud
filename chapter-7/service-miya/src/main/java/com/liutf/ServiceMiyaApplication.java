package com.liutf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class ServiceMiyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyaApplication.class, args);
	}

    @Autowired
    private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
	    return new RestTemplate();
	}

	@Bean
	public AlwaysSampler defaulstSampler(){
	    return new AlwaysSampler();
	}


    @GetMapping("/hi")
    public String callHome(){
        log.info("hi is being called");
        return "hi i'm miya!";

    }

    @GetMapping("/miya")
    public String info(){
        log.info("info is beging called.");
        return restTemplate.getForObject("http://localhost:8988/info",String.class);
    }
	
}

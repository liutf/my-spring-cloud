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
public class ServiceHiZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHiZipkinApplication.class, args);
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
        log.info("calling trace sercice-hi");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }

    @GetMapping("/info")
    public String info(){
        log.info("calling trace service-hi");
        return "i am service-hi";
    }
	
}

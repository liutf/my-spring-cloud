package com.liutf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClient8Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient8Application.class, args);
	}

	@Value("${server.port}")
	String port;
	@GetMapping("/hi/{name}")
	public String hi(@PathVariable String name) {
		return "hi "+name+",i am from port:" +port;
	}

}

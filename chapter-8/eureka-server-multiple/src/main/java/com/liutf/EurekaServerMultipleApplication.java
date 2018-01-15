package com.liutf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMultipleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerMultipleApplication.class, args);
	}
}

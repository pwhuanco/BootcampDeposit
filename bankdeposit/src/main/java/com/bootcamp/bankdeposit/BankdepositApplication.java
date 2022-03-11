package com.bootcamp.bankdeposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BankdepositApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankdepositApplication.class, args);
	}

}

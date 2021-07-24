package com.springbank.handlers.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@EnableSqs
@SpringBootApplication(scanBasePackages = { "com.springbank" })
public class AwsSQSConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSQSConsumerApplication.class, args);
	}

}

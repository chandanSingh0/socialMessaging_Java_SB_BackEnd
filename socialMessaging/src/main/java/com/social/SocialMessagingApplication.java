package com.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Social Messaging Backend API",description = "the social messaging application working api's",contact = @Contact(
		name = "Chandan Singh",
        email = "cs95335@gmail.com")))
public class SocialMessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMessagingApplication.class, args);
	}

}

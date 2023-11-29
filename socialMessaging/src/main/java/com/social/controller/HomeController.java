package com.social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HomeController {
	
	@Operation(
            summary = "Welcome! Screen "
    )
	//while retrieving from database
	@GetMapping("/")
	public String homeControllerHandler() {
		return "Welcome To Social-Messaging API's Backend Application";
	}
	
}

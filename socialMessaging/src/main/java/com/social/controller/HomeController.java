package com.social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	
	//while retrieving from database
	@GetMapping("/")
	public String homeControllerHandler() {
		return "This is home Controller";
	}
	
}

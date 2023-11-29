package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Story;
import com.social.models.User;
import com.social.service.StoryService;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	@Autowired
	private UserService userService;
	
	@Operation(
            summary = "Creating a Story the currently login User  "
    )
	@PostMapping("/story")
	public Story createStory(@RequestHeader("Authorization") String jwt, @RequestBody Story story) {
		
		User user = userService.findUserByJwt(jwt);
		
		Story createdStory = storyService.createStory(story, user);
		
		return createdStory;
		
	}
	
	@Operation(
            summary = "Finding a Story of the User whose userId is passed  "
    )
	@GetMapping("/story/user/{userId}")
	public List<Story> findAllUSerReels(@PathVariable Integer userId) throws Exception{
		List<Story> stories = storyService.findStoryByUserId(userId);
		return stories;
	}
	
}

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

import com.social.models.Reels;
import com.social.models.User;
import com.social.service.ReelsService;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class ReelsController {

	@Autowired
	private ReelsService reelsService;

	@Autowired
	private UserService userService;

	@Operation(
            summary = "Creating a Reels the currently login User  "
    )
	@PostMapping("/reels")
	public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwt(jwt);
		Reels createdReels = reelsService.createReel(reels, user);
		return createdReels;
	}
	
	@Operation(
            summary = "Finding all Reels of Application  "
    )
	@GetMapping("/reels")
	public List<Reels> findAllReels(){
		List<Reels> reels = reelsService.findAllReels();
		return reels;
	}
	
	@Operation(
            summary = "Finding a Reels of the User whose userId is passed  "
    )
	@GetMapping("/reels/user/{userId}")
	public List<Reels> findAllUSerReels(@PathVariable Integer userId) throws Exception{
		List<Reels> reels = reelsService.findUsersReels(userId);
		return reels;
	}
}

package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.User;
import com.social.repository.UserRepository;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@Operation(
            summary = "Getting all the Users from our application"
    )
	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@Operation(
            summary = "Getting a User by id from our application"
    )
	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer userId) throws Exception {
		System.out.println("onennnn");
		User updUser =  userService.findUserById( userId);
		
		return updUser;
		
	}
	
	@Operation(
            summary = "Updating a User who is login "
    )
	@PutMapping("/users")
	public User updateUser(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User updUser =  userService.updateUser(user, reqUser.getId());
		
		return updUser;
		
	}
	
	@Operation(
            summary = "Following a User "
    )
	@PutMapping("/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable("userId2") Integer userId2) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@Operation(
            summary = "Search a User with there firstname, email or lastname"
    )
	@GetMapping("/users/search")
	public List<User> searchUsers(@RequestParam("query") String query){
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@Operation(
            summary = "Search a User profile who is login"
    )
	@GetMapping("/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		System.out.println("jwt--------"+jwt);
		return user;
	}
	
	
}

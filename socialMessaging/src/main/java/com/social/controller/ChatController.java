package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Chat;
import com.social.models.User;
import com.social.request.CreateChatRequest;
import com.social.service.ChatService;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api")
public class ChatController {

	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;
	
	@Operation(
            summary = "Creating a Chats of the currently login User with any other user "
    )
	@PostMapping("/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest chatRequest) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		System.out.println(reqUser.getFirstName()+"===================");
		System.out.println(chatRequest.getUserId());

		User user2 = userService.findUserById(chatRequest.getUserId());
		
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;
	}
	
	@Operation(
            summary = "Finding all Chats of Currently login user  "
    )
	@GetMapping("/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
		User user = userService.findUserByJwt(jwt);
		List<Chat> chat =chatService.findUsersChat(user.getId());
		return chat;
	}
	
}

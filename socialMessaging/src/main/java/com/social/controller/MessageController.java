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

import com.social.models.Message;
import com.social.models.User;
import com.social.service.MessageService;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	@Operation(
            summary = "Messaging to a User "
    )
	@PostMapping("/message/chat/{chatId}")
	public Message createMessage(@RequestBody Message message,
			@RequestHeader("Authorization") String jwt, 
			@PathVariable("chatId")Integer chatId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Message message2 = messageService.createMessage(user, chatId, message);
		return message2;
	}
	
	@Operation(
            summary = "Find the Messages inside Chat "
    )
	@GetMapping("/message/chat/{chatId}")
	public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt, 
			@PathVariable("chatId")Integer chatId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		List<Message> messages = messageService.findChatsMessages(chatId);
		return messages;
	}
}

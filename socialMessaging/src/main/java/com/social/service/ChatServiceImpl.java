package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Chat;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.repository.UserRepository;

@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	ChatRepository chatRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		// TODO Auto-generated method stub
		Chat isExist = chatRepository.findChatByUserId(user2, reqUser);
		if(isExist!=null) {
		 return	isExist;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimeStamp(LocalDateTime.now());
		
		return chatRepository.save(chat);
		 
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Chat> chaOptional = chatRepository.findById(chatId);
		if(chaOptional.isEmpty()) {
			throw new Exception("chat not found with id---"+chatId);
		}
		return chaOptional.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		// TODO Auto-generated method stub
		
		return chatRepository.findByUsersId(userId);
	}

}

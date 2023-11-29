package com.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatService chatService;

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Message createMessage(User user, Integer chatId, Message message) throws Exception {
		// TODO Auto-generated method stub

		Chat chat = chatService.findChatById(chatId);

		Message creatMessage = new Message();

		creatMessage.setChat(chat);
		creatMessage.setContent(message.getContent());
		creatMessage.setImage(message.getImage());
		creatMessage.setUser(user);
		creatMessage.setCreatedAt(LocalDateTime.now());
		Message message2 = messageRepository.save(creatMessage);

		chat.getMessages().add(message2);

		chatRepository.save(chat); /// here we updating the chat with messages

		return message2;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
		Chat chat = chatService.findChatById(chatId);

		return messageRepository.findByChatId(chatId);
	}

}

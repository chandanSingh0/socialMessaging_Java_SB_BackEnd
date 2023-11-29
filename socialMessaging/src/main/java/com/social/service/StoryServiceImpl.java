package com.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Story;
import com.social.models.User;
import com.social.repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	StoryRepository storyRepository;
	@Autowired
	UserService userService;
	
	
	@Override
	public Story createStory(Story story, User user) {
		// TODO Auto-generated method stub
		Story createdStory = new Story();
		createdStory.setCaptions(story.getCaptions());
		createdStory.setImage(story.getImage());
		createdStory.setUser(user);
		createdStory.setTimeStamp(LocalDateTime.now());
		
		return createdStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		userService.findUserById(userId);
		
		return storyRepository.findByUserId(userId);
	}

}

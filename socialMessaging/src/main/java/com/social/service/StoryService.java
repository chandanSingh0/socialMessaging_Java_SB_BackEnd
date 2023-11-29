package com.social.service;

import java.util.List;

import com.social.models.Story;
import com.social.models.User;

public interface StoryService {

	public Story createStory(Story story ,User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
	
}

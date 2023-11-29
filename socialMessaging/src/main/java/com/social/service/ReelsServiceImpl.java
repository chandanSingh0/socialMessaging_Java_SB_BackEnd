package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Reels;
import com.social.models.User;
import com.social.repository.ReelsRepository;

@Service
public class ReelsServiceImpl implements ReelsService {

	@Autowired
	private ReelsRepository reelsRepository;

	@Autowired
	private UserService userService;

	@Override
	public Reels createReel(Reels reels, User user) {
		// TODO Auto-generated method stub
		
		Reels createReels = new Reels();
		createReels.setTitle(reels.getTitle());
		createReels.setUser(user);
		createReels.setVideo(reels.getVideo());
		
	return reelsRepository.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReels(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		userService.findUserById(userId);
		
		return reelsRepository.findByUserId(userId);
	}

}

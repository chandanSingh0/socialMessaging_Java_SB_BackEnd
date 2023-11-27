package com.social.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.config.JwtProvider;
import com.social.models.User;
import com.social.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		User newUser = new User();
		
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setGender(user.getGender());
		newUser.setId(user.getId());
		
		User saveUser = userRepository.save(newUser);
		
		return saveUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		// TODO Auto-generated method stub
   Optional<User> getuserOptional = userRepository.findById(userId);
		
		if(getuserOptional.isPresent()) {
			return getuserOptional.get();
		}
		
		 throw new Exception("user not exist by id "+userId);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		 User getuserOptional = userRepository.findByEmail(email);
			
			
		return getuserOptional;
	}

	
	//reqUserId wants to follow userId2
	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {
		// TODO Auto-generated method stub
		
		//reqUser is the logged in User
		
		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	@Override
	public User updateUser(User user,Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> user1 = userRepository.findById(userId);
		if(user1.isEmpty()) {
			throw new Exception("user does not exist"+userId);
		}
		User oldUser = user1.get();
		if(user.getFirstName()!=null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setEmail(user.getGender());
		}
		
		User updUser = userRepository.save(oldUser);
		
		return updUser;
	}

	@Override
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		// TODO Auto-generated method stub
		String emailString = JwtProvider.getEmailfromJwtToken(jwt);
		
		User user = userRepository.findByEmail(emailString);
		
		
		return user;
	}

}


package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Post;
import com.social.models.User;
import com.social.repository.PostIRepository;
import com.social.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostIRepository postIRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		
		User user = userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now() );
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		postIRepository.save(newPost);
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		
		User user = userService.findUserById(userId);
		Post post = findPostById(postId);
		
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("you can not delete another user post");
		}
		
		postIRepository.delete(post);
		
		return "post deleted successfullly";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		
		return postIRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Post> post = postIRepository.findById(postId);
		
		if(post.isEmpty()) {
			throw new Exception("PostNot Found with id"+postId);	
		}
		
		
		return post.get();
	}

	@Override
	public List<Post> findAllPosts() {
		// TODO Auto-generated method stub
		return postIRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		
		User user = userService.findUserById(userId);
		Post post = findPostById(postId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		
		userRepository.save(user);
		
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		
		User user = userService.findUserById(userId);
		Post post = findPostById(postId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		
		return postIRepository.save(post);
	}

}

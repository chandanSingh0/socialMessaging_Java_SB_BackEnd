package com.social.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Comment;
import com.social.models.Post;
import com.social.models.User;
import com.social.repository.CommentRepository;
import com.social.repository.PostIRepository;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	PostIRepository postIRepository;
	
	@Override
	public Comment createCommment(Comment comment, Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreateAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment);
		
		post.getComments().add(savedComment);
		postIRepository.save(post);
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Comment> optional = commentRepository.findById(commentId);
		if(optional.isEmpty()) {
			throw new Exception("comment not exist with id "+commentId);
		}
		return optional.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Comment comment = findCommentById(commentId);
		System.out.println(comment.getContent()+"-------------");
		User user = userService.findUserById(userId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

}

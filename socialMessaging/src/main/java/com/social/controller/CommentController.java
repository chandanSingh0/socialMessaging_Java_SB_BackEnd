package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Comment;
import com.social.models.User;
import com.social.service.CommentService;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentServic;
	@Autowired
	private UserService userService;
	
	@Operation(
            summary = "Creating a Comment by the currently login User to a post  "
    )
	@PostMapping("/api/comment/post/{postId}")
	public Comment createComment(@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,@PathVariable("postId") Integer postId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		
		Comment createdComment = commentServic.createCommment(comment,postId , user.getId());
		
		return createdComment;
	}
	
	@Operation(
            summary = "Liking a Comment by the currently login User of a post  "
    )
	@PutMapping("/api/comment/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,@PathVariable("commentId") Integer commentId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		
		Comment likedComment = commentServic.likeComment(commentId , user.getId());
		
		return likedComment;
	}
	
	
	
}

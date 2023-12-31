package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Post;
import com.social.models.User;
import com.social.response.ApiResponse;
import com.social.service.PostService;
import com.social.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	
	@Operation(
            summary = "Creating a post by the user "
    )
	@PostMapping("/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,@RequestBody Post post) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		
		return new ResponseEntity<Post>(createdPost,HttpStatus.CREATED);
		
	}
	
	@Operation(
            summary = "Delete a post by the user who is login "
    )
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		String messageString = postService.deletePost(postId, reqUser.getId());
		ApiResponse response = new ApiResponse(messageString,true);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@Operation(
            summary = "Finding a post by the postId "
    )
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post>findPostById(@PathVariable Integer postId) throws Exception{
		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
	
	@Operation(
            summary = "Finding a post by the userId "
    )
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@Operation(
            summary = "Finding all posts "
    )
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		List<Post> posts = postService.findAllPosts();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@Operation(
            summary = "Saving a post by the currently login User  "
    )
	@PutMapping("/posts/save/{postId}")
	public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);
		Post messageString = postService.savedPost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(messageString,HttpStatus.OK);
	}
	
	@Operation(
            summary = "Liking a post by the currently login User  "
    )
	@PutMapping("/posts/like/{postId}")
	public ResponseEntity<Post> likedPostHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception{
		User reqUser = userService.findUserByJwt(jwt);

		Post messageString = postService.likePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(messageString,HttpStatus.OK);
	}
	
	
	
}

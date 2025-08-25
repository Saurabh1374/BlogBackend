package com.saurabh.blog.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saurabh.blog.payloads.PostDto;
import com.saurabh.blog.payloads.PostResponseDto;
import com.saurabh.blog.services.PostService;

@RestController
@RequestMapping("/app/post")
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	ObjectMapper mapper;
	
	@PostMapping("/create")
	public ResponseEntity<PostDto> createPost(@RequestParam("image") MultipartFile image, 
											@RequestParam String postdto, 
											@RequestParam Integer userid, 
											@RequestParam Integer categoryId) 
													throws IOException{
		PostDto post=mapper.readValue(postdto, PostDto.class);			

		PostDto message=postService.createPost(image,post, userid, categoryId);
		System.out.println(message);
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
		}
	@GetMapping("/get_posts")
	public ResponseEntity<List<PostResponseDto>> getAllPost(){
		List<PostResponseDto> posts =postService.getAllPost();
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	@GetMapping("/get_post_by_user/{user_id}")
	public ResponseEntity<List<PostResponseDto>> getPostByUser(@PathVariable("user_id") Integer userId){
		List<PostResponseDto> posts=postService.getAllPostByUser(userId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	@GetMapping("/get_post_by_category/{cat_id}")
	public ResponseEntity<List<PostResponseDto>> getPostByCategory(@PathVariable("cat_id") Integer catId){
		List<PostResponseDto> posts=postService.getAllPostByCategory(catId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	@PutMapping("/update/{post_id}")
	public ResponseEntity<PostDto> updatePost( @PathVariable("post_id")Integer postId, @RequestBody PostDto postDto){
		PostDto post=postService.updatePost(postId, postDto);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	@DeleteMapping("/delete-post/{post_id}")
	public HttpStatus deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return HttpStatus.OK;
	}
}

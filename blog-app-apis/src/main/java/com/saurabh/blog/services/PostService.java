package com.saurabh.blog.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.saurabh.blog.entities.Post;
import com.saurabh.blog.payloads.PostDto;
import com.saurabh.blog.payloads.PostResponseDto;

public interface PostService {
	//create
	PostDto createPost(MultipartFile imageFile,PostDto postDto,Integer userId, Integer categoryId) throws IOException;
	//read
	List<PostResponseDto> getAllPost();
	List<PostResponseDto> getAllPostByUser(Integer userId);
	List<PostResponseDto> getAllPostByCategory(Integer categoryId);
	
	
	void deletePost(Integer postId);
	PostDto updatePost(Integer postId, PostDto postDto);
	
	
}

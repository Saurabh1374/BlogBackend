package com.saurabh.blog.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.saurabh.blog.entities.Category;
import com.saurabh.blog.entities.Post;
import com.saurabh.blog.entities.User;
import com.saurabh.blog.exceptions.ResourceNotFoundException;
import com.saurabh.blog.payloads.PostDto;
import com.saurabh.blog.payloads.PostResponseDto;
import com.saurabh.blog.payloads.UserDto;
import com.saurabh.blog.repositories.PostRepository;
import com.saurabh.blog.repositories.UserRepo;
import com.saurabh.blog.services.CategoryService;
import com.saurabh.blog.services.PostService;
import com.saurabh.blog.services.UserService;
@Service
@PropertySource("classpath:post.properties")
public class PostServiceImpl implements PostService{
	@Value("${post.path}")
	private String FILE_DIR;
	@Autowired
	UserService userService;
	@Autowired
	PostRepository postRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	CategoryService categoryService;

	@Override
	public PostDto createPost(MultipartFile imageFile,PostDto postDto, Integer userId, Integer categoryId) throws IOException {
		// TODO Auto-generated method stub
		String fileName=imageFile.getOriginalFilename();
		String inputFileName=getFileName(fileName);
		InputStream fileStream=null;
		fileStream=imageFile.getInputStream();
		//Path path=Paths.get(FileDir);
		uploadImage(fileStream, inputFileName);
		
		User user= mapper.map(userService.getuserById(userId), User.class);
		Category category=mapper.map(categoryService.getCategory(categoryId), Category.class);
		Post post=new Post();
		post.setContent(postDto.getContent());
		post.setAddedDate(new Date());
		post.setTitle(postDto.getTitle());
		post.setUser(user);
		post.setImageName(inputFileName);
		post.setCategory(category);
		postRepo.save(post);
		
		PostDto postdto=mapper.map(post, PostDto.class);
		return postdto;
	}
	//pagination
	@Override
	public List<PostResponseDto> getAllPost() {
		List<Post> post=postRepo.findAll();
		List <PostResponseDto> responses=post.stream().map(e -> mapper.map(e, PostResponseDto.class)).peek(r->r.setImageUrl(FILE_DIR+r.getImageName())).collect(Collectors.toList());
		return responses;
		
	}


	@Override
	public PostDto updatePost(Integer postId, PostDto postDto) {
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("postId", "Post",postId));
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		postRepo.save(post);
		
		return mapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("PostId", "Post", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostResponseDto> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=mapper.map(userService.getuserById(userId),User.class);
		List<Post> posts=postRepo.findByUser(user);
		return posts.stream().map(e->mapper.map(e, PostResponseDto.class)).peek(e->e.setImageUrl(FILE_DIR+e.getImageName())).collect(Collectors.toList());
	}

	@Override
	public List<PostResponseDto> getAllPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=mapper.map(categoryService.getCategory(categoryId), Category.class);
		List<Category> posts=postRepo.findByCategory(cat);
		return posts.stream().map(e->mapper.map(e, PostResponseDto.class)).peek(e->e.setImageUrl(FILE_DIR+e.getImageName())).collect(Collectors.toList());
	}
	private void uploadImage(InputStream fileInputStream, String fileName) throws IOException{
		 Path directoryPath = Paths.get(FILE_DIR);

	        // Check if the directory exists and is a directory
	        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
	            throw new IOException("Directory does not exist: " + FILE_DIR);
	        }

	        // Create the target file path where the file will be stored
	        Path targetFilePath = directoryPath.resolve(fileName);

	        // Copy the file to the target location
	        try {
	            Files.copy(fileInputStream, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("File uploaded successfully to: " + targetFilePath.toAbsolutePath());
	        } catch (FileAlreadyExistsException e) {
	            System.err.println("File already exists: " + targetFilePath.toAbsolutePath());
	            throw e;
	        } catch (IOException e) {
	            System.err.println("Error while uploading file: " + e.getMessage());
	            throw e;
	        }
		
	}
	private String getFileName(String fileName) {
		 String[] fileParts = fileName.split("\\.");
	     String fileExtension = fileParts[fileParts.length - 1];
		return fileParts[0] + "_#"+ new Date().hashCode()+"."+fileExtension;
	}

}

package com.saurabh.blog.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.saurabh.blog.entities.User;
import com.saurabh.blog.exceptions.ResourceNotFoundException;
import com.saurabh.blog.payloads.UserDto;
import com.saurabh.blog.repositories.UserRepo;
import com.saurabh.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser = userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getuserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList = userRepo.findAll();
		List<UserDto> users = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return users;
	}

	@Override
	public void deleteUser(Integer userid) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		this.userRepo.delete(user);

	}

//usually we do this by using model mappers
	public User dtoToUser(UserDto userDto) {
		User user = this.mapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.mapper.map(user, UserDto.class);
//		userDto.setAbout(user.getAbout());
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}

	@Override
	public Page<UserDto> getAllUser( int page,int offset) {
		// TODO Auto-generated method stub
		Pageable pageRequest = createPageRequestUsing(page, offset);
		List<User> userList=userRepo.findAll();
		int start = (int) pageRequest.getOffset();
	    int end = Math.min((start + pageRequest.getPageSize()), userList.size());
	    List<UserDto> users = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
	    List<UserDto> pageContent = users.subList(start, end);
	    return new PageImpl<>(pageContent, pageRequest, users.size());
	}
	private Pageable createPageRequestUsing(int page, int size) {
	    return PageRequest.of(page, size);
	}

}

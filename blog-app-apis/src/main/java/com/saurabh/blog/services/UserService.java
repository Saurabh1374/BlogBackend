package com.saurabh.blog.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.saurabh.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getuserById(Integer userId);

	List<UserDto> getAllUser();

	void deleteUser(Integer userid);
	
	Page<UserDto> getAllUser( int page, int offset);

}

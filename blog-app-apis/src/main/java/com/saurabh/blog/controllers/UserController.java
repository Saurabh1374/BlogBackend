package com.saurabh.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurabh.blog.payloads.UserDto;
import com.saurabh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	@PostMapping("/createuser")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUsers=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUsers, HttpStatus.CREATED);
	}
	@GetMapping("/getusers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> listOfUser=this.userService.getAllUser();
		return new ResponseEntity<>(listOfUser,HttpStatus.OK);
		
	}
	@GetMapping("/getusersbyid/{id}")
	public ResponseEntity<UserDto> getAllUsers(@PathVariable Integer id){
		UserDto listOfUser=this.userService.getuserById(id);
		return new ResponseEntity<>(listOfUser,HttpStatus.OK);
		
	}
	@PutMapping("/updateuser/{uId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer uId){
		UserDto user=this.userService.updateUser(userDto, uId);
		return ResponseEntity.ok(user);
	}
	@DeleteMapping("/deleteUser/{uId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer uId){
		this.userService.deleteUser(uId);
		return ResponseEntity.ok(Map.of("message","User deleted successfully"));
	}
	
	

}

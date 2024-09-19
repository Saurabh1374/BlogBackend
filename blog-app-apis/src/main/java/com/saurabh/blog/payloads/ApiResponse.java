package com.saurabh.blog.payloads;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//add factory methods
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private String message;
	private boolean success;

}

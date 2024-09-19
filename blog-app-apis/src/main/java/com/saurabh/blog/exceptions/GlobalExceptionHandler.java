package com.saurabh.blog.exceptions;

import java.util.HashMap;
import java.util.Map;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.saurabh.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundException(ResourceNotFoundException e){
		String message=e.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity <Map<String, String>> handleMethodArgsnotvalidException(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errors)->{
			String fieldNames=((FieldError) errors).getField();
			String message=errors.getDefaultMessage();
			resp.put(fieldNames, message);
		});
		return new ResponseEntity <Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleInvalidAttributesException(IllegalArgumentException ex ){
		Map<String, String> resp=new HashMap<>();
		String message =ex.getMessage();
		String cause=ex.toString();
		resp.put(message, cause);
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
		} 
	public ResponseEntity<?> handleGenericException(Exception ex){
		System.out.println("Exception occured"+ ex);
		return new ResponseEntity<>(new ApiResponse(ex.getMessage(), false),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

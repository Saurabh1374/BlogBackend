package com.saurabh.blog.exceptions;

import lombok.AllArgsConstructor;


public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String resourceField;
	long fieldvalue;
	public ResourceNotFoundException(String resourceName, String resourceField, long fieldvalue) {
		super(String.format("%s not found with resource %s: %l", resourceName,resourceField,fieldvalue));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.fieldvalue = fieldvalue;
	}

	
	
	
}

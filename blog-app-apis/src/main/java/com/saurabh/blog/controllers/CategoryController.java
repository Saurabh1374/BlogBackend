package com.saurabh.blog.controllers;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurabh.blog.payloads.ApiResponse;
import com.saurabh.blog.payloads.CategoryDto;
import com.saurabh.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategpry(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto create=categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(create,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto){
		Integer categoryId=categoryDto.getCategoryId();
		CategoryDto categoryEntity=categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryEntity,HttpStatus.OK);
	}
	//delete
	@DeleteMapping("/{category_id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer category_id){
		this.categoryService.deleteCategory(category_id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully",true), HttpStatus.OK);
		
	}
	//get
	@GetMapping("/{category_id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer category_id){
		CategoryDto categoryResponse=this.categoryService.getCategory(category_id);
		return new ResponseEntity<CategoryDto>(categoryResponse,HttpStatus.OK);
	}
	//getAll
	@GetMapping("/get_all")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryResponse=this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categoryResponse,HttpStatus.OK);
	}
}

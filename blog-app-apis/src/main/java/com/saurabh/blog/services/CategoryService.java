package com.saurabh.blog.services;

import java.util.List;

import com.saurabh.blog.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto addCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	void deleteCategory(Integer categoryId);

	CategoryDto getCategory(Integer categoryId);

	List<CategoryDto> getAllCategory();
}

package com.saurabh.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.saurabh.blog.entities.Category;
import com.saurabh.blog.payloads.CategoryDto;
import com.saurabh.blog.repositories.CategoryRepo;
import com.saurabh.blog.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category=modelMapper.map(CategoryDto.class,Category.class);
		Category addedcat=categoryRepo.save(category);
		return modelMapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

}

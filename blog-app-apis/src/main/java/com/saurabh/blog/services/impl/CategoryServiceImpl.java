package com.saurabh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.directory.InvalidAttributesException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.saurabh.blog.entities.Category;
import com.saurabh.blog.exceptions.ResourceNotFoundException;
import com.saurabh.blog.payloads.CategoryDto;
import com.saurabh.blog.repositories.CategoryRepo;
import com.saurabh.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * That's the difference between a "checked" exception and an "unchecked"
	 * exception. Anything that extends RuntimeException, including
	 * NullPointerException, are "unchecked" which means they don't need to be
	 * explicitly handled via a try/catch or by declaring that the method throw
	 * them.
	 * 
	 * Checked exceptions are those that do not extend RuntimeException and must be
	 * handled either by try/catch or by declaring your method throw it. So your
	 * code fails to compile because you are not handling it either way.
	 */
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		if (categoryDto.getCategoryId() == 0) {
			Category category = modelMapper.map(categoryDto, Category.class);
			Category addedcat = categoryRepo.save(category);
			return modelMapper.map(addedcat, CategoryDto.class);
		} else {
			throw new IllegalArgumentException("Can not accept");
		}
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category ", "category id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCat = categoryRepo.save(category);
		return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category ", "category id", categoryId));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category ", "category id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categoryLi = categoryRepo.findAll();
		return categoryLi.stream().map(e -> modelMapper.map(e, CategoryDto.class)).collect(Collectors.toList());
	}

}

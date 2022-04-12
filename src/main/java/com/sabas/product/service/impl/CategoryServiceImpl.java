package com.sabas.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sabas.product.entity.Category;
import com.sabas.product.payload.CategoryDto;
import com.sabas.product.repository.CategoryRepository;
import com.sabas.product.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	private ModelMapper mapper;
	
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}


	@Transactional
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = mapToEntity(categoryDto);
		Category newCategory = categoryRepository.save(category);

		CategoryDto categoryResponse = mapToDto(newCategory);
		return categoryResponse;
	}


	@Transactional(readOnly = true)
	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> listOfCategories = categoryRepository.findAll();
		return listOfCategories.stream()
				.map(category->mapToDto(category))
				.collect(Collectors.toList()); 
	}
	
	
	// convert Entity to Dto
	private CategoryDto mapToDto(Category category) {
		CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	// convert DTO to entity
	private Category mapToEntity(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		return category;
	}
	
}

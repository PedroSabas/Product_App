package com.sabas.product.service;

import java.util.List;

import com.sabas.product.payload.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	List<CategoryDto> getAllCategories();
}

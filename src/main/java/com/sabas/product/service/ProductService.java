package com.sabas.product.service;

import java.util.List;

import com.sabas.product.payload.ProductDto;

public interface ProductService {
	ProductDto createProduct(Long categoryId, ProductDto productDto);
	List<ProductDto> getProductsByCategoryId(Long categoryId);
	List<ProductDto> getExpiredProducts();
	ProductDto getProductById(Long categoryId, Long productId);
	ProductDto getProductByName(Long categoryId, String productName);
	ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto);
	void deleteProduct(Long categoryId, Long productId);
}

package com.sabas.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sabas.product.entity.Category;
import com.sabas.product.entity.Product;
import com.sabas.product.exception.ProductAPIException;
import com.sabas.product.exception.ResourceNotFoundException;
import com.sabas.product.payload.ProductDto;
import com.sabas.product.repository.CategoryRepository;
import com.sabas.product.repository.ProductRepository;
import com.sabas.product.service.ProductService;

@Service
public class ProuctServiceImpl implements ProductService{

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private ModelMapper mapper;
	
	public ProuctServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			ModelMapper mapper) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}

	// Create product
	@Override
	public ProductDto createProduct(Long categoryId, ProductDto productDto) {
		Product product = mapToEntity(productDto);
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow( ()-> new ResourceNotFoundException("Category", "id", categoryId) );
		product.setCategory(category);
		Product newProduct = productRepository.save(product);
		return mapToDto(newProduct);
	}

	// Get products by category Id
	@Override
	public List<ProductDto> getProductsByCategoryId(Long categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		return products.stream()
					  .map(product->mapToDto(product))
					  .collect(Collectors.toList());
	}

	// Get expired products by category Id
	@Override
	public List<ProductDto> getExpiredProducts() {
		List<Product> productsObject = productRepository.findByCategoryIdExpired();
		//  .filter(p -> p.isStatus() == false )
		return productsObject.stream()
				  .map(product->mapToDto(product))
				  .collect(Collectors.toList());
					 
	}
	
	// Get product by Id
	@Override
	public ProductDto getProductById(Long categoryId, Long productId) {
		Category category = categoryRepository.findById(categoryId)
				  .orElseThrow( ()-> new ResourceNotFoundException("Category", "id", categoryId) );    
		Product product = productRepository.findById(productId)
			   .orElseThrow( ()-> new ResourceNotFoundException("Product", "id", productId) );	
		if (!product.getCategory().getId().equals(category.getId())) {
			throw new ProductAPIException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoria");
		}
		return mapToDto(product);
	}
	

	// Search product by name
	@Override
	public ProductDto getProductByName(Long categoryId, String productName) {
		Category category = categoryRepository.findById(categoryId)
				  .orElseThrow( ()-> new ResourceNotFoundException("Category", "id", categoryId) ); 
		Product product = productRepository.findByName(productName)
										 .orElseThrow( ()-> new ProductAPIException(HttpStatus.NOT_FOUND, "No se encontro esté producto") );
		if (!product.getCategory().getId().equals(category.getId())) {
			throw new ProductAPIException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoria");
		}
		return mapToDto(product);
	}
	
	// Update product
	@Override
	public ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto) {
		Category category = categoryRepository.findById(categoryId)
											  .orElseThrow( ()-> new ResourceNotFoundException("Category", "id", categoryId) );    
		Product product = productRepository.findById(productId)
										   .orElseThrow( ()-> new ResourceNotFoundException("Product", "id", productId) );	
		if (!product.getCategory().getId().equals(category.getId())) {
			throw new ProductAPIException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoria");
		}
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setExpired_date(productDto.getExpired_date());
		Product updateProduct = productRepository.save(product);
		return mapToDto(updateProduct);
	}

	// Delete product
	@Override
	public void deleteProduct(Long categoryId, Long productId) {
		Category category = categoryRepository.findById(categoryId)
											  .orElseThrow( ()-> new ResourceNotFoundException("Category", "id", categoryId) );    
		Product product = productRepository.findById(productId)
										   .orElseThrow( ()-> new ResourceNotFoundException("Product", "id", productId) );	
		if (!product.getCategory().getId().equals(category.getId())) {
			throw new ProductAPIException(HttpStatus.BAD_REQUEST, "El producto no pertenece a la categoria");
		}
		productRepository.delete(product);
	}
	
	// convert Entity to Dto
	private ProductDto mapToDto(Product product) {
		ProductDto productDto = mapper.map(product, ProductDto.class);
		return productDto;
	}

	// convert DTO to entity
	private Product mapToEntity(ProductDto productDto) {
		Product product = mapper.map(productDto, Product.class);
		return product;
	}
	
	/*
	private boolean isStatus(Product product) {
		if ( !product.isStatus() && product.getExpired_date() 
				 != null && Calendar.getInstance().getTime().after(product.getExpired_date())) {
				
			product.setStatus(false);
		}
		return false;

	}*/
}

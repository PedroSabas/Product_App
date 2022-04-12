package com.sabas.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sabas.product.payload.ProductDto;
import com.sabas.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	// Create product
	@PostMapping("/categories/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(@PathVariable(value="categoryId")long categoryId,
													@Valid @RequestBody ProductDto productDto) {
		return new ResponseEntity<ProductDto>(productService.createProduct(
				categoryId, productDto), HttpStatus.CREATED);  
	}
	
	// Get products by category Id
	@GetMapping("/categories/{categoryId}/products")
	public List<ProductDto> getProductsByCategoryId(@PathVariable(value="categoryId")long categoryId) {
		return productService.getProductsByCategoryId(categoryId);
	}
	
	// Update product
	@PutMapping("/categories/{categoryId}/products/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, 
													@PathVariable(name = "categoryId") long categoryId,
													@PathVariable(name = "productId") long productId){
		ProductDto productResponse = productService.updateProduct(categoryId, productId, productDto);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}
	
	// Get product by Id
	@GetMapping("/categories/{categoryId}/products/{Id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(name="categoryId") long categoryId,
													 @PathVariable(name="Id") long productId) {
		ProductDto productDto = productService.getProductById(categoryId, productId);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	// Search product by name
	//@GetMapping("/categories/{categoryId}/products/{productName}")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ProductDto> searchProductsName(@PathVariable(name="categoryId") long categoryId,
														 @RequestParam(name="productName") String productName) {
		return new ResponseEntity<>(productService.getProductByName(categoryId, productName), HttpStatus.OK);
	}
	
	// Get expired products by category Id
	@GetMapping("/categories/expired_products")
	public List<ProductDto> getExpiredProductsByCategoryId() {
		return productService.getExpiredProducts();
	}
	
	
	// Delete product
	@DeleteMapping("/categories/{categoryId}/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable(name="categoryId") long categoryId,
												@PathVariable(name="productId") long productId) {
		productService.deleteProduct(categoryId, productId);
		return new ResponseEntity<>("The product was successfully removed", HttpStatus.OK);
	}
	
	
	
	
}

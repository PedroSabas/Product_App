package com.sabas.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sabas.product.entity.Product;

@Repository
public interface ProductRepository extends IGenericRepository<Product, Long>{
	List<Product> findByCategoryId(long categoryId);
	
	@Query("SELECT pr FROM Product pr WHERE pr.status = false")
	List<Product> findByCategoryIdExpired();
	
	
	Optional<Product> findByName(String productName);
}

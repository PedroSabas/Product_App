package com.sabas.product.repository;

import org.springframework.stereotype.Repository;

import com.sabas.product.entity.Category;

@Repository
public interface CategoryRepository extends IGenericRepository<Category, Long> {

}

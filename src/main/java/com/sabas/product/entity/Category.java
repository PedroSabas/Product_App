package com.sabas.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.Data;

@Data
@Entity
@Table(
		name="category", uniqueConstraints = 
			{@UniqueConstraint(columnNames = {"category_name"})}
		)

public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="category_name", nullable = false, unique = true)
	private String name;
	
}

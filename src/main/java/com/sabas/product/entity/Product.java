package com.sabas.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
*/
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private int price;
	private String description;
	
	@Column(name="product_status")
	private boolean status = true;
	
	
	@Column(name="expired_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date expired_date;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date date = new Date();
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	
}

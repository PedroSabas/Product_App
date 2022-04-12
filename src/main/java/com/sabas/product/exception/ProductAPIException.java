package com.sabas.product.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductAPIException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;	
}

package com.sabas.product.payload;

import java.util.Date;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Long id;
	
	@NotEmpty
	@Size(min=2, message = "el nombre del producto debe tener 2 caracteres como minimo")
	private String name;
	
	@Min(1)
	private int price;
	
	@NotEmpty
	@Size(min=5, message = "la descripción del producto debe de tener 5 caracteres como minimo")
	private String description;
	
	private Date expired_date;
	
	
	private boolean status = true;
}

package com.sabas.product.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Extrae los datos del JSON de respuesta, es un DTo genérico para el tratamiento de la información de respuesta
 * 
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
}

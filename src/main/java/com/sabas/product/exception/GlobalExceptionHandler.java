package com.sabas.product.exception;

import com.sabas.product.payload.ErrorDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Clase que manejara todas las excepciones
 * */


// ControllerAdvice indica a Spring que está clase va a controlar todas las excepciones que se den en tiempo de ejecución
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {// Extiende de ResponseEntityExceptionHandler porque el formato que vamos a emitir es un formato JSON

	
	
	
	/*
	 * Este metodo se encarga de que cuando no encuentre un recurso va a llegar una petición que se ha dado un error y emite una respuesta.
	 * Esta función recibe la excepcio que se esta dando el momento(ResourceNotFoundException exception) y (WebRequest webRequest) que
	 * va a contener toda la información que está llegando, RETORNA UN NOT_FOUND
	 * */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	
	/*
	 * Trata las excepciones por ejemplo cuando se envia un POST con atributos en blanco, RETORNA UN BAD_REQUEST
	 * */
	@ExceptionHandler(ProductAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException
			(ResourceNotFoundException exception, WebRequest webRequest){
		
		ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	
	/*
	 * RETORNA UNA RESPUESTA EN JSON DE LOS CAMPOS FALTANTES
	 * */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}













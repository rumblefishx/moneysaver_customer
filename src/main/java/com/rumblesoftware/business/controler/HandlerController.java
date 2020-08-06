package com.rumblesoftware.business.controler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rumblesoftware.exception.CustomerNotFoundException;
import com.rumblesoftware.exception.DateConversionException;
import com.rumblesoftware.exception.PasswordHashException;
import com.rumblesoftware.io.model.CustomerResponseFactory;
import com.rumblesoftware.io.output.dto.CustomerResponseDto;

@RestControllerAdvice
public class HandlerController {
	
	@Autowired
	private MessageSource ms;
	
	private static final String CUSTOMER_NOT_FOUND_MSG = "customer.not.found.message";

	@ExceptionHandler(value = DateConversionException.class)
	public ResponseEntity<CustomerResponseDto> handleDateConversionException(DateConversionException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(ex.getMessage());
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = PasswordHashException.class)
	public ResponseEntity<CustomerResponseDto> handlePasswordHashException(PasswordHashException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(ex.getMessage());
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<CustomerResponseDto> handleCustomerNotFoundException(CustomerNotFoundException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(ms.getMessage(String.format(CUSTOMER_NOT_FOUND_MSG,ex.getMessage()),null,Locale.getDefault()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.NOT_FOUND);
	}
}

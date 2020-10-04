package com.rumblesoftware.business.controler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rumblesoftware.exception.CustomerIdNotFoundException;
import com.rumblesoftware.exception.CustomerNotFoundException;
import com.rumblesoftware.exception.DateConversionException;
import com.rumblesoftware.exception.ExternalTokenValidationException;
import com.rumblesoftware.exception.InvalidExternalTokenException;
import com.rumblesoftware.exception.InvalidPasswordException;
import com.rumblesoftware.exception.PasswordHashException;
import com.rumblesoftware.io.model.CustomerResponseFactory;
import com.rumblesoftware.io.output.dto.CustomerResponseDto;
import com.rumblesoftware.utils.PostOfficer;

@RestControllerAdvice
public class HandlerController {
	
	@Autowired
	private PostOfficer po;
	
	private static final String CUSTOMER_ID_NOT_FOUND = "customer.id.not.found.message";
	
	private static final String CUSTOMER_NOT_FOUND_MSG = "customer.not.found.message";
	
	private static final String INVALID_PASSWD_MSG = "customer.input.password.invalid.message";
	
	private static final String INVALID_EXTERNAL_TOKEN_MSG = "external.login.token.invalid";
	
	private static final String ERROR_EXTERNAL_TOKEN_MSG = "external.login.token.error";

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
	
	@ExceptionHandler(value = CustomerIdNotFoundException.class)
	public ResponseEntity<CustomerResponseDto> handleCustomerIdNotFoundException(CustomerIdNotFoundException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(CUSTOMER_ID_NOT_FOUND),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<CustomerResponseDto> handleCustomerNotFoundException(CustomerNotFoundException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(CUSTOMER_NOT_FOUND_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(value = InvalidPasswordException.class)
	public ResponseEntity<CustomerResponseDto> handleInvalidPasswordException(InvalidPasswordException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(INVALID_PASSWD_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(value = InvalidExternalTokenException.class)
	public ResponseEntity<CustomerResponseDto> handleInvalidExternalTokenException(InvalidExternalTokenException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(INVALID_EXTERNAL_TOKEN_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(value = ExternalTokenValidationException.class)
	public ResponseEntity<CustomerResponseDto> handleExternalTokenValidationException(ExternalTokenValidationException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(ERROR_EXTERNAL_TOKEN_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomerResponseDto> onValidationError(ConstraintViolationException ex) {
		
		CustomerResponseDto response = new CustomerResponseDto();
		
		for(ConstraintViolation<?> val : ex.getConstraintViolations()) {
			String invalidValue = val.getInvalidValue() == null ? "é null" : String.valueOf(val.getInvalidValue());
				response.addError(String.format(po.getMessage(val.getMessage()),invalidValue));

		}
		
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
    }
}

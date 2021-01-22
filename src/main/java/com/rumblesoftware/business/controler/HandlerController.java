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
import com.rumblesoftware.exception.EmailAlreadyRegisteredException;
import com.rumblesoftware.exception.ExternalTokenValidationException;
import com.rumblesoftware.exception.InvalidExternalTokenException;
import com.rumblesoftware.exception.InvalidPasswordException;
import com.rumblesoftware.exception.LoginDataNotFoundException;
import com.rumblesoftware.exception.PasswordHashException;
import com.rumblesoftware.io.model.CustomerResponseFactory;
import com.rumblesoftware.io.output.dto.CustomerResponseDto;
import com.rumblesoftware.utils.PostOfficer;

/**
 * Controller responsible for handle exceptions from the application
 * @author Cleiton
 *
 */
@RestControllerAdvice
public class HandlerController {
	
	/**
	 * Inject an instance of a PostOfficer class
	 */
	@Autowired
	private PostOfficer po;

	private static final String CUSTOMER_ID_NOT_FOUND = "customer.id.not.found.message";
	
	private static final String CUSTOMER_NOT_FOUND_MSG = "customer.not.found.message";
	
	private static final String INVALID_PASSWD_MSG = "customer.input.password.invalid.message";
	
	private static final String INVALID_EXTERNAL_TOKEN_MSG = "external.login.token.invalid";
	
	private static final String DUPLICATED_EMAIL_MSG = "customer.email.duplicated.message";
	
	private static final String ERROR_EXTERNAL_TOKEN_MSG = "external.login.token.error";
	
	private static final String LOGIN_DATA_NOT_FOUND = "internal.login.not.found";

	/**
	 * Method responsible for handle "DateConversionException" exceptions
	 * @param ex : DateConversionException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = DateConversionException.class)
	public ResponseEntity<CustomerResponseDto> handleDateConversionException(DateConversionException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(ex.getMessage());
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Method responsible for handle "PasswordHashException" Exceptions
	 * @param ex : PasswordHashException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = PasswordHashException.class)
	public ResponseEntity<CustomerResponseDto> handlePasswordHashException(PasswordHashException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(ex.getMessage());
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Method responsible for handle "CustomerIdNotFoundException" exceptions
	 * @param ex : CustomerIdNotFoundException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = CustomerIdNotFoundException.class)
	public ResponseEntity<CustomerResponseDto> handleCustomerIdNotFoundException(CustomerIdNotFoundException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(CUSTOMER_ID_NOT_FOUND),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Method responsible for handle "CustomerNotFoundException" exceptions
	 * @param ex : CustomerIdNotFoundException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<CustomerResponseDto> handleCustomerNotFoundException(CustomerNotFoundException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(CUSTOMER_NOT_FOUND_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.NOT_FOUND);	
	}
	
	/**
	 * Method responsible for handle "InvalidPasswordException" exceptions
	 * @param ex : InvalidPasswordException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = InvalidPasswordException.class)
	public ResponseEntity<CustomerResponseDto> handleInvalidPasswordException(InvalidPasswordException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(INVALID_PASSWD_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
	}
	
	/**
	 * Method responsible for handle "InvalidExternalTokenException" exceptions
	 * @param ex : InvalidExternalTokenException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = InvalidExternalTokenException.class)
	public ResponseEntity<CustomerResponseDto> handleInvalidExternalTokenException(InvalidExternalTokenException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(INVALID_EXTERNAL_TOKEN_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
	}
	
	
	/**
	 * Method responsible for handle "EmailAlreadyRegisteredException" exceptions
	 * @param ex : EmailAlreadyRegisteredException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = EmailAlreadyRegisteredException.class)
	public ResponseEntity<CustomerResponseDto> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(DUPLICATED_EMAIL_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
	}
	
	
	/**
	 * Method responsible for handle "ExternalTokenValidationException" exceptions
	 * @param ex : ExternalTokenValidationException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = ExternalTokenValidationException.class)
	public ResponseEntity<CustomerResponseDto> handleExternalTokenValidationException(ExternalTokenValidationException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(String.format(po.getMessage(ERROR_EXTERNAL_TOKEN_MSG),ex.getMessage()));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	/**
	 * Method responsible for handle "LoginDataNotFoundException" exceptions
	 * @param ex : LoginDataNotFoundException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(value = LoginDataNotFoundException.class)
	public ResponseEntity<CustomerResponseDto> handleExternalTokenValidationException(LoginDataNotFoundException ex){
		CustomerResponseDto response = CustomerResponseFactory.getCustomerResponse(po.getMessage(LOGIN_DATA_NOT_FOUND));
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	
	/**
	 * Method responsible for handle "ConstraintViolationException" exceptions
	 * @param ex : ConstraintViolationException instance
	 * @return ResponseEntity instance
	 */
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomerResponseDto> onValidationError(ConstraintViolationException ex) {
		
		CustomerResponseDto response = new CustomerResponseDto();
		
		for(ConstraintViolation<?> val : ex.getConstraintViolations()) {
			String invalidValue = val.getInvalidValue() == null ? "é null" : String.valueOf(val.getInvalidValue());
			response.addError(String.format(po.getMessage(val.getMessage()), invalidValue));
		}
		
		return new ResponseEntity<CustomerResponseDto>(response,HttpStatus.BAD_REQUEST);	
    }
}

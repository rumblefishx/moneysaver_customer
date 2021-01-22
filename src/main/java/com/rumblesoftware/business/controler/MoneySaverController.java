package com.rumblesoftware.business.controler;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.enums.ExternalTokenType;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.output.dto.CustomerResponseDto;
import com.rumblesoftware.io.validation.ValidExternalIdType;

/**
 * Controller responsible to deal with the application requests
 * @author Cleiton
 *
 */
@RestController(value = "/moneysaver")
@Validated
public class MoneySaverController {

	/**
	 * Inject a CustomerOperations instance
	 */
	@Autowired
	private CustomerOperations customerOperations;
	
	/**
	 * Inject a CustomerIOConverter instance
	 */
	@Autowired
	private CustomerIOConverter customerConverter;
	
	
	/**
	 * Inject a MessageSource instance
	 */
	@Autowired
	private MessageSource ms;
	
	private static final String CUSTOMER_ID_NOT_NULL = "customer.input.id.notnull";
	
	/**
	 * Get a Logger instance to log error ocurrences
	 */
	private Logger log = LoggerFactory.getLogger(MoneySaverController.class);
	
	/**
	 * Method responsible for receive "create new user" requests and persist new user in the database
	 * @param customer : input parameter with the customer details
	 * @param bindingResult : parameter responsible for hold validation messages
	 * @return ResponseEntity<CustomerResponseDto> instance filled with created user details
	 */
	@PostMapping("/customer")
	public ResponseEntity<CustomerResponseDto> createNewCustomer(@Valid @RequestBody CustomerInputDTO customer,BindingResult bindingResult) {
		
		log.info("Create New Customer Endpoint - Request Receiver");
		
		CustomerResponseDto response = new CustomerResponseDto();
		
		if (bindingResult.hasErrors()) {
			
			log.error("Create New Customer Endpoint - Validation errors. Generating error response...");
			
			bindingResult.getAllErrors()
				.stream()
				.forEach(error -> response.addError(error.getDefaultMessage()));
			
			response.setResponseBody(customerConverter.convertInputToOutput(customer));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setResponseBody(customerOperations.createCustomer(customer));
		
		log.info("Create New Customer Endpoint - Deliveing response...");
		
		return ResponseEntity.ok(response);
	}

	/**
	 * Method responsible for receive "update user" requests and update an user in the database
	 * @param customer : input parameters with new customer details
	 * @param br : parameter responsible for hold validation messages
	 * @return ResponseEntity<CustomerResponseDto> instance filled with created user details
	 */
	@PatchMapping("/customer")
	public ResponseEntity<CustomerResponseDto> updateCustomer(@Valid @RequestBody CustomerInputPatchDto customer,BindingResult br){
		CustomerResponseDto response = new CustomerResponseDto();
		
		log.info("Receiving an update request...");
		
		if(customer.getCustomerId() == null) {
			response.addError(ms.getMessage(CUSTOMER_ID_NOT_NULL, null,Locale.getDefault()));
			response.setResponseBody(customerConverter.convertPatchToOutput(customer));
			return ResponseEntity.badRequest().body(response);
		}
		
		if(br.hasErrors()) {
			log.error("An error during update request. Generating error response...");
			br.getAllErrors()
			.stream()
			.forEach(error -> response.addError(error.getDefaultMessage()));
		
			response.setResponseBody(customerConverter.convertInputPatchToOutput(customer));
			return ResponseEntity.badRequest().body(response);
		}
		
		log.error("Delivering update response...");
		
		response.setResponseBody(customerOperations.updateCustomer(customer));
		return ResponseEntity.ok(response);
	}
	
	
	/**
	 * Method responsible for receive an internal login request and authenticate the user
	 * @param email : input parameter filled with user's email
	 * @param password : input parameter filled with user's password
	 * @return ResponseEntity<CustomerResponseDto> instance filled with created user details
	 */
	@GetMapping("/customer/internal_login")
	public ResponseEntity<CustomerResponseDto> customerLoginByEmailAndPassword
			(@Valid @Email @RequestParam("email") String email,
			 @Valid @RequestParam("password") String password){
		
		CustomerResponseDto response = new CustomerResponseDto();		
		
		log.info("Receiving a internal login request...");
		response.setResponseBody(customerOperations.findUserByPasswdAndCredential(email, password));
		
		log.info("Delivering internal login response...");
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Method responsible for receive an external login request and authenticate the user
	 * @param externalToken : input parameter with external token details
	 * @param tokenType : input parameter which describes the external token type
	 * @return ResponseEntity<CustomerResponseDto> instance filled with created user details
	 */
	@GetMapping("/customer/external_login")
	public ResponseEntity<CustomerResponseDto> customerLoginByExternalEntity
			(@RequestParam("externalToken") String externalToken,
			 @ValidExternalIdType(message = "{external.id.type.invalid}") @RequestParam("tokenType") Integer tokenType){
		
		log.info("Receiving external login request...");
		CustomerResponseDto response = new CustomerResponseDto();
		
		ExternalTokenType tkType = ExternalTokenType.castIntToEnum(tokenType);		
		ExternalTokenDataDto tokenData = new ExternalTokenDataDto(externalToken,tkType);
		
		log.info("Delivering external login response...");
		response.setResponseBody(customerOperations.findUserByExternalTokenId(tokenData));
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/customer/")
	public ResponseEntity<CustomerResponseDto> getCustomerById(@RequestParam("customerId") @NotNull(message="customer.by.id.notnull") Long customerId){
		CustomerResponseDto response = new CustomerResponseDto();

		response.setResponseBody(customerOperations.findByCustomerId(customerId));

		return ResponseEntity.ok(response);
	}
}

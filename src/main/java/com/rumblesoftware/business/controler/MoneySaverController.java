package com.rumblesoftware.business.controler;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.Email;

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

@RestController(value = "/moneysaver")
@Validated
public class MoneySaverController {

	@Autowired
	private CustomerOperations customerOperations;
	
	@Autowired
	private CustomerIOConverter customerConverter;
	
	@Autowired
	private MessageSource ms;
	
	private static final String CUSTOMER_ID_NOT_NULL = "customer.input.id.notnull";
	
	private Logger log = LoggerFactory.getLogger(MoneySaverController.class);
	
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
	
	
	//TODO: SEPARAR EM DOIS ENDPOINTS DIFERENTES DE LOGIN
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
}

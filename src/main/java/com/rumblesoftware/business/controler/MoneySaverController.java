package com.rumblesoftware.business.controler;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
import com.rumblesoftware.io.output.dto.CustomerResponseDto;

@RestController(value = "/moneysaver")
public class MoneySaverController {

	@Autowired
	private CustomerOperations customerOperations;
	
	@Autowired
	private CustomerIOConverter customerConverter;
	
	@Autowired
	private MessageSource ms;
	
	private static final String CUSTOMER_ID_NOT_NULL = "customer.input.id.notnull";
	
	@PostMapping("/customer")
	public ResponseEntity<CustomerResponseDto> createNewCustomer(@Valid @RequestBody CustomerInputDTO customer,BindingResult bindingResult) {
		
		CustomerResponseDto response = new CustomerResponseDto();
		
		if (bindingResult.hasErrors()) {
			
			bindingResult.getAllErrors()
				.stream()
				.forEach(error -> response.addError(error.getDefaultMessage()));
			
			response.setResponseBody(customerConverter.convertInputToOutput(customer));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setResponseBody(customerOperations.createCustomer(customer));
		
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/customer")
	public ResponseEntity<CustomerResponseDto> updateCustomer(@RequestBody CustomerInputPatchDto customer){
		CustomerResponseDto response = new CustomerResponseDto();
		
		if(customer.getCustomerId() == null) {
			response.addError(ms.getMessage(CUSTOMER_ID_NOT_NULL, null,Locale.getDefault()));
			response.setResponseBody(customerConverter.convertPatchToOutput(customer));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setResponseBody(customerOperations.updateCustomer(customer));
		return ResponseEntity.ok(response);
	}
}

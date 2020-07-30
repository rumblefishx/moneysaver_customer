package com.rumblesoftware.business.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.output.dto.CustomerResponseDto;

@RestController(value = "/moneysaver")
public class MoneySaverController {

	@Autowired
	private CustomerOperations customerOperations;
	
	@Autowired
	private CustomerIOConverter customerConverter;
	
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
}

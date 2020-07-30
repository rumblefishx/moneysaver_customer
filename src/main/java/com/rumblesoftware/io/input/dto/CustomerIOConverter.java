package com.rumblesoftware.io.input.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.io.enums.Gender;
import com.rumblesoftware.io.model.CustomerEntity;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;
import com.rumblesoftware.utils.DateUtils;

@Component
public class CustomerIOConverter {

	@Autowired
	private DateUtils dateUtils;
	
	public CustomerOutputDTO convertInputToOutput(CustomerInputDTO input) {
		
		CustomerOutputDTO output = new CustomerOutputDTO();
		output.setCustomerActive(true);
		output.setCustomerId(1100L);
		output.setGender(Gender.castStringToEnum(input.getGender()));
		output.setDateOfBirth(input.getDateOfBirth());
		output.setEmail(input.getEmail());
		output.setName(input.getName());
		output.setSurname(input.getSurname());
		
		return output;
	}
	
	public CustomerEntity convertInputToEntity(CustomerInputDTO input) {
		CustomerEntity entity = new CustomerEntity();
		
		entity.setActive(true);
		entity.setDateOfBirth(dateUtils.castStringToDate(input.getDateOfBirth()));
		entity.setEmail(input.getEmail());
		entity.setGender(Gender.castStringToEnum(input.getGender()));
		entity.setName(input.getName());
		
		//TODO: Create encryption here
		entity.setPassword(input.getPassword());
		entity.setSurname(input.getSurname());
	
		return entity;
	}
	
	public CustomerOutputDTO convertEntityToOutput(CustomerEntity entity) {
		CustomerOutputDTO output = new CustomerOutputDTO();
		output.setCustomerActive(entity.isActive());
		output.setCustomerId(entity.getCustomerId());
		output.setGender(entity.getGender());
		output.setDateOfBirth(dateUtils.castDateToString(entity.getDateOfBirth()));
		output.setEmail(entity.getEmail());
		output.setName(entity.getName());
		output.setSurname(entity.getSurname());
		
		return output;
	}
}

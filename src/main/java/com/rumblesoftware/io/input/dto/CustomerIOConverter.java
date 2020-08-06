package com.rumblesoftware.io.input.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.io.enums.Gender;
import com.rumblesoftware.io.model.CustomerEntity;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;
import com.rumblesoftware.utils.DateUtils;
import com.rumblesoftware.utils.PasswordSecurity;

@Component
public class CustomerIOConverter {

	@Autowired
	private DateUtils dateUtils;
	
	@Autowired
	private PasswordSecurity passwordSecurity;
	
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
			
		String salt = PasswordSecurity.generateRandomSalt();
		
		Optional<String> securePassowrd = passwordSecurity.hashPassword(input.getPassword(), salt);
		
		if(securePassowrd.isPresent()) {
			entity.setPassword(securePassowrd.get());
			entity.setSalt(salt);
		}
		
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
	
	public CustomerEntity transferPatchToEntity(CustomerInputPatchDto patch,CustomerEntity entity) {
		
		//TODO: VERIFY IF PATCH IS NULL AND THROW AN EXCEPTION
		
		if(patch.getName() != null)
		entity.setName(patch.getName());
		
		if(patch.getSurname() != null)
		entity.setSurname(patch.getSurname());
		
		if(patch.getGender() != null)
		entity.setGender(Gender.castStringToEnum(patch.getGender()));
		
		if(patch.getDateOfBirth() != null)
		entity.setDateOfBirth(dateUtils.castStringToDate(patch.getDateOfBirth()));
		
		if(patch.getEmail() != null)
		entity.setEmail(patch.getEmail());
		
		if(patch.getPassword() != null) {
			String salt = PasswordSecurity.generateRandomSalt();
			
			Optional<String> securePassowrd = passwordSecurity.hashPassword(patch.getPassword(), salt);
			
			if(securePassowrd.isPresent()) {
				entity.setPassword(securePassowrd.get());
				entity.setSalt(salt);
			}
		}

				
		return entity;
	}
	
	public CustomerOutputDTO convertPatchToOutput(CustomerInputPatchDto patch) {
		CustomerOutputDTO output = new CustomerOutputDTO();
		
		output.setCustomerId(patch.getCustomerId());
		output.setDateOfBirth(patch.getDateOfBirth());
		output.setEmail(patch.getEmail());
		output.setGender(Gender.castStringToEnum(patch.getGender()));
		output.setName(patch.getName());
		output.setSurname(patch.getSurname());
		
		return output;
	}
}

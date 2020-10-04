package com.rumblesoftware.io.input.dto;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.io.enums.Gender;
import com.rumblesoftware.io.model.CustomerEntity;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;
import com.rumblesoftware.utils.PasswordSecurity;

@Component
public class CustomerIOConverter {
	
	@Autowired
	private PasswordSecurity passwordSecurity;
	
	private Logger log = LoggerFactory.getLogger(CustomerIOConverter.class);
	
	public CustomerOutputDTO convertInputToOutput(CustomerInputDTO input) {
		
		log.info("Casting CustomerInputDTO to CustomerOutputDTO...");
		
		CustomerOutputDTO output = new CustomerOutputDTO();
		output.setCustomerActive(true);
		output.setCustomerId(1100L);
		output.setEmail(input.getEmail());
		output.setName(input.getName());
		output.setSurname(input.getSurname());
		return output;
	}
	
	public CustomerOutputDTO convertInputPatchToOutput(CustomerInputPatchDto patchDto) {
		
		log.info("Casting CustomerInputPatchDto to CustomerOutputDTO...");
		
		CustomerOutputDTO output = new CustomerOutputDTO();
		output.setCustomerActive(false);
		output.setCustomerId(patchDto.getCustomerId());
		output.setGender(Gender.castStringToEnum(patchDto.getGender()));
		output.setDateOfBirth(patchDto.getDateOfBirth());
		output.setEmail(patchDto.getEmail());
		output.setName(patchDto.getName());
		output.setSurname(patchDto.getSurname());
		return output;
	}
	
	public CustomerEntity convertInputToEntity(CustomerInputDTO input) {
		
		log.info("Casting CustomerInputDTO to CustomerEntity...");
		
		CustomerEntity entity = new CustomerEntity();
		
		entity.setActive(true);
		entity.setEmail(input.getEmail());
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
		
		log.info("Casting CustomerEntity to CustomerOutputDTO...");
		
		CustomerOutputDTO output = new CustomerOutputDTO();
		output.setCustomerActive(entity.isActive());
		output.setCustomerId(entity.getCustomerId());
		output.setEmail(entity.getEmail());
		output.setName(entity.getName());
		output.setSurname(entity.getSurname());
		
		return output;
	}
	
	public CustomerEntity transferPatchToEntity(CustomerInputPatchDto patch,CustomerEntity entity) {
	
		log.info("Transfering updated customer details to entity...");
		
		if(patch.getName() != null)
		entity.setName(patch.getName());
		
		if(patch.getSurname() != null)
		entity.setSurname(patch.getSurname());
		
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
		
		log.info("Casting CustomerInputPatchDto to CustomerOutputDTO...");
		
		CustomerOutputDTO output = new CustomerOutputDTO();
		
		if(patch.getCustomerId() != null)
			output.setCustomerId(patch.getCustomerId());
		
		if(patch.getDateOfBirth() != null)
			output.setDateOfBirth(patch.getDateOfBirth());
		
		if(patch.getEmail() != null)
			output.setEmail(patch.getEmail());
		
		if(patch.getGender() != null)
			output.setGender(Gender.castStringToEnum(patch.getGender()));
		
		if(patch.getName() != null)
			output.setName(patch.getName());
		
		if(patch.getSurname() != null)
			output.setSurname(patch.getSurname());
		
		return output;
	}
	
	public CustomerEntity castLoginDetailsToEntity(LoginDetailsDto loginDetails) {
		
		log.info("Casting LoginDetailsDto to CustomerEntity...");
		
		CustomerEntity ce = new CustomerEntity();
		
		if(loginDetails.getDateOfBirth() != null)
		ce.setEmail(loginDetails.getEmail());
		ce.setName(loginDetails.getName());
		ce.setSurname(loginDetails.getSurname());
		
		if(loginDetails.getExternalId() != null) {
			String salt = PasswordSecurity.generateRandomSalt();
			
			Optional<String> securedId = passwordSecurity.hashPassword(loginDetails.getExternalId(), salt);
			
			if(securedId.isPresent()) {
				ce.setExternalId(securedId.get());
				ce.setSalt(salt);
			}
		}
		
		ce.setExternalIdType(loginDetails.getExternalIdType());
		
		
		return ce;
	} 
}

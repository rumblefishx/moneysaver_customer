package com.rumblesoftware.business;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rumblesoftware.exception.EmailAlreadyRegisteredException;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerOperationsTest {
	
	private static final String DUPLICATED_NAME = "Marta";
	
	private static final String DUPLICATED_SURNAME = "Gum";
	
	private static final String DUPLICATED_PASSWORD = "figL@456";
	
	private static final String DUPLICATED_EMAIL = "gum_marta@pt.com";

	@Autowired
	private CustomerOperations service;
	
	
	@Test(expected = EmailAlreadyRegisteredException.class)
	public void verifyEmailDuplicatedOnCreateUserTest() {
		CustomerInputDTO input = createDuplicatedUser();
		service.createCustomer(input);
	}
	
	private CustomerInputDTO createDuplicatedUser(){
		CustomerInputDTO user = new CustomerInputDTO();
		user.setEmail(DUPLICATED_EMAIL);
		user.setName(DUPLICATED_NAME);
		user.setPassword(DUPLICATED_PASSWORD);
		user.setSurname(DUPLICATED_SURNAME);
		
		return user;
	}
}

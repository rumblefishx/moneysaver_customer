package com.rumblesoftware.business;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rumblesoftware.exception.CustomerNotFoundException;
import com.rumblesoftware.exception.EmailAlreadyRegisteredException;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.output.dto.CustomerOutputDTO;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerOperationsTests {
	
	private static final String DUPLICATED_NAME = "Marta";
	
	private static final String DUPLICATED_SURNAME = "Gum";
	
	private static final String DUPLICATED_PASSWORD = "figL@456";
	
	private static final String DUPLICATED_EMAIL = "gum_marta@pt.com";
	
	private static final String DISTINCT_EMAIL = "gum_alternative@pt.com";

	@Autowired
	private CustomerOperations service;
	
	
	@Test(expected = EmailAlreadyRegisteredException.class)
	public void verifyEmailDuplicatedOnCreateUserTest() {
		CustomerInputDTO input = createDuplicatedUser();
		service.createCustomer(input);
	}
	
	@Test
	public void createNewUserTest() {
		CustomerInputDTO input = createDuplicatedUser();
		input.setEmail(DISTINCT_EMAIL);
		CustomerOutputDTO dto = service.createCustomer(input);
		assertTrue(dto != null);
	}
	
	@Test
	public void verifyFindCustomerByIdTest() {
		CustomerOutputDTO dto = service.findByCustomerId(7L);
		assertTrue(dto != null);
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void verifyFindCustomerByIdFailTest() {
		CustomerOutputDTO dto = service.findByCustomerId(2000L);
		assertTrue(dto != null);
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

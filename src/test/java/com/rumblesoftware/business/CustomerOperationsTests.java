package com.rumblesoftware.business;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
import com.rumblesoftware.exception.LoginDataNotFoundException;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.io.input.dto.CustomerInputPatchDto;
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
	
	
	private static final Long ID_TO_UPDATE = 9L;
	private static final String UPDATED_NAME = "Jhony";
	private static final String UPDATED_SURNAME = "Silverhand";
	private static final String UPDATED_PASSWORD = "ca88953oP@";
	private static final String UPDATED_EMAIL = "Jhon@google.com";
	
	
	private static final String INTERNAL_EMAIL_LG_OK = "mary_claire@gmail.com";	
	private static final String INTERNAL_PASSWORD_LG_OK = "bk556633Lp@";	
	private static final String INTERNAL_PASSWORD_LG_FAIL = "kkhu@";

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
	
	@Test
	public void internalLoginOKTest() {
		Optional<CustomerOutputDTO> customer = Optional.of(
				service.findUserByPasswdAndCredential(INTERNAL_EMAIL_LG_OK, INTERNAL_PASSWORD_LG_OK));
		
		assertTrue(customer.isPresent());
	}
	
	@Test(expected = LoginDataNotFoundException.class)
	public void internalLoginFailTest() {
		service.findUserByPasswdAndCredential(INTERNAL_PASSWORD_LG_FAIL, INTERNAL_PASSWORD_LG_OK);
	}
	
	@Test
	public void updateOKTest() {
		CustomerOutputDTO output = service.updateCustomer(createPatchDtoInstance());
		
		assertTrue(output.getName().equals(UPDATED_NAME));
		assertTrue(output.getEmail().equals(UPDATED_EMAIL));
		assertTrue(output.getSurname().equals(UPDATED_SURNAME));
	}
	
	
	private CustomerInputDTO createDuplicatedUser(){
		CustomerInputDTO user = new CustomerInputDTO();
		user.setEmail(DUPLICATED_EMAIL);
		user.setName(DUPLICATED_NAME);
		user.setPassword(DUPLICATED_PASSWORD);
		user.setSurname(DUPLICATED_SURNAME);
		
		return user;
	}
	
	private CustomerInputPatchDto createPatchDtoInstance(){
		CustomerInputPatchDto patch = new CustomerInputPatchDto();
		patch.setCustomerId(ID_TO_UPDATE);
		patch.setEmail(UPDATED_EMAIL);
		patch.setName(UPDATED_NAME);
		patch.setPassword(UPDATED_PASSWORD);
		patch.setSurname(UPDATED_SURNAME);
		
		return patch;
	}
	
	

}

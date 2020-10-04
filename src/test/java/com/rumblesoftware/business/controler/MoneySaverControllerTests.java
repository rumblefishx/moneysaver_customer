package com.rumblesoftware.business.controler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.utils.PostOfficer;

@WebMvcTest(MoneySaverController.class)
public class MoneySaverControllerTests {
	
	private static final String EMAIL = "defaultUser@email.com";
	
	private static final String NAME = "daniel";
	
	private static final String SURNAME = "houston";
	
	private static final String PASSWORD =  "b76lG@23";

	private static final String MISSING_AT_EMAIL = "fulanoemail.com";
	
	private static final String MISSING_COM_EMAIL = "fulano@email";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private CustomerOperations customerOperations;
	
	@MockBean
	private CustomerIOConverter converter;
	
	@MockBean
	private PostOfficer po;
	
	@Test
	public void createUserWithAllFieldsFilledTest() throws JsonProcessingException, Exception {
		
		CustomerInputDTO input = getFilledCustomerInputDTO();		

	   mockMvc.perform(post("/customer")
	        .contentType("application/json")
	        .content(mapper.writeValueAsString(input)))
	        .andExpect(status().isOk());
	}
	
	@Test
	public void createUserWithNullValuesTest() throws JsonProcessingException, Exception {	
		CustomerInputDTO input = new CustomerInputDTO();		

		Mockito.when(po.getMessage(Mockito.anyString())).thenReturn("mockedString");
		
	   mockMvc.perform(post("/customer")
	        .contentType("application/json")
	        .content(mapper.writeValueAsString(input)))
	        .andExpect(status().isBadRequest());
	}
	
	@Test
	public void createUserEmailWithoutAtTest() throws JsonProcessingException, Exception {
		CustomerInputDTO input = getFilledCustomerInputDTO();
		input.setEmail(MISSING_AT_EMAIL);

		Mockito.when(po.getMessage(Mockito.anyString())).thenReturn("mockedString");
		
	   System.out.println(mockMvc.perform(post("/customer")
	        .contentType("application/json")
	        .content(mapper.writeValueAsString(input)))
	        .andExpect(status().isBadRequest()));	
	}
	
	
	private CustomerInputDTO getFilledCustomerInputDTO() {
		CustomerInputDTO input = new CustomerInputDTO();
		
		input.setEmail(EMAIL);
		input.setName(NAME);
		input.setSurname(SURNAME);
		input.setPassword(PASSWORD);
		
		return input;
	}
}

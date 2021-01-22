package com.rumblesoftware.business.controler;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumblesoftware.business.CustomerOperations;
import com.rumblesoftware.io.input.dto.CustomerIOConverter;
import com.rumblesoftware.io.input.dto.CustomerInputDTO;
import com.rumblesoftware.utils.PostOfficer;

@WebMvcTest(MoneySaverController.class)
@ActiveProfiles("test")
public class MoneySaverControllerTests {
	
	private static final String EMAIL = "defaultUser@email.com";
	
	private static final String NAME = "daniel";
	
	private static final String SURNAME = "houston";
	
	private static final String PASSWORD =  "b76lG@23";

	private static final String MISSING_AT_EMAIL = "fulanoemail.com";
	
	private static final String MISSING_SUFFIX_EMAIL = "fulano@email";
	
	private static final String INVALID_EMAIL_ERROR_ID = "customer.input.email.invalid";
	
	private static final String NAME_MAX_LENGTH_ERROR_ID = "customer.input.name.maxlength";
	
	private static final String SURNAME_MAX_LENGTH_ERROR_ID = "customer.input.name.maxlength";
	
	private static final String NAME_NOT_BLANK_ERROR_ID = "customer.input.name.notblank";
	private static final String SURNAME_NOT_BLANK_ERROR_ID = "customer.input.surname.notblank";
	private static final String EMAIL_NOT_BLANK_ERROR_ID = "customer.input.email.notblank";
	private static final String PASSWORD_NOT_BLANK_ERROR_ID = "customer.input.password.notblank";
	
	private static final String LONGER_USER_NAME = "MY NAME IS REALLY HUGE. I AM TOTALLY SURE ABOUT IT";
	
	private static final String LONGER_USER_SURNAME = "PROBABLY MY NAME IS THE LARGEST NAME IN THE WORLD,MAYBE AN OLD AND LOYAL NAME, WHAT DO YOU THINK ABOUT IT ?";
	
	private static final String BLANK_STRING = " ";
	
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private PostOfficer po;
	
	@MockBean
	private CustomerOperations customerOperations;
	
	@MockBean
	private CustomerIOConverter converter;
	
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

		Mockito.when(po.getMessage(Mockito.anyString())).thenAnswer(returnTheSameInput());
		
	   mockMvc.perform(post("/customer")
	        .contentType("application/json")
	        .content(mapper.writeValueAsString(input)))
	        .andExpect(status().isBadRequest())
	        .andExpect(jsonPath("$.errors[0]", containsString(INVALID_EMAIL_ERROR_ID)));
	}
	
	@Test
	public void createUserEmailWithoutSufix() throws JsonProcessingException, Exception {
		CustomerInputDTO input = getFilledCustomerInputDTO();
		input.setEmail(MISSING_SUFFIX_EMAIL);

		Mockito.when(po.getMessage(Mockito.anyString())).thenAnswer(returnTheSameInput());
		
	   mockMvc.perform(post("/customer")
	        .contentType("application/json")
	        .content(mapper.writeValueAsString(input)))
	        .andExpect(status().isBadRequest())
	        .andExpect(jsonPath("$.errors[0]", containsString(INVALID_EMAIL_ERROR_ID)));
	}
	
	@Test
	public void createUserWithLongNameTest() throws JsonProcessingException, Exception {
		CustomerInputDTO input = getFilledCustomerInputDTO();
		input.setName(LONGER_USER_NAME);
		
		Mockito.when(po.getMessage(Mockito.anyString())).thenAnswer(returnTheSameInput());
		
		   mockMvc.perform(post("/customer")
		        .contentType("application/json")
		        .content(mapper.writeValueAsString(input)))
		        .andExpect(status().isBadRequest())
		        .andExpect(jsonPath("$.errors[0]", containsString(NAME_MAX_LENGTH_ERROR_ID)));
	}
	
	@Test
	public void createUserWithLongSurnameTest() throws JsonProcessingException, Exception {
		CustomerInputDTO input = getFilledCustomerInputDTO();
		input.setName(LONGER_USER_SURNAME);
		
		Mockito.when(po.getMessage(Mockito.anyString())).thenAnswer(returnTheSameInput());
		
		   mockMvc.perform(post("/customer")
		        .contentType("application/json")
		        .content(mapper.writeValueAsString(input)))
		        .andExpect(status().isBadRequest())
		        .andExpect(jsonPath("$.errors[0]", containsString(SURNAME_MAX_LENGTH_ERROR_ID)));
	}
	
	@Test
	public void createUserWithBlankFields() throws JsonProcessingException, Exception {
		CustomerInputDTO input = getFilledCustomerInputDTO();
		input.setName(BLANK_STRING);
		input.setEmail(BLANK_STRING);
		input.setPassword(BLANK_STRING);
		input.setSurname(BLANK_STRING);
		
		Mockito.when(po.getMessage(Mockito.anyString())).thenAnswer(returnTheSameInput());
		
		   mockMvc.perform(post("/customer")
		        .contentType("application/json")
		        .content(mapper.writeValueAsString(input)))
		        .andExpect(status().isBadRequest())
		        .andExpect(jsonPath("$.errors[*]", 
		        		containsInAnyOrder(NAME_NOT_BLANK_ERROR_ID
		        				,SURNAME_NOT_BLANK_ERROR_ID
		        				,EMAIL_NOT_BLANK_ERROR_ID
		        				,PASSWORD_NOT_BLANK_ERROR_ID)));
	}
	
	
//	public void findCustomerById() {
//		   mockMvc.perform(get("/customer")
//			        .contentType("application/json")
//			        .content(mapper.writeValueAsString(input)))
//			        .andExpect(status().isBadRequest())
//			        .andExpect(jsonPath("$.errors[*]", 
//			        		containsInAnyOrder(NAME_NOT_BLANK_ERROR_ID
//			        				,SURNAME_NOT_BLANK_ERROR_ID
//			        				,EMAIL_NOT_BLANK_ERROR_ID
//			        				,PASSWORD_NOT_BLANK_ERROR_ID)));		
//	}

	
	private CustomerInputDTO getFilledCustomerInputDTO() {
		CustomerInputDTO input = new CustomerInputDTO();
		
		input.setEmail(EMAIL);
		input.setName(NAME);
		input.setSurname(SURNAME);
		input.setPassword(PASSWORD);
		
		return input;
	}
	
	
	//This method is responsible for catch Mockito.anyString value and return it in the method answer
	private Answer<String> returnTheSameInput() {
		return new Answer<String>() {
		    @Override
		    public String answer(InvocationOnMock invocation) throws Throwable {
		      Object[] args = invocation.getArguments();
		      return (String) args[0];
		    }
		  };
	}
	
	
}

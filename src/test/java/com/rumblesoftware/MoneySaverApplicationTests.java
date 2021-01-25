package com.rumblesoftware;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rumblesoftware.business.CustomerOperationsTests;
import com.rumblesoftware.business.controler.MoneySaverControllerTests;
import com.rumblesoftware.io.repository.CustomerRepositoryTests;

@RunWith(Suite.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test", "webtest"}) 
@Suite.SuiteClasses(value = { CustomerOperationsTests.class,MoneySaverControllerTests.class,CustomerRepositoryTests.class })
public class MoneySaverApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
}

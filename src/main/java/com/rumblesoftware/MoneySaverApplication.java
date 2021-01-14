package com.rumblesoftware;

import java.util.Locale;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author Cleiton
 * Main spring boot class
 */
@SpringBootApplication
public class MoneySaverApplication implements CommandLineRunner {

	/**
	 * Injecting dataSource for the Flyway workaround purpose
	 */
	@Autowired
	DataSource dataSource;

	/**
	 * When using flyway + Hibernate we have a boot issue during initialization, what happens is that
	 * flyway tries to insert data on the database and hibernate had not created it. This way we can use
	 * the following workaround to solve the problem:
	 * 
	 *  1 - Create the first migration script with a higher version (cuz normally the V1 is initialized during boot) 
	 *  2 - Stop the Flyway initialization on application.properties
	 *  3 - Force the Flyway initialization after all (using the method below)
	 */
	@Override
	public void run(String... args) throws Exception {
		Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
	}

	
	/**
	 * Method responsible for setup the MessageSource component
	 * @return a ready instance of MessageSource
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultLocale(Locale.ENGLISH);
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Method responsible for set a Default Locale to MessageSource LocaleResolver
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	/**
	 * Create an instance of a LocalValidatorFactoryBean managed by Spring Context.
	 * This way is possible to use it in order to verify message contents during Junit validations
	 * @return an Instance of a LocalValidatorFactoryBean
	 */
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	/**
	 * Method responsible for create an instance of the MethodValidationPostProcessor
	 * In order to test the service layer with Junit
	 * @return an instance of a MethodValidationPostProcessor
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	/**
	 * Java Main method
	 * @param args : initialization arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MoneySaverApplication.class, args);
	}

}

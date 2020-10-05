package com.rumblesoftware.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class PostOfficer {

	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String messageId) {
		System.out.println("Config setada no messageSource:");
		//messageSource.getBasenameSet().forEach(n -> System.out.println(n));
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(messageId,null,locale);
	}
}

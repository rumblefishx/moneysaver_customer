package com.rumblesoftware.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class PostOfficer {

	@Autowired
	@Qualifier(value = "messageSource")
	private MessageSource ms;
	
	public String getMessage(String messageId) {
		return ms.getMessage(messageId,null,Locale.getDefault());
	}
}

package com.rumblesoftware.utils;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

@Component
@PropertySource("classpath:application.properties")
public class DateUtils {

	@Value(value = "${moneysaver.date.pattern}")
	public String datePattern;
	
	public Date castStringToDate(String dateStr) {
		
		Date dt = null;
		
		DateFormat df = new SimpleDateFormat(datePattern);
		
		try {
			dt = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO create exception treatment for date conversion
			e.printStackTrace();
		}
		
		return dt;
	}
	
	public String castDateToString(Date dt) {
		DateFormat df = new SimpleDateFormat(datePattern);
		return df.format(dt);
	}
	
}

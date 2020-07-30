package com.rumblesoftware.utils;

import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

@Component
public class DateUtils {

	//TODO: Load date pattern from properties
	
	public Date castStringToDate(String dateStr) {
		
		Date dt = null;
		
		DateFormat df = new SimpleDateFormat("dd/mm/YYYY");
		
		try {
			dt = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO create exception treatment for date conversion
			e.printStackTrace();
		}
		
		return dt;
	}
	
	public String castDateToString(Date dt) {
		DateFormat df = new SimpleDateFormat("dd/mm/YYYY");
		return df.format(dt);
	}
	
}

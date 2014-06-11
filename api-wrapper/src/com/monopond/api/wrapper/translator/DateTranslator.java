package com.monopond.api.wrapper.translator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTranslator implements Translator<Date, String> {

	private static final String ISO8601_FORMAT =
										"yyyy-MM-dd'T'HH:mm:ssZ";
	
	@Override
	public String toApi(Date date) {
		if (date ==  null) {
			return null;
		} 
		DateFormat df = new SimpleDateFormat(ISO8601_FORMAT);
		return df.format(date);
	}

	@Override
	public Date toWrapper(String apiDate) {
		if (apiDate == null || apiDate.isEmpty()) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(ISO8601_FORMAT);
		try {
			return df.parse(apiDate);
		} catch (ParseException e) {
			throw new TranslationException(String.class, Date.class, e);
		}
	}

}

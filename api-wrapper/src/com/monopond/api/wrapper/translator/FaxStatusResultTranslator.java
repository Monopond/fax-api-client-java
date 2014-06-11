package com.monopond.api.wrapper.translator;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatusResults;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxErrorAdapter;
import com.monopond.api.wrapper.model.FaxError;
import com.monopond.api.wrapper.model.FaxResult;
import com.monopond.api.wrapper.model.FaxStatusResult;

public class FaxStatusResultTranslator implements
		Translator<FaxStatusResult, ApiFaxMessageStatusResults> {
	private Translator<FaxResult, ApiServiceStub.FaxResult>
		faxResultTranslator = new FaxResultTranslator();
	private Translator<FaxError, FaxErrorAdapter>
		faxErrorTranslator = new FaxErrorTranslator();
	
	@Override
	public ApiFaxMessageStatusResults toApi(FaxStatusResult faxStatusResult) {
		if (faxStatusResult == null) {
			return null;
		}
		ApiFaxMessageStatusResults apiFaxStatusResults =
			new ApiFaxMessageStatusResults();
		
		apiFaxStatusResults.setAttempt(faxStatusResult.getAttempt());
		apiFaxStatusResults.setResult(faxResultTranslator
			.toApi(faxStatusResult.getResult()));
		apiFaxStatusResults.setError(faxErrorTranslator
			.toApi(faxStatusResult.getError()));
		apiFaxStatusResults.setCost(faxStatusResult.getCost().toString());
		apiFaxStatusResults.setPages(faxStatusResult.getPages());
		apiFaxStatusResults.setScheduledStartTime(toCalendar(faxStatusResult
			.getScheduledStartTime()));
		apiFaxStatusResults.setDateCallStarted(toCalendar(faxStatusResult
			.getDateCallStarted()));
		apiFaxStatusResults.setDateCallEnded(toCalendar(faxStatusResult
			.getDateCallEnded()));
		return apiFaxStatusResults;
	}
	
	private Calendar toCalendar(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
	}

	@Override
	public FaxStatusResult toWrapper(ApiFaxMessageStatusResults apiFaxStatusResults) {
		if (apiFaxStatusResults == null) {
			return null;
		}
		FaxStatusResult faxStatusResult = new FaxStatusResult();
		
		faxStatusResult.setAttempt(apiFaxStatusResults.getAttempt());
		faxStatusResult.setResult(faxResultTranslator
			.toWrapper(apiFaxStatusResults.getResult()));
		faxStatusResult.setError(faxErrorTranslator
			.toWrapper(apiFaxStatusResults.getError()));
		faxStatusResult.setCost(new BigDecimal(apiFaxStatusResults.getCost()));
		faxStatusResult.setPages(apiFaxStatusResults.getPages());
		faxStatusResult.setScheduledStartTime(toDate(apiFaxStatusResults
			.getScheduledStartTime()));
		faxStatusResult.setDateCallStarted(toDate(apiFaxStatusResults
			.getDateCallStarted()));
		faxStatusResult.setDateCallEnded(toDate(apiFaxStatusResults
			.getDateCallEnded()));
		return faxStatusResult;
	}
	
	private Date toDate(Calendar cal) {
		return cal == null? null: cal.getTime();
	}

}

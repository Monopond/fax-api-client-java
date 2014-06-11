package com.monopond.api.wrapper.translator;

import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatus;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatusDetails;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatusResults;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxResults_type0;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.PhoneNumber;
import com.monopond.api.wrapper.model.FaxDetails;
import com.monopond.api.wrapper.model.FaxMessageStatus;
import com.monopond.api.wrapper.model.FaxStatusResult;

public class FaxMessageStatusTranslator implements
		Translator<FaxMessageStatus, ApiFaxMessageStatus> {
	private Translator<String, PhoneNumber>
		phoneNumberTranslator = new PhoneNumberTranslator();
	private Translator<FaxDetails, ApiFaxMessageStatusDetails>
		faxDetailsTranslator = new FaxDetailsTranslator();
	private Translator<List<FaxStatusResult>, ApiFaxMessageStatusResults[]>
		faxResultListTranslator = new FaxResultListTranslator();

	@Override
	public ApiFaxMessageStatus toApi(FaxMessageStatus faxMessageStatus) {
		if (faxMessageStatus == null) {
			return null;
		}
		ApiFaxMessageStatus apiFaxMessageStatus =
			new ApiFaxMessageStatus();
		
		apiFaxMessageStatus.setMessageRef(faxMessageStatus.getMessageRef());
		apiFaxMessageStatus.setSendRef(faxMessageStatus.getSendRef());
		apiFaxMessageStatus.setBroadcastRef(faxMessageStatus.getSendRef());
		apiFaxMessageStatus.setSendTo(phoneNumberTranslator
			.toApi(faxMessageStatus.getSendTo()));
		apiFaxMessageStatus.setFaxDetails(faxDetailsTranslator
			.toApi(faxMessageStatus.getDetails()));
		apiFaxMessageStatus.setFaxResults(wrapArray(faxResultListTranslator
			.toApi(faxMessageStatus.getResults())));
		return apiFaxMessageStatus;
	}
	
	private FaxResults_type0 wrapArray(ApiFaxMessageStatusResults[] array) {
		FaxResults_type0 faxResults = new FaxResults_type0();
		faxResults.setFaxResult(array);
		
		return faxResults;
	}

	@Override
	public FaxMessageStatus toWrapper(ApiFaxMessageStatus apiFaxMessageStatus) {
		if (apiFaxMessageStatus == null) {
			return null;
		}
		FaxMessageStatus faxMessageStatus = new FaxMessageStatus();
		
		faxMessageStatus.setMessageRef(apiFaxMessageStatus.getMessageRef());
		faxMessageStatus.setSendRef(apiFaxMessageStatus.getSendRef());
		faxMessageStatus.setBroadcastRef(apiFaxMessageStatus.getBroadcastRef());
		faxMessageStatus.setSendTo(phoneNumberTranslator
			.toWrapper(apiFaxMessageStatus.getSendTo()));
		faxMessageStatus.setDetails(faxDetailsTranslator
			.toWrapper(apiFaxMessageStatus.getFaxDetails()));
		if (apiFaxMessageStatus.getFaxResults() != null) {
			faxMessageStatus.setResults(faxResultListTranslator
				.toWrapper(apiFaxMessageStatus.getFaxResults() .getFaxResult()));
		}
		return faxMessageStatus;
	}

}

package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatusDetails;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxResolution;
import com.monopond.api.wrapper.model.FaxDetails;
import com.monopond.api.wrapper.model.Resolution;

public class FaxDetailsTranslator implements
		Translator<FaxDetails, ApiFaxMessageStatusDetails> {
	private Translator<Resolution, FaxResolution>
		resolutionTranslator = new ResolutionTranslator();
	
	@Override
	public ApiFaxMessageStatusDetails toApi(FaxDetails faxDetails) {
		if (faxDetails == null) {
			return null;
		}
		ApiFaxMessageStatusDetails apiFaxDetails =
			new ApiFaxMessageStatusDetails();
		
		apiFaxDetails.setSendFrom(faxDetails.getSendFrom());
		apiFaxDetails.setResolution(resolutionTranslator
			.toApi(faxDetails.getResolution()));
		apiFaxDetails.setRetries(faxDetails.getRetries());
		apiFaxDetails.setBusyRetries(faxDetails.getBusyRetries());
		apiFaxDetails.setHeaderFormat(faxDetails.getHeaderFormat());
		return apiFaxDetails;
	}

	@Override
	public FaxDetails toWrapper(ApiFaxMessageStatusDetails apiFaxDetails) {
		if (apiFaxDetails == null) {
			return null;
		}
		FaxDetails faxDetails = new FaxDetails();
		
		faxDetails.setSendFrom(apiFaxDetails.getSendFrom());
		faxDetails.setResolution(resolutionTranslator
			.toWrapper(apiFaxDetails.getResolution()));
		faxDetails.setRetries(apiFaxDetails.getRetries());
		faxDetails.setBusyRetries(apiFaxDetails.getBusyRetries());
		faxDetails.setHeaderFormat(apiFaxDetails.getHeaderFormat());
		return faxDetails;
	}

}

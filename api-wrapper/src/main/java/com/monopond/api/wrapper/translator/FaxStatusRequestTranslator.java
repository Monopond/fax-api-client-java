package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusLevel;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusRequestE;
import com.monopond.api.wrapper.model.FaxStatusRequest;
import com.monopond.api.wrapper.model.VerbosityLevel;

public class FaxStatusRequestTranslator implements
		Translator<FaxStatusRequest, FaxStatusRequestE> {
	private Translator<VerbosityLevel, FaxStatusLevel>
		verbosityLevelTranslator = new VerbosityLevelTranslator();
	
	@Override
	public FaxStatusRequestE toApi(FaxStatusRequest request) {
		if (request == null) {
			return null;
		}
		ApiServiceStub.FaxStatusRequest apiRequest =
			new ApiServiceStub.FaxStatusRequest();
		
		apiRequest.setBroadcastRef(request.getBroadcastRef());
		apiRequest.setSendRef(request.getSendRef());
		apiRequest.setMessageRef(request.getMessageRef());
		apiRequest.setVerbosity(verbosityLevelTranslator
			.toApi(request.getVerbosity()));
		
		FaxStatusRequestE rv = new FaxStatusRequestE();
		rv.setFaxStatusRequest(apiRequest);
		return rv;
	}

	@Override
	public FaxStatusRequest toWrapper(FaxStatusRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		ApiServiceStub.FaxStatusRequest apiStatusRequest =
			new ApiServiceStub.FaxStatusRequest();
		FaxStatusRequest request = new FaxStatusRequest();
		
		request.setBroadcastRef(apiStatusRequest.getBroadcastRef());
		request.setSendRef(apiStatusRequest.getSendRef());
		request.setMessageRef(apiStatusRequest.getMessageRef());
		request.setVerbosity(verbosityLevelTranslator
			.toWrapper(apiStatusRequest.getVerbosity()));
		return request;
	}

}

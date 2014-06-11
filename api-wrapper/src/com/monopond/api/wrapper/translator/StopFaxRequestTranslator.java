package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.StopFaxRequestE;
import com.monopond.api.wrapper.model.StopFaxRequest;

public class StopFaxRequestTranslator implements
		Translator<StopFaxRequest, StopFaxRequestE> {

	@Override
	public StopFaxRequestE toApi(StopFaxRequest request) {
		if (request == null) {
			return null;
		}
		ApiServiceStub.StopFaxRequest apiRequest =
			new ApiServiceStub.StopFaxRequest();
		
		apiRequest.setBroadcastRef(request.getBroadcastRef());
		apiRequest.setSendRef(request.getSendRef());
		apiRequest.setMessageRef(request.getMessageRef());
		
		StopFaxRequestE rv = new StopFaxRequestE();
		rv.setStopFaxRequest(apiRequest);
		return rv;
	}

	@Override
	public StopFaxRequest toWrapper(StopFaxRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		ApiServiceStub.StopFaxRequest apiObject =
			apiRequest.getStopFaxRequest();
		StopFaxRequest request = new StopFaxRequest();
		
		request.setBroadcastRef(apiObject.getBroadcastRef());
		request.setSendRef(apiObject.getSendRef());
		request.setMessageRef(apiObject.getMessageRef());
		return request;
	}

}

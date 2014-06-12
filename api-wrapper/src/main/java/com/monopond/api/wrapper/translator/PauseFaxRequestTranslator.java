package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.PauseFaxRequestE;
import com.monopond.api.wrapper.model.PauseFaxRequest;

public class PauseFaxRequestTranslator implements
		Translator<PauseFaxRequest, PauseFaxRequestE> {

	@Override
	public PauseFaxRequestE toApi(PauseFaxRequest request) {
		if (request == null) {
			return null;
		}
		ApiServiceStub.PauseFaxRequest apiRequest =
			new ApiServiceStub.PauseFaxRequest();
		
		apiRequest.setBroadcastRef(request.getBroadcastRef());
		apiRequest.setSendRef(request.getSendRef());
		apiRequest.setMessageRef(request.getMessageRef());
		
		PauseFaxRequestE rv = new PauseFaxRequestE();
		rv.setPauseFaxRequest(apiRequest);
		return rv;
	}

	@Override
	public PauseFaxRequest toWrapper(PauseFaxRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		ApiServiceStub.PauseFaxRequest apiObject =
			apiRequest.getPauseFaxRequest();
		PauseFaxRequest request = new PauseFaxRequest();
		
		request.setBroadcastRef(apiObject.getBroadcastRef());
		request.setSendRef(apiObject.getSendRef());
		request.setMessageRef(apiObject.getMessageRef());
		return request;
	}

}

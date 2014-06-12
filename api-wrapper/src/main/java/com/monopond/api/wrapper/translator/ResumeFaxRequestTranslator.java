package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ResumeFaxRequestE;
import com.monopond.api.wrapper.model.ResumeFaxRequest;

public class ResumeFaxRequestTranslator implements
		Translator<ResumeFaxRequest, ResumeFaxRequestE> {

	@Override
	public ResumeFaxRequestE toApi(ResumeFaxRequest request) {
		if (request == null) {
			return null;
		}
		ApiServiceStub.ResumeFaxRequest apiRequest =
			new ApiServiceStub.ResumeFaxRequest();
		
		apiRequest.setBroadcastRef(request.getBroadcastRef());
		apiRequest.setSendRef(request.getSendRef());
		apiRequest.setMessageRef(request.getMessageRef());
		
		ResumeFaxRequestE rv = new ResumeFaxRequestE();
		rv.setResumeFaxRequest(apiRequest);
		return rv;
	}

	@Override
	public ResumeFaxRequest toWrapper(ResumeFaxRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		ApiServiceStub.ResumeFaxRequest apiObject =
			apiRequest.getResumeFaxRequest();
		ResumeFaxRequest request = new ResumeFaxRequest();
		
		request.setBroadcastRef(apiObject.getBroadcastRef());
		request.setSendRef(apiObject.getSendRef());
		request.setMessageRef(apiObject.getMessageRef());
		return request;
	}

}

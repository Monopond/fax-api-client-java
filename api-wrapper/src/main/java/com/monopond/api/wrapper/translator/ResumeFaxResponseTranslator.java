package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ResumeFaxResponseE;
import com.monopond.api.wrapper.model.FaxStatusResponse;
import com.monopond.api.wrapper.model.ResumeFaxResponse;

public class ResumeFaxResponseTranslator implements
		Translator<ResumeFaxResponse, ResumeFaxResponseE> {

	private Translator<FaxStatusResponse, FaxStatusResponseE>
		delegate = new FaxStatusResponseTranslator();
	
	@Override
	public ResumeFaxResponseE toApi(ResumeFaxResponse response) {
		if (response == null) {
			return null;
		}
		ApiServiceStub.FaxStatusResponse apiResponseE =
			delegate.toApi(response).getFaxStatusResponse();
		ApiServiceStub.ResumeFaxResponse apiStatusResponse =
			new ApiServiceStub.ResumeFaxResponse();
		
		apiStatusResponse.setFaxStatusTotals(apiResponseE.getFaxStatusTotals());
		apiStatusResponse.setFaxResultsTotals(apiResponseE.getFaxResultsTotals());
		apiStatusResponse.setFaxMessages(apiResponseE.getFaxMessages());
		
		ResumeFaxResponseE rv = new ResumeFaxResponseE();
		rv.setResumeFaxResponse(apiStatusResponse);
		return rv;
	}
	
	@Override
	public ResumeFaxResponse toWrapper(ResumeFaxResponseE apiResponse) {
		if (apiResponse == null) {
			return null;
		}
		FaxStatusResponseE apiResponseE =
			new FaxStatusResponseE();
		apiResponseE.setFaxStatusResponse(apiResponse.getResumeFaxResponse());
		FaxStatusResponse response = delegate.toWrapper(apiResponseE);
		
		ResumeFaxResponse rv = new ResumeFaxResponse();
		rv.setFaxStatusTotals(response.getFaxStatusTotals());
		rv.setFaxResultsTotals(response.getFaxResultsTotals());
		rv.setFaxMessages(response.getFaxMessages());
		return rv;
	}

}

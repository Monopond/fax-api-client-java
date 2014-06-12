package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.PauseFaxResponseE;
import com.monopond.api.wrapper.model.FaxStatusResponse;
import com.monopond.api.wrapper.model.PauseFaxResponse;

public class PauseFaxResponseTranslator implements
		Translator<PauseFaxResponse, PauseFaxResponseE> {

	private Translator<FaxStatusResponse, FaxStatusResponseE>
		delegate = new FaxStatusResponseTranslator();
	
	@Override
	public PauseFaxResponseE toApi(PauseFaxResponse response) {
		if (response == null) {
			return null;
		}
		ApiServiceStub.FaxStatusResponse apiResponseE =
			delegate.toApi(response).getFaxStatusResponse();
		ApiServiceStub.PauseFaxResponse apiStatusResponse =
			new ApiServiceStub.PauseFaxResponse();
		
		apiStatusResponse.setFaxStatusTotals(apiResponseE.getFaxStatusTotals());
		apiStatusResponse.setFaxResultsTotals(apiResponseE.getFaxResultsTotals());
		apiStatusResponse.setFaxMessages(apiResponseE.getFaxMessages());
		
		PauseFaxResponseE rv = new PauseFaxResponseE();
		rv.setPauseFaxResponse(apiStatusResponse);
		return rv;
	}

	@Override
	public PauseFaxResponse toWrapper(PauseFaxResponseE apiResponse) {
		if (apiResponse == null) {
			return null;
		}
		FaxStatusResponseE apiResponseE =
			new FaxStatusResponseE();
		apiResponseE.setFaxStatusResponse(apiResponse.getPauseFaxResponse());
		FaxStatusResponse response = delegate.toWrapper(apiResponseE);
		
		PauseFaxResponse rv = new PauseFaxResponse();
		rv.setFaxStatusTotals(response.getFaxStatusTotals());
		rv.setFaxResultsTotals(response.getFaxResultsTotals());
		rv.setFaxMessages(response.getFaxMessages());
		return rv;
	}

}

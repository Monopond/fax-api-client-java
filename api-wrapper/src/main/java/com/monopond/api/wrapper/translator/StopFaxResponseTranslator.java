package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.StopFaxResponseE;
import com.monopond.api.wrapper.model.FaxStatusResponse;
import com.monopond.api.wrapper.model.StopFaxResponse;

public class StopFaxResponseTranslator implements
		Translator<StopFaxResponse, StopFaxResponseE> {

	private Translator<FaxStatusResponse, FaxStatusResponseE>
		delegate = new FaxStatusResponseTranslator();
	
	@Override
	public StopFaxResponseE toApi(StopFaxResponse response) {
		if (response == null) {
			return null;
		}
		ApiServiceStub.FaxStatusResponse apiResponseE =
			delegate.toApi(response).getFaxStatusResponse();
		ApiServiceStub.StopFaxResponse apiStatusResponse =
			new ApiServiceStub.StopFaxResponse();
		
		apiStatusResponse.setFaxStatusTotals(apiResponseE.getFaxStatusTotals());
		apiStatusResponse.setFaxResultsTotals(apiResponseE.getFaxResultsTotals());
		apiStatusResponse.setFaxMessages(apiResponseE.getFaxMessages());
		
		StopFaxResponseE rv = new StopFaxResponseE();
		rv.setStopFaxResponse(apiStatusResponse);
		return rv;
	}

	@Override
	public StopFaxResponse toWrapper(StopFaxResponseE apiResponse) {
		if (apiResponse == null) {
			return null;
		}
		FaxStatusResponseE apiResponseE =
			new FaxStatusResponseE();
		apiResponseE.setFaxStatusResponse(apiResponse.getStopFaxResponse());
		FaxStatusResponse response = delegate.toWrapper(apiResponseE);
		
		StopFaxResponse rv = new StopFaxResponse();
		rv.setFaxStatusTotals(response.getFaxStatusTotals());
		rv.setFaxResultsTotals(response.getFaxResultsTotals());
		rv.setFaxMessages(response.getFaxMessages());
		return rv;
	}

}

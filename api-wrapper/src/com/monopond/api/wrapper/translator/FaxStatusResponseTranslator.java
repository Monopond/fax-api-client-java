package com.monopond.api.wrapper.translator;

import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatus;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxMessages_type0;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusResponseE;
import com.monopond.api.wrapper.model.FaxMessageStatus;
import com.monopond.api.wrapper.model.FaxResultsTotals;
import com.monopond.api.wrapper.model.FaxStatusResponse;
import com.monopond.api.wrapper.model.FaxStatusTotals;

public class FaxStatusResponseTranslator implements
		Translator<FaxStatusResponse, ApiServiceStub.FaxStatusResponseE> {
	private Translator<FaxStatusTotals, ApiServiceStub.FaxStatusTotals>
		faxStatusTotalsTranslator = new FaxStatusTotalTranslator();
	private Translator<FaxResultsTotals, ApiServiceStub.FaxResultsTotals>
		faxResultsTotalsTranslator = new FaxResultTotalsTranslator();
	private Translator<List<FaxMessageStatus>, ApiFaxMessageStatus[]>
		faxMessageStatusListTranslator = new FaxMessageStatusListTranslator();
	
	@Override
	public FaxStatusResponseE toApi(FaxStatusResponse response) {
		if (response == null) {
			return null;
		}
		ApiServiceStub.SendFaxResponse apiResponse =
			new ApiServiceStub.SendFaxResponse();
		apiResponse.setFaxStatusTotals(faxStatusTotalsTranslator
			.toApi(response.getFaxStatusTotals()));
		apiResponse.setFaxResultsTotals(faxResultsTotalsTranslator
			.toApi(response.getFaxResultsTotals()));
		apiResponse.setFaxMessages(wrapArray(faxMessageStatusListTranslator
			.toApi(response.getFaxMessages())));
		
		FaxStatusResponseE rv = new FaxStatusResponseE();
		rv.setFaxStatusResponse(apiResponse);
		return rv;
	}
	
	private FaxMessages_type0 wrapArray(ApiFaxMessageStatus[] array) {
		FaxMessages_type0 apiFaxMessages = new FaxMessages_type0();
		apiFaxMessages.setFaxMessage(array);
		return apiFaxMessages;
	}
	
	@Override
	public FaxStatusResponse toWrapper(FaxStatusResponseE apiResponse) {
		if (apiResponse == null) {
			return null;
		}
		ApiServiceStub.FaxStatusResponse apiSendFaxResponse =
			apiResponse.getFaxStatusResponse();
		FaxStatusResponse response = new FaxStatusResponse();
		
		response.setFaxStatusTotals(faxStatusTotalsTranslator
			.toWrapper(apiSendFaxResponse.getFaxStatusTotals()));
		response.setFaxResultsTotals(faxResultsTotalsTranslator
			.toWrapper(apiSendFaxResponse.getFaxResultsTotals()));
		response.setFaxMessages(faxMessageStatusListTranslator
			.toWrapper(apiSendFaxResponse.getFaxMessages().getFaxMessage()));
		return response;
	}
}

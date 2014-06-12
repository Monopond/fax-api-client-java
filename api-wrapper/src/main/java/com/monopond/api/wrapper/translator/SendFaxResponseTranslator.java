package com.monopond.api.wrapper.translator;

import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatus;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxMessages_type0;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SendFaxResponseE;
import com.monopond.api.wrapper.model.FaxMessageStatus;
import com.monopond.api.wrapper.model.FaxResultsTotals;
import com.monopond.api.wrapper.model.FaxStatusTotals;
import com.monopond.api.wrapper.model.SendFaxResponse;

public class SendFaxResponseTranslator
		implements Translator<SendFaxResponse, SendFaxResponseE> {
	Translator<FaxStatusTotals, ApiServiceStub.FaxStatusTotals>
		faxStatusTotalsTranslator = new FaxStatusTotalTranslator();
	Translator<FaxResultsTotals, ApiServiceStub.FaxResultsTotals>
		faxResultsTotalsTranslator = new FaxResultTotalsTranslator();
	Translator<List<FaxMessageStatus>, ApiFaxMessageStatus[]>
		faxMessageStatusListTranslator = new FaxMessageStatusListTranslator();
	
	@Override
	public SendFaxResponseE toApi(SendFaxResponse response) {
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
		
		SendFaxResponseE rv = new SendFaxResponseE();
		rv.setSendFaxResponse(apiResponse);
		return rv;
	}

	private FaxMessages_type0 wrapArray(ApiFaxMessageStatus[] array) {
		FaxMessages_type0 apiFaxMessages = new FaxMessages_type0();
		apiFaxMessages.setFaxMessage(array);
		return apiFaxMessages;
	}

	@Override
	public SendFaxResponse toWrapper(SendFaxResponseE apiResponse) {
		if (apiResponse == null) {
			return null;
		}
		ApiServiceStub.SendFaxResponse apiSendFaxResponse =
			apiResponse.getSendFaxResponse();
		SendFaxResponse response = new SendFaxResponse();
		
		response.setFaxStatusTotals(faxStatusTotalsTranslator
			.toWrapper(apiSendFaxResponse.getFaxStatusTotals()));
		response.setFaxResultsTotals(faxResultsTotalsTranslator
			.toWrapper(apiSendFaxResponse.getFaxResultsTotals()));
		response.setFaxMessages(faxMessageStatusListTranslator
			.toWrapper(apiSendFaxResponse.getFaxMessages().getFaxMessage()));
		return response;
	}
}

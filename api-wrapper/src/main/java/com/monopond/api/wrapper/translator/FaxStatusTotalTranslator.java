package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.wrapper.model.FaxStatusTotals;

public class FaxStatusTotalTranslator
		implements Translator<FaxStatusTotals, ApiServiceStub.FaxStatusTotals> {

	@Override
	public ApiServiceStub.FaxStatusTotals toApi(FaxStatusTotals faxStatusTotals) {
		if (faxStatusTotals == null) {
			return null;
		}
		ApiServiceStub.FaxStatusTotals apiFaxStatusTotals =
			new ApiServiceStub.FaxStatusTotals();
		
		apiFaxStatusTotals.setPending(faxStatusTotals.getPending());
		apiFaxStatusTotals.setProcessing(faxStatusTotals.getProcessing());
		apiFaxStatusTotals.setQueued(faxStatusTotals.getQueued());
		apiFaxStatusTotals.setStarting(faxStatusTotals.getStarting());
		apiFaxStatusTotals.setSending(faxStatusTotals.getSending());
		apiFaxStatusTotals.setFinalizing(faxStatusTotals.getFinalizing());
		apiFaxStatusTotals.setDone(faxStatusTotals.getDone());
		return apiFaxStatusTotals;
	}

	@Override
	public FaxStatusTotals toWrapper(ApiServiceStub.FaxStatusTotals apiFaxStatusTotals) {
		if (apiFaxStatusTotals == null) {
			return null;
		}
		FaxStatusTotals faxStatusTotals = new FaxStatusTotals();
		
		faxStatusTotals.setPending(apiFaxStatusTotals.getPending());
		faxStatusTotals.setProcessing(apiFaxStatusTotals.getProcessing());
		faxStatusTotals.setQueued(apiFaxStatusTotals.getQueued());
		faxStatusTotals.setStarting(apiFaxStatusTotals.getStarting());
		faxStatusTotals.setSending(apiFaxStatusTotals.getSending());
		faxStatusTotals.setFinalizing(apiFaxStatusTotals.getFinalizing());
		faxStatusTotals.setDone(apiFaxStatusTotals.getDone());
		return faxStatusTotals;
	}

}

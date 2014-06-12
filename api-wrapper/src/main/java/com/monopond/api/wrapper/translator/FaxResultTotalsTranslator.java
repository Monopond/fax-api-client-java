package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.wrapper.model.FaxResultsTotals;

public class FaxResultTotalsTranslator
		implements Translator<FaxResultsTotals, ApiServiceStub.FaxResultsTotals> {

	@Override
	public ApiServiceStub.FaxResultsTotals toApi(FaxResultsTotals faxResultsTotals) {
		if (faxResultsTotals == null) {
			return null;
		}
		ApiServiceStub.FaxResultsTotals apiFaxResultTotals =
			new ApiServiceStub.FaxResultsTotals();
		
		apiFaxResultTotals.setSuccess(faxResultsTotals.getSuccess());
		apiFaxResultTotals.setBlocked(faxResultsTotals.getBlocked());
		apiFaxResultTotals.setFailed(faxResultsTotals.getFailed());
		apiFaxResultTotals.setTotalAttempts(faxResultsTotals.getTotalAttempt());
		apiFaxResultTotals.setTotalFaxDuration(faxResultsTotals.getTotalFaxDuration());
		apiFaxResultTotals.setTotalPages(faxResultsTotals.getTotalPages());
		return apiFaxResultTotals;
	}

	@Override
	public FaxResultsTotals toWrapper(ApiServiceStub.FaxResultsTotals apiFaxResultsTotals) {
		if (apiFaxResultsTotals == null) {
			return null;
		}
		FaxResultsTotals faxResultsTotals = new FaxResultsTotals();
		
		faxResultsTotals.setSuccess(apiFaxResultsTotals.getSuccess());
		faxResultsTotals.setBlocked(apiFaxResultsTotals.getBlocked());
		faxResultsTotals.setFailed(apiFaxResultsTotals.getFailed());
		faxResultsTotals.setTotalAttempt(apiFaxResultsTotals.getTotalAttempts());
		faxResultsTotals.setTotalFaxDuration(apiFaxResultsTotals.getTotalFaxDuration());
		faxResultsTotals.setTotalPages(apiFaxResultsTotals.getTotalPages());
		return faxResultsTotals;
	}

}

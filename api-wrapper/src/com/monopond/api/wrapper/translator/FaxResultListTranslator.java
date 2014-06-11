package com.monopond.api.wrapper.translator;

import java.util.ArrayList;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatusResults;
import com.monopond.api.wrapper.model.FaxStatusResult;

public class FaxResultListTranslator implements
		Translator<List<FaxStatusResult>, ApiFaxMessageStatusResults[]> {
	private Translator<FaxStatusResult, ApiFaxMessageStatusResults>
		faxStatusResultTranslator = new FaxStatusResultTranslator();
	@Override
	public ApiFaxMessageStatusResults[] toApi(List<FaxStatusResult> faxStatusResults) {
		if (faxStatusResults == null) {
			return null;
		}
		ApiFaxMessageStatusResults[] apiFaxResults =
			new ApiFaxMessageStatusResults[faxStatusResults.size()];
		
		for (int i = 0; i < faxStatusResults.size(); i++) {
			apiFaxResults[i] = faxStatusResultTranslator
				.toApi(faxStatusResults.get(i));
		}
		return apiFaxResults;
	}

	@Override
	public List<FaxStatusResult> toWrapper(ApiFaxMessageStatusResults[] apiFaxStatusResults) {
		if (apiFaxStatusResults == null) {
			return null;
		}
		List<FaxStatusResult> faxStatusResults = new ArrayList<>();
		
		for (ApiFaxMessageStatusResults apiFaxStatusResult : apiFaxStatusResults) {
			faxStatusResults.add(faxStatusResultTranslator
				.toWrapper(apiFaxStatusResult));
		}
		return faxStatusResults;
	}

}

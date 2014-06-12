package com.monopond.api.wrapper.translator;

import java.util.ArrayList;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageStatus;
import com.monopond.api.wrapper.model.FaxMessageStatus;

public class FaxMessageStatusListTranslator implements
		Translator<List<FaxMessageStatus>, ApiFaxMessageStatus[]> {
	private Translator<FaxMessageStatus, ApiFaxMessageStatus>
		faxMessageStatusTranslator = new FaxMessageStatusTranslator();

	@Override
	public ApiFaxMessageStatus[] toApi(List<FaxMessageStatus> faxMessagesStatuses) {
		if (faxMessagesStatuses == null) {
			return null;
		}
		ApiFaxMessageStatus[] apiFaxMessagesStatuses =
			new ApiFaxMessageStatus[faxMessagesStatuses.size()];
		
		for (int i = 0; i < faxMessagesStatuses.size(); i++) {
			apiFaxMessagesStatuses[i] = faxMessageStatusTranslator
				.toApi(faxMessagesStatuses.get(i));
		}
		return apiFaxMessagesStatuses;
	}

	@Override
	public List<FaxMessageStatus> toWrapper(ApiFaxMessageStatus[] apiFaxMessagesStatuses) {
		if (apiFaxMessagesStatuses == null) {
			return null;
		}
		List<FaxMessageStatus> faxMessageStatuses = new ArrayList<>();
		
		for (ApiFaxMessageStatus apiFaxMessageStatus : apiFaxMessagesStatuses) {
			faxMessageStatuses.add(faxMessageStatusTranslator
				.toWrapper(apiFaxMessageStatus));
		}
		return faxMessageStatuses;
	}

}

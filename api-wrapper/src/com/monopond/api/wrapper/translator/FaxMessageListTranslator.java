package com.monopond.api.wrapper.translator;

import java.util.ArrayList;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessage;
import com.monopond.api.wrapper.model.FaxMessage;

public class FaxMessageListTranslator implements
		Translator<List<FaxMessage>, ApiFaxMessage[]> {
	private Translator<FaxMessage, ApiFaxMessage>
		faxMessageTranslator = new FaxMessageTranslator();
	
	@Override
	public ApiFaxMessage[] toApi(List<FaxMessage> faxMessages) {
		if (faxMessages == null) {
			return null;
		}
		ApiFaxMessage[] apiFaxMessages =
			new ApiFaxMessage[faxMessages.size()];
				
		for (int i = 0; i < faxMessages.size(); i++) {
			apiFaxMessages[i] =
				faxMessageTranslator.toApi(faxMessages.get(i));
		}
		return apiFaxMessages;
	}

	@Override
	public List<FaxMessage> toWrapper(ApiFaxMessage[] apiFaxMessages) {
		if (apiFaxMessages == null) {
			return null;
		}
		List<FaxMessage> faxMessages = new ArrayList<>();
		for (ApiFaxMessage apiFaxMessage : apiFaxMessages) {
			faxMessages.add(faxMessageTranslator.toWrapper(apiFaxMessage));
		}	
		
		return faxMessages;
	}

}

package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxErrorAdapter;
import com.monopond.api.wrapper.model.FaxError;

public class FaxErrorTranslator implements
		Translator<FaxError, FaxErrorAdapter> {

	@Override
	public FaxErrorAdapter toApi(FaxError faxError) {
		if (faxError == null) {
			return null;
		}
		FaxErrorAdapter apiFaxError = new FaxErrorAdapter();
		apiFaxError.setCode(faxError.toString());
		apiFaxError.setName(faxError.getName());
		return apiFaxError;
	}

	@Override
	public FaxError toWrapper(FaxErrorAdapter apiFaxError) {
		if (apiFaxError == null) {
			return null;
		}
		try {
			return FaxError.valueOf(apiFaxError.getCode());
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new TranslationException(FaxErrorAdapter.class, FaxError.class, e);
		}
	}

}

package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.wrapper.model.FaxResult;

public class FaxResultTranslator
		implements Translator<FaxResult, ApiServiceStub.FaxResult> {

	@Override
	public ApiServiceStub.FaxResult toApi(FaxResult faxResult) {
		switch (faxResult) {
		case SUCCESS:
			return ApiServiceStub.FaxResult.success;
		case BLOCKED:
			return ApiServiceStub.FaxResult.blocked;
		case FAILED:
			return ApiServiceStub.FaxResult.failed;
		default:
			throw new TranslationException(FaxResult.class, ApiServiceStub.FaxResult.class,
					"Invalid fax result: " + faxResult);	
		}
	}

	@Override
	public FaxResult toWrapper(ApiServiceStub.FaxResult apiFaxResult) {
		if (apiFaxResult == ApiServiceStub.FaxResult.success) {
			return FaxResult.SUCCESS;
		} else if (apiFaxResult == ApiServiceStub.FaxResult.blocked) {
			return FaxResult.BLOCKED;
		} else if (apiFaxResult == ApiServiceStub.FaxResult.failed) {
			return FaxResult.FAILED;
		} else {
			throw new TranslationException(ApiServiceStub.FaxResult.class, FaxResult.class,
					"Invalid fax result: " + apiFaxResult);
		}
	}

}

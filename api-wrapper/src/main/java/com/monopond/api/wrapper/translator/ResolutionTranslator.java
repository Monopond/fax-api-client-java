package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxResolution;
import com.monopond.api.wrapper.model.Resolution;

public class ResolutionTranslator implements
		Translator<Resolution, FaxResolution> {

	@Override
	public FaxResolution toApi(Resolution resolution) {
		switch (resolution) {
		case NORMAL:
			return FaxResolution.normal;
		case FINE:
			return FaxResolution.fine;
		default:
			throw new TranslationException(Resolution.class, FaxResolution.class,
				"Unknown Resolution: " + resolution);
		}
	}

	@Override
	public Resolution toWrapper(FaxResolution apiResolution) {
		if (apiResolution == FaxResolution.normal) {
			return Resolution.NORMAL;
		} else if (apiResolution == FaxResolution.fine) {
			return Resolution.FINE;
		} else {
			throw new TranslationException(FaxResolution.class, Resolution.class,
					"Unknown Resolution: " + apiResolution);
		}
	}

}

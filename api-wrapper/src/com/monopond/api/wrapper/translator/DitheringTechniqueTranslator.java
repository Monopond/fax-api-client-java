package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.wrapper.model.FaxDitheringTechnique;

public class DitheringTechniqueTranslator
		implements Translator<FaxDitheringTechnique, ApiServiceStub.FaxDitheringTechnique> {

	@Override
	public ApiServiceStub.FaxDitheringTechnique toApi(FaxDitheringTechnique dithering) {
		switch (dithering) {
		case NONE:
			return ApiServiceStub.FaxDitheringTechnique.none;
		case NORMAL:
			return ApiServiceStub.FaxDitheringTechnique.normal;
		case TURBO:
			return ApiServiceStub.FaxDitheringTechnique.turbo;
		case DARKEN:
			return ApiServiceStub.FaxDitheringTechnique.darken;
		case DARKEN_MORE:
			return ApiServiceStub.FaxDitheringTechnique.darken_more;
		case DARKEN_EXTRA:
			return ApiServiceStub.FaxDitheringTechnique.darken_extra;
		case LIGHTEN:
			return ApiServiceStub.FaxDitheringTechnique.lighten;
		case LIGHTEN_MORE:
			return ApiServiceStub.FaxDitheringTechnique.lighten_more;
		case CROSSHATCH:
			return ApiServiceStub.FaxDitheringTechnique.crosshatch;
		case DETAILED:
			return ApiServiceStub.FaxDitheringTechnique.DETAILED;
		default:
			throw new TranslationException(FaxDitheringTechnique.class, ApiServiceStub.FaxDitheringTechnique.class,
				"Invalid dithering technique: " + dithering);	
		}
	}

	@Override
	public FaxDitheringTechnique toWrapper(ApiServiceStub.FaxDitheringTechnique apiDithering) {
		if (apiDithering == ApiServiceStub.FaxDitheringTechnique.none) {
			return FaxDitheringTechnique.NONE;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.normal) {
			return FaxDitheringTechnique.NORMAL;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.turbo) {
			return FaxDitheringTechnique.TURBO;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.darken) {
			return FaxDitheringTechnique.DARKEN;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.darken_more) {
			return FaxDitheringTechnique.DARKEN_MORE;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.darken_extra) {
			return FaxDitheringTechnique.DARKEN_EXTRA;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.lighten) {
			return FaxDitheringTechnique.LIGHTEN;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.lighten_more) {
			return FaxDitheringTechnique.LIGHTEN_MORE;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.crosshatch) {
			return FaxDitheringTechnique.CROSSHATCH;
		} else if (apiDithering == ApiServiceStub.FaxDitheringTechnique.DETAILED) {
			return FaxDitheringTechnique.DETAILED;
		} else {
			throw new TranslationException(ApiServiceStub.FaxDitheringTechnique.class, FaxDitheringTechnique.class,
					"Invalid dithering technique: " + apiDithering);
		}
	}

}

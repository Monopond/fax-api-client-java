package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusLevel;
import com.monopond.api.wrapper.model.VerbosityLevel;

public class VerbosityLevelTranslator implements
		Translator<VerbosityLevel, FaxStatusLevel> {

	@Override
	public FaxStatusLevel toApi(VerbosityLevel verbosity) {
		switch (verbosity) {
		case BRIEF:
			return FaxStatusLevel.brief;
		case SEND:
			return FaxStatusLevel.send;
		case DETAILS:
			return FaxStatusLevel.details;
		case RESULTS:
			return FaxStatusLevel.results;
		case ALL:
			return FaxStatusLevel.all;
		default:
			throw new TranslationException(VerbosityLevel.class, FaxStatusLevel.class,
				"Invalid verbosity level: " + verbosity);
		}
	}

	@Override
	public VerbosityLevel toWrapper(FaxStatusLevel apiVerbosity) {
		String verbosity = apiVerbosity.getValue().toUpperCase();
		return VerbosityLevel.valueOf(verbosity);
	}

}

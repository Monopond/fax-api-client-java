package com.monopond.api.wrapper.model;

public class FaxStatusRequest extends Request {
	private VerbosityLevel verbosity;
	
	public VerbosityLevel getVerbosity() {
		return verbosity;
	}
	
	public void setVerbosity(VerbosityLevel verbosity) {
		this.verbosity = verbosity;
	}
}

package com.monopond.api.wrapper;

public class FaxApiException extends Exception {

	private static final long serialVersionUID = 6966509980901635889L;

	public FaxApiException(Throwable cause) {
		super(cause);
	}

	public FaxApiException(String message, Throwable cause) {
		super(message, cause);
	}

}

package com.monopond.api.wrapper.translator;

public class TranslationException extends RuntimeException {

	private static final long serialVersionUID = -5825874592694476418L;

	public TranslationException(Class<?> from, Class<?> to, String message) {
		super(String.format("Translation failed from %s to %s. %s",
				from.getName(), to.getName(), message));
	}
	
	public TranslationException(Class<?> from, Class<?> to, Throwable cause) {
		super(String.format("Translation failed from %s to %s.",
				from.getName(), to.getName()), cause);
	}
	
}

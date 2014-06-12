package com.monopond.api.wrapper.translator;

public interface Translator<WrapperClass, ApiClass> {
	
	ApiClass toApi(WrapperClass object);
	
	WrapperClass toWrapper(ApiClass apiObject);
}

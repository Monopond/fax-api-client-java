package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.PhoneNumber;

public class PhoneNumberTranslator implements Translator<String, PhoneNumber> {

	@Override
	public PhoneNumber toApi(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return null;
		}
 		PhoneNumber apiPhoneNumber = new PhoneNumber();
		apiPhoneNumber.setPhoneNumber(phoneNumber);
		return apiPhoneNumber;
	}

	@Override
	public String toWrapper(PhoneNumber apiPhoneNumber) {
		return apiPhoneNumber == null? "": apiPhoneNumber.getPhoneNumber();
	}

}

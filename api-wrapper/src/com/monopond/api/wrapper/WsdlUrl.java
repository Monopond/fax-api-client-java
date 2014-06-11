package com.monopond.api.wrapper;

public enum WsdlUrl {
	TEST ("https://test.api.monopond.com/fax/soap/v2.1"),
	LOCAL("http://localhost:8000/fax/soap/v2.1"),
	PROD ("https://test.api.monopond.com/fax/soap/v2.1");
	
	private final String url;
	
	private WsdlUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}

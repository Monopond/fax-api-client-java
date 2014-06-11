package com.monopond.api.wrapper.model;

public enum FaxError {
	DOCUMENT_EXCEEDS_PAGE_LIMIT	("Document exceeds page limit"),
	DOCUMENT_UNSUPPORTED		("Unsupported document type"),
	DOCUMENT_FAILED_CONVERSION	("Document failed conversion"),
	
	FUNDS_INSUFFICIENT			("Insufficient funds"),
	FUNDS_FAILED				("Failed to transfer funds"),
	
	BLOCK_ACCOUNT				("Number cannot be sent from this account"),
	BLOCK_GLOBAL				("Number found in the Global blocklist"),
	BLOCK_SMART					("Number found in the Smart blocklist"),
	BLOCK_DNCR					("Number found in the DNCR blocklist"),
	BLOCK_CUSTOM				("Number found in a user specified blocklist"),
	
	FAX_NEGOTIATION_FAILED		("Negotiation failed"),
	FAX_EARLY_HANGUP			("Early hang-up on call"),
	FAX_INCOMAPATIBLE_MACHINE	("Incompatible fax machine"),
	FAX_BUSY					("Phone number busy"),
	FAX_NUMBER_UNOBTAINABLE		("Number unobtainable"),
	FAX_SENDING_FAILED			("Sending fax failed"),
	FAX_CANCELLED				("Cancelled"),
	FAX_NO_ANSWER				("No answer"),
	FAX_UNKNOWN					("Unknown fax error");
	
	private String name;
	private FaxError(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

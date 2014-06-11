package com.monopond.api.wrapper.model;

import java.util.List;


public class FaxMessage extends FaxOptions {
	private String messageRef;
	private String sendTo;
	private String cli;
	
	public FaxMessage(String messageRef, String sendTo, List<FaxDocument> documents) {
		super(documents);
		this.setMessageRef(messageRef);
		this.setSendTo(sendTo);
	}
	
	public FaxMessage(String messageRef, String sendTo) {
		this(messageRef, sendTo, null);
	}

	public String getMessageRef() {
		return messageRef;
	}

	public void setMessageRef(String messageRef) {
		this.messageRef = messageRef;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getCli() {
		return cli;
	}

	public void setCli(String cli) {
		this.cli = cli;
	}
}

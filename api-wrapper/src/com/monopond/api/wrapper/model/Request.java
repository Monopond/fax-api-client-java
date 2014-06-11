package com.monopond.api.wrapper.model;

public abstract class Request {
	private String broadcastRef;
	private String sendRef;
	private String messageRef;
	
	public Request() { }
	
	public Request(Request request) {
		this.broadcastRef = request.broadcastRef;
		this.sendRef = request.sendRef;
		this.messageRef = request.messageRef;
	}

	public String getBroadcastRef() {
		return broadcastRef;
	}

	public void setBroadcastRef(String broadcastRef) {
		this.broadcastRef = broadcastRef;
	}

	public String getSendRef() {
		return sendRef;
	}

	public void setSendRef(String sendRef) {
		this.sendRef = sendRef;
	}

	public String getMessageRef() {
		return messageRef;
	}

	public void setMessageRef(String messageRef) {
		this.messageRef = messageRef;
	}
}

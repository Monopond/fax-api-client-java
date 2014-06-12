package com.monopond.api.wrapper.model;

import java.util.ArrayList;
import java.util.List;

public class FaxMessageStatus {
	private String messageRef;
	private String sendRef;
	private String broadcastRef;
	private String sendTo;
	private FaxStatus status;
	private FaxDetails details;
	private List<FaxStatusResult> results;
	
	public FaxMessageStatus() {
		results = new ArrayList<>();
	}
	
	public String getMessageRef() {
		return messageRef;
	}
	
	public void setMessageRef(String messageRef) {
		this.messageRef = messageRef;
	}
	
	public String getSendRef() {
		return sendRef;
	}
	
	public void setSendRef(String sendRef) {
		this.sendRef = sendRef;
	}
	
	public String getBroadcastRef() {
		return broadcastRef;
	}
	
	public void setBroadcastRef(String broadcastRef) {
		this.broadcastRef = broadcastRef;
	}
	
	public String getSendTo() {
		return sendTo;
	}
	
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	
	public FaxStatus getStatus() {
		return status;
	}
	
	public void setStatus(FaxStatus status) {
		this.status = status;
	}
	
	public FaxDetails getDetails() {
		return details;
	}
	
	public void setDetails(FaxDetails details) {
		this.details = details;
	}
	
	public List<FaxStatusResult> getResults() {
		return new ArrayList<>(results);
	}
	
	public void setResults(List<FaxStatusResult> results) {
		this.results = results;
	}
}
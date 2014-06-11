package com.monopond.api.wrapper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class FaxOptions {
	private String broadcastRef;
	private String sendRef;
	private String sendFrom;
	private List<FaxDocument> documents;
	private Resolution resolution;
	private Date scheduledStartTime;
	private Blocklists blocklists;
	private int retries;
	private String headerFormat;
	private Date mustBeSentBeforeDate;
	private int maxFaxPages;
	private int busyRetries;

	FaxOptions(List<FaxDocument> documents) {
		this.documents = documents;
		
		resolution = Resolution.NORMAL;
		scheduledStartTime = new Date();
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

	public String getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public List<FaxDocument> getDocuments() {
		return documents == null? null: new ArrayList<>(documents);
	}

	public void setDocuments(List<FaxDocument> documents) {
		this.documents = documents;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public Date getScheduledStartTime() {
		return scheduledStartTime;
	}

	public void setScheduledStartTime(Date scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}

	public Blocklists getBlocklists() {
		return blocklists;
	}

	public void setBlocklists(Blocklists blocklists) {
		this.blocklists = blocklists;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public String getHeaderFormat() {
		return headerFormat;
	}

	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}

	public Date getMustBeSentBeforeDate() {
		return mustBeSentBeforeDate;
	}

	public void setMustBeSentBeforeDate(Date mustBeSentBeforeDate) {
		this.mustBeSentBeforeDate = mustBeSentBeforeDate;
	}

	public int getMaxFaxPages() {
		return maxFaxPages;
	}

	public void setMaxFaxPages(int maxFaxPages) {
		this.maxFaxPages = maxFaxPages;
	}

	public int getBusyRetries() {
		return busyRetries;
	}

	public void setBusyRetries(int busyRetries) {
		this.busyRetries = busyRetries;
	}
	
}

package com.monopond.api.wrapper.model;

public class FaxDetails {
	private String sendFrom;
	private Resolution resolution;
	private int retries;
	private int busyRetries;
	private String headerFormat;
	
	public String getSendFrom() {
		return sendFrom;
	}
	
	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}
	
	public Resolution getResolution() {
		return resolution;
	}
	
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	
	public int getRetries() {
		return retries;
	}
	
	public void setRetries(int retries) {
		this.retries = retries;
	}
	
	public int getBusyRetries() {
		return busyRetries;
	}
	
	public void setBusyRetries(int busyRetries) {
		this.busyRetries = busyRetries;
	}
	
	public String getHeaderFormat() {
		return headerFormat;
	}
	
	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}
}

package com.monopond.api.wrapper.model;

import java.math.BigDecimal;
import java.util.Date;

public class FaxStatusResult {
	private int attempt;
	private FaxResult result;
	private FaxError error;
	private BigDecimal cost;
	private int pages;
	private Date scheduledStartTime;
	private Date dateCallStarted;
	private Date dateCallEnded;
	
	public int getAttempt() {
		return attempt;
	}
	
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	
	public FaxResult getResult() {
		return result;
	}
	
	public void setResult(FaxResult result) {
		this.result = result;
	}
	
	public FaxError getError() {
		return error;
	}
	
	public void setError(FaxError error) {
		this.error = error;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public Date getScheduledStartTime() {
		return scheduledStartTime;
	}
	
	public void setScheduledStartTime(Date scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}
	
	public Date getDateCallStarted() {
		return dateCallStarted;
	}
	
	public void setDateCallStarted(Date dateCallStarted) {
		this.dateCallStarted = dateCallStarted;
	}
	
	public Date getDateCallEnded() {
		return dateCallEnded;
	}
	
	public void setDateCallEnded(Date dateCallEnded) {
		this.dateCallEnded = dateCallEnded;
	}
	
}

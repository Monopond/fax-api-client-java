package com.monopond.api.wrapper.model;

public class FaxResultsTotals {
	private long success;
	private long blocked;
	private long failed;
	private long totalAttempt;
	private long totalFaxDuration;
	private long totalPages;
	public long getSuccess() {
		return success;
	}
	public void setSuccess(long success) {
		this.success = success;
	}
	public long getBlocked() {
		return blocked;
	}
	public void setBlocked(long blocked) {
		this.blocked = blocked;
	}
	public long getFailed() {
		return failed;
	}
	public void setFailed(long failed) {
		this.failed = failed;
	}
	public long getTotalAttempt() {
		return totalAttempt;
	}
	public void setTotalAttempt(long totalAttempt) {
		this.totalAttempt = totalAttempt;
	}
	public long getTotalFaxDuration() {
		return totalFaxDuration;
	}
	public void setTotalFaxDuration(long totalFaxDuration) {
		this.totalFaxDuration = totalFaxDuration;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
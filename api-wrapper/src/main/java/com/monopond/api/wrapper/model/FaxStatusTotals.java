package com.monopond.api.wrapper.model;

public class FaxStatusTotals {
	private long pending;
	private long processing;
	private long queued;
	private long starting;
	private long sending;
	private long pausing;
	private long paused;
	private long resuming;
	private long stopping;
	private long finalizing;
	private long done;
	
	public long getPending() {
		return pending;
	}
	public void setPending(long pending) {
		this.pending = pending;
	}
	public long getProcessing() {
		return processing;
	}
	public void setProcessing(long processing) {
		this.processing = processing;
	}
	public long getQueued() {
		return queued;
	}
	public void setQueued(long queued) {
		this.queued = queued;
	}
	public long getStarting() {
		return starting;
	}
	public void setStarting(long starting) {
		this.starting = starting;
	}
	public long getSending() {
		return sending;
	}
	public void setSending(long sending) {
		this.sending = sending;
	}
	public long getPausing() {
		return pausing;
	}
	public void setPausing(long pausing) {
		this.pausing = pausing;
	}
	public long getPaused() {
		return paused;
	}
	public void setPaused(long paused) {
		this.paused = paused;
	}
	public long getResuming() {
		return resuming;
	}
	public void setResuming(long resuming) {
		this.resuming = resuming;
	}
	public long getStopping() {
		return stopping;
	}
	public void setStopping(long stopping) {
		this.stopping = stopping;
	}
	public long getFinalizing() {
		return finalizing;
	}
	public void setFinalizing(long finalizing) {
		this.finalizing = finalizing;
	}
	public long getDone() {
		return done;
	}
	public void setDone(long done) {
		this.done = done;
	}
}
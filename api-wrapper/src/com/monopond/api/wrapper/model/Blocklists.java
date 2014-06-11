package com.monopond.api.wrapper.model;


public class Blocklists {
	private boolean dncr;
	private boolean fps;
	private boolean smartblock;
	
	public boolean isDncr() {
		return dncr;
	}
	public void setDncr(boolean dncr) {
		this.dncr = dncr;
	}
	public boolean isFps() {
		return fps;
	}
	public void setFps(boolean fps) {
		this.fps = fps;
	}
	public boolean isSmartblock() {
		return smartblock;
	}
	public void setSmartblock(boolean smartblock) {
		this.smartblock = smartblock;
	}
	
	
}

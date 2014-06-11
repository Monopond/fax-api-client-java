package com.monopond.api.wrapper.model;


public class DocumentPreviewResponse {
	private String tiffPreview;
	private int numberOfPages;
	
	public String getTiffPreview() {
		return tiffPreview;
	}
	
	public void setTiffPreview(String tiffPreview) {
		this.tiffPreview = tiffPreview;
	}
	
	public int getNumberOfPages() {
		return numberOfPages;
	}
	
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
}

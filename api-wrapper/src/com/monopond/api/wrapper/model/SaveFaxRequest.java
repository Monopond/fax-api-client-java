package com.monopond.api.wrapper.model;

public class SaveFaxRequest {
	private String documentRef;
	private String fileName;
	private String fileData;
	
	public SaveFaxRequest(String documentRef, String fileName, String fileData) {
		this.documentRef = documentRef;
		this.fileName = fileName;
		this.fileData = fileData;
	}

	public String getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
}

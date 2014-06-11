package com.monopond.api.wrapper.model;

public class DeleteFaxRequest {
	private String documentRef;
	
	public DeleteFaxRequest(String documentRef) {
		this.setDocumentRef(documentRef);
	}

	public String getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}
}

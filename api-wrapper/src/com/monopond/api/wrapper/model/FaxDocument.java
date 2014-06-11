package com.monopond.api.wrapper.model;

import java.util.ArrayList;
import java.util.List;

public class FaxDocument {
	private String documentRef;
	private String fileName;
	private String fileData;
	private int order;
	private FaxDitheringTechnique ditheringTechnique;
	private List<DocMerge> docMergeData;
	private List<StampMerge> stampMergeData;
	
	public FaxDocument(String documentRef) {
		this(documentRef, null, null);
	}
	
	public FaxDocument(String fileName, String fileData) {
		this(null, fileName, fileData);
	}
	
	private FaxDocument(String documentRef, String fileName, String fileData) {
		this.documentRef = documentRef;
		this.fileName = fileName;
		this.fileData = fileData;
		
		order = 0;
		ditheringTechnique = FaxDitheringTechnique.NONE;
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


	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
	}


	public FaxDitheringTechnique getDitheringTechnique() {
		return ditheringTechnique;
	}


	public void setDitheringTechnique(FaxDitheringTechnique ditheringTechnique) {
		this.ditheringTechnique = ditheringTechnique;
	}


	public List<DocMerge> getDocMergeData() {
		return docMergeData == null? null: new ArrayList<>(docMergeData);
	}


	public void setDocMergeData(List<DocMerge> docMergeData) {
		this.docMergeData = docMergeData;
	}


	public List<StampMerge> getStampMergeData() {
		return stampMergeData == null? null: new ArrayList<>(stampMergeData);
	}


	public void setStampMergeData(List<StampMerge> stampMergeData) {
		this.stampMergeData = stampMergeData;
	}
}

package com.monopond.api.wrapper.model;

import java.util.ArrayList;
import java.util.List;

public class DocumentPreviewRequest {
	private String documentRef;
	private Resolution resolution;
	private FaxDitheringTechnique ditheringTechnique;
	private List<DocMerge> docMergeData;
	private List<StampMerge> stampMergeData;

	
	public DocumentPreviewRequest(String documentRef) {
		this.documentRef = documentRef;
		
		resolution = Resolution.NORMAL;
		ditheringTechnique = FaxDitheringTechnique.NONE;
	}
	
	public String getDocumentRef() {
		return documentRef;
	}
	
	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}
	
	public Resolution getResolution() {
		return resolution;
	}
	
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
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

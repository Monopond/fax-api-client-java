package com.monopond.api.wrapper.translator;

import java.util.ArrayList;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocument;
import com.monopond.api.wrapper.model.FaxDocument;

public class DocumentListTranslator implements
		Translator<List<FaxDocument>, ApiFaxDocument[]> {
	private Translator<FaxDocument, ApiServiceStub.ApiFaxDocument>
		faxDocumentTranslator = new FaxDocumentTranslator();

	public ApiFaxDocument[] toApi(List<FaxDocument> faxDocuments) {
		if (faxDocuments == null) {
			return null;
		}
		ApiServiceStub.ApiFaxDocument[] apiFaxDocuments =
			new ApiServiceStub.ApiFaxDocument[faxDocuments.size()];
				
		for (int i = 0; i < faxDocuments.size(); i++) {
			apiFaxDocuments[i] =
				faxDocumentTranslator.toApi(faxDocuments.get(i));
		}
		
		return apiFaxDocuments;
	}
	
	public List<FaxDocument> toWrapper(ApiFaxDocument[] apiFaxDocuments) {
		if (apiFaxDocuments == null) {
			return null;
		}
		List<FaxDocument> faxDocuments = new ArrayList<>();
		
		for (ApiFaxDocument apiFaxDocument : apiFaxDocuments) {
			faxDocuments.add(faxDocumentTranslator.toWrapper(apiFaxDocument));
		}
		return faxDocuments;
	}

}

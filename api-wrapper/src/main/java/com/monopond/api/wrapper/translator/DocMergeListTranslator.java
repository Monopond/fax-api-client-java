package com.monopond.api.wrapper.translator;

import java.util.ArrayList;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentDocMergeField;
import com.monopond.api.wrapper.model.DocMerge;

public class DocMergeListTranslator implements
		Translator<List<DocMerge>, ApiFaxDocumentDocMergeField[]> {
			
	private Translator<DocMerge, ApiFaxDocumentDocMergeField>
		docMergeTranslator = new DocMergeTranslator();
	
	@Override
	public ApiFaxDocumentDocMergeField[] toApi(List<DocMerge> docMergeFields) {
		if (docMergeFields == null) {
			return null;
		}
		ApiFaxDocumentDocMergeField[] apiDocMerges =
			new ApiServiceStub.ApiFaxDocumentDocMergeField[docMergeFields.size()];
		
		for (int i = 0 ; i < docMergeFields.size(); i++) {
			apiDocMerges[i] =
				docMergeTranslator.toApi(docMergeFields.get(i));
		}
		return apiDocMerges;
	}

	@Override
	public List<DocMerge> toWrapper(ApiFaxDocumentDocMergeField[] apiDocMergeFields) {
		if (apiDocMergeFields == null) {
			return null;
		}
		List<DocMerge> docMergeFields = new ArrayList<>();
		
		for (ApiFaxDocumentDocMergeField apiDocMerge : apiDocMergeFields) {
			docMergeFields.add(docMergeTranslator.toWrapper(apiDocMerge));
		}
		
		return docMergeFields;
	}

}

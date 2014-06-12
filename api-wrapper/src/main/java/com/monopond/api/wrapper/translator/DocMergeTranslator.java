package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentDocMergeField;
import com.monopond.api.wrapper.model.DocMerge;

public class DocMergeTranslator implements
		Translator<DocMerge, ApiFaxDocumentDocMergeField> {

	@Override
	public ApiFaxDocumentDocMergeField toApi(DocMerge docMerge) {
		if (docMerge == null) {
			return null;
		}
		ApiFaxDocumentDocMergeField apiDocMergeField =
			new ApiFaxDocumentDocMergeField();
		apiDocMergeField.setKey(docMerge.getKey());
		apiDocMergeField.setValue(docMerge.getValue());
		
		return apiDocMergeField;
	}

	@Override
	public DocMerge toWrapper(ApiFaxDocumentDocMergeField apiDocMerge) {
		if (apiDocMerge == null) {
			return null;
		}
		DocMerge docMerge = new DocMerge();
		docMerge.setKey(apiDocMerge.getKey());
		docMerge.setValue(apiDocMerge.getValue());
		
		return docMerge;
	}

}

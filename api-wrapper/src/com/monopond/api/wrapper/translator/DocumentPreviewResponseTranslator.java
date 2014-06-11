package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxDocumentPreviewResponse;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxDocumentPreviewResponseE;
import com.monopond.api.wrapper.model.DocumentPreviewResponse;

public class DocumentPreviewResponseTranslator implements
		Translator<DocumentPreviewResponse, FaxDocumentPreviewResponseE> {

	@Override
	public FaxDocumentPreviewResponseE toApi(DocumentPreviewResponse response) {
		if (response == null) {
			return null;
		}
		FaxDocumentPreviewResponse apiResponse = new FaxDocumentPreviewResponse();
		apiResponse.setTiffPreview(response.getTiffPreview());
		apiResponse.setNumberOfPages(response.getNumberOfPages());
		
		FaxDocumentPreviewResponseE rv = new FaxDocumentPreviewResponseE();
		rv.setFaxDocumentPreviewResponse(apiResponse);
		return rv;
	}

	@Override
	public DocumentPreviewResponse toWrapper(FaxDocumentPreviewResponseE apiResponse) {
		if (apiResponse == null) {
			return null;
		}
		DocumentPreviewResponse response = new DocumentPreviewResponse();
		FaxDocumentPreviewResponse apiDocPrevResponse =
			apiResponse.getFaxDocumentPreviewResponse();
		response.setTiffPreview(apiDocPrevResponse.getTiffPreview());
		response.setNumberOfPages(apiDocPrevResponse.getNumberOfPages());
		return response;
	}

}

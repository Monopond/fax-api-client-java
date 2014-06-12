package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.DeleteFaxDocumentRequest;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.DeleteFaxDocumentRequestE;
import com.monopond.api.wrapper.model.DeleteFaxRequest;

public class DeleteFaxRequestTranslator implements
		Translator<DeleteFaxRequest, DeleteFaxDocumentRequestE> {

	@Override
	public DeleteFaxDocumentRequestE toApi(DeleteFaxRequest request) {
		if (request == null) {
			return null;
		}
		DeleteFaxDocumentRequest apiRequest = new DeleteFaxDocumentRequest();
		apiRequest.setDocumentRef(request.getDocumentRef());
		
		DeleteFaxDocumentRequestE rv = new DeleteFaxDocumentRequestE();
		rv.setDeleteFaxDocumentRequest(apiRequest);
		return rv;
	}

	@Override
	public DeleteFaxRequest toWrapper(DeleteFaxDocumentRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		DeleteFaxDocumentRequest apiDeleteRequest =
			apiRequest.getDeleteFaxDocumentRequest();
		String documentRef = apiDeleteRequest.getDocumentRef();
		
		return new DeleteFaxRequest(documentRef);
	}

}

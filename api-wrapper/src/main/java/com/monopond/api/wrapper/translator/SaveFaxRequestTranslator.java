package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SaveFaxDocumentRequest;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SaveFaxDocumentRequestE;
import com.monopond.api.wrapper.model.SaveFaxRequest;

public class SaveFaxRequestTranslator implements
		Translator<SaveFaxRequest, SaveFaxDocumentRequestE> {

	@Override
	public SaveFaxDocumentRequestE toApi(SaveFaxRequest request) {
		if (request == null) {
			return null;
		}
		SaveFaxDocumentRequest apiRequest = new SaveFaxDocumentRequest();
		apiRequest.setDocumentRef(request.getDocumentRef());
		apiRequest.setFileName(request.getFileName());
		apiRequest.setFileData(request.getFileData());
		
		SaveFaxDocumentRequestE rv = new SaveFaxDocumentRequestE();
		rv.setSaveFaxDocumentRequest(apiRequest);
		return rv;
	}

	@Override
	public SaveFaxRequest toWrapper(SaveFaxDocumentRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		SaveFaxDocumentRequest apiSaveRequest =
			apiRequest.getSaveFaxDocumentRequest();
		String documentRef = apiSaveRequest.getDocumentRef();
		String fileName = apiSaveRequest.getFileName();
		String fileData = apiSaveRequest.getFileData();
		
		return new SaveFaxRequest(documentRef, fileName, fileData);
	}

}

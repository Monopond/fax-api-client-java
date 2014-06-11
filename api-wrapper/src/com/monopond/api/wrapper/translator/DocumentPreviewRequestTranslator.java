package com.monopond.api.wrapper.translator;

import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentDocMergeField;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeField;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.DocMergeData_type1;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxDocumentPreviewRequest;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxDocumentPreviewRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxResolution;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.StampMergeData_type1;
import com.monopond.api.wrapper.model.DocMerge;
import com.monopond.api.wrapper.model.DocumentPreviewRequest;
import com.monopond.api.wrapper.model.FaxDitheringTechnique;
import com.monopond.api.wrapper.model.Resolution;
import com.monopond.api.wrapper.model.StampMerge;

public class DocumentPreviewRequestTranslator implements
	Translator<DocumentPreviewRequest, FaxDocumentPreviewRequestE> {

	private Translator<Resolution, FaxResolution>
		resolutionTranslator = new ResolutionTranslator();
	private Translator<FaxDitheringTechnique, ApiServiceStub.FaxDitheringTechnique>
		ditheringTechniqueTranslator = new DitheringTechniqueTranslator();
	private Translator<List<DocMerge>, ApiFaxDocumentDocMergeField[]>
		docMergeListTranslator = new DocMergeListTranslator();
	private Translator<List<StampMerge>, ApiFaxDocumentStampMergeField[]>
		stampMergeListTranslator = new StampMergeListTranslator();
	
	@Override
	public FaxDocumentPreviewRequestE toApi(DocumentPreviewRequest request) {
		if (request == null) {
			return null;
		}
		FaxDocumentPreviewRequest apiRequest = new FaxDocumentPreviewRequest();
		apiRequest.setDocumentRef(request.getDocumentRef());
		apiRequest.setResolution(resolutionTranslator
			.toApi(request.getResolution()));
		apiRequest.setDitheringTechnique(ditheringTechniqueTranslator
			.toApi(request.getDitheringTechnique()));
		
		if (request.getDocMergeData() != null) {
			apiRequest.setDocMergeData(wrapArray(docMergeListTranslator
				.toApi(request.getDocMergeData())));
		}
		
		if (request.getStampMergeData() != null) {
			apiRequest.setStampMergeData(wrapArray(stampMergeListTranslator
				.toApi(request.getStampMergeData())));
		}
		
		FaxDocumentPreviewRequestE rv = new FaxDocumentPreviewRequestE();
		rv.setFaxDocumentPreviewRequest(apiRequest);
		return rv;
	}

	private DocMergeData_type1 wrapArray(ApiFaxDocumentDocMergeField[] array) {
		DocMergeData_type1 docMerge = new DocMergeData_type1();
		docMerge.setMergeField(array);
		return docMerge;
	}

	private StampMergeData_type1 wrapArray(ApiFaxDocumentStampMergeField[] array) {
		StampMergeData_type1 stampMerge = new StampMergeData_type1();
		stampMerge.setMergeField(array);
		return stampMerge;
	}

	@Override
	public DocumentPreviewRequest toWrapper(FaxDocumentPreviewRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		FaxDocumentPreviewRequest apiDocPrevRequest =
			apiRequest.getFaxDocumentPreviewRequest();
		String documentRef = apiDocPrevRequest.getDocumentRef();
		DocumentPreviewRequest request = new DocumentPreviewRequest(documentRef);
		
		request.setResolution(resolutionTranslator
			.toWrapper(apiDocPrevRequest.getResolution()));
		request.setDitheringTechnique(ditheringTechniqueTranslator
			.toWrapper(apiDocPrevRequest.getDitheringTechnique()));
		request.setDocMergeData(docMergeListTranslator
			.toWrapper(apiDocPrevRequest.getDocMergeData().getMergeField()));
		request.setStampMergeData(stampMergeListTranslator
			.toWrapper(apiDocPrevRequest.getStampMergeData().getMergeField()));
		return request;
	}

}

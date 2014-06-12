package com.monopond.api.wrapper.translator;

import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocument;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentDocMergeField;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeField;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.DocMergeData_type0;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.StampMergeData_type0;
import com.monopond.api.wrapper.model.DocMerge;
import com.monopond.api.wrapper.model.FaxDitheringTechnique;
import com.monopond.api.wrapper.model.FaxDocument;
import com.monopond.api.wrapper.model.StampMerge;

public class FaxDocumentTranslator implements
		Translator<FaxDocument, ApiFaxDocument> {
	private Translator<FaxDitheringTechnique, ApiServiceStub.FaxDitheringTechnique>
		ditheringTechniqueTranslator = new DitheringTechniqueTranslator();
	private Translator<List<DocMerge>, ApiFaxDocumentDocMergeField[]>
		docMergeListTranslator = new DocMergeListTranslator();
	private Translator<List<StampMerge>, ApiFaxDocumentStampMergeField[]>
		stampMergeListTranslator = new StampMergeListTranslator();
		
	@Override
	public ApiFaxDocument toApi(FaxDocument faxDocument) {
		if (faxDocument == null) {
			return null;
		}
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setDocumentRef(faxDocument.getDocumentRef());
		apiFaxDocument.setFileName(faxDocument.getFileName());
		apiFaxDocument.setFileData(faxDocument.getFileData());
		apiFaxDocument.setOrder(faxDocument.getOrder());
		apiFaxDocument.setDitheringTechnique(ditheringTechniqueTranslator
			.toApi(faxDocument.getDitheringTechnique()));
		
		if (faxDocument.getStampMergeData() != null) {
			apiFaxDocument.setStampMergeData(wrapArray(stampMergeListTranslator
					.toApi(faxDocument.getStampMergeData())));
		}
		
		if (faxDocument.getDocMergeData() != null) {
			apiFaxDocument.setDocMergeData(wrapArray(docMergeListTranslator
				.toApi(faxDocument.getDocMergeData())));
		}
		
		return apiFaxDocument;
	}

	private DocMergeData_type0 wrapArray(ApiFaxDocumentDocMergeField[] array) {
		DocMergeData_type0 docMerge = new DocMergeData_type0();
		docMerge.setMergeField(array);
		return docMerge;
	}

	private StampMergeData_type0 wrapArray(ApiFaxDocumentStampMergeField[] array) {
		StampMergeData_type0 stampMerge = new StampMergeData_type0();
		stampMerge.setMergeField(array);
		return stampMerge;
	}

	@Override
	public FaxDocument toWrapper(ApiFaxDocument apiFaxDocument) {
		if (apiFaxDocument == null) {
			return null;
		}
		String documentRef = apiFaxDocument.getDocumentRef();
		String fileName = apiFaxDocument.getFileName();
		String fileData = apiFaxDocument.getFileData();
		
		FaxDocument faxDocument;
		if (documentRef != null) {
			faxDocument = new FaxDocument(documentRef);
		} else {
			faxDocument = new FaxDocument(fileName, fileData);
		}		
		faxDocument.setOrder(apiFaxDocument.getOrder());
		faxDocument.setDitheringTechnique(ditheringTechniqueTranslator
			.toWrapper(apiFaxDocument.getDitheringTechnique()));
		faxDocument.setDocMergeData(docMergeListTranslator
			.toWrapper(apiFaxDocument.getDocMergeData().getMergeField()));
		faxDocument.setStampMergeData(stampMergeListTranslator
			.toWrapper(apiFaxDocument.getStampMergeData().getMergeField()));
		
		return faxDocument;
	}

}

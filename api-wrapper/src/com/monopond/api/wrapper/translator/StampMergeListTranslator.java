package com.monopond.api.wrapper.translator;

import java.util.ArrayList;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeField;
import com.monopond.api.wrapper.model.StampMerge;

public class StampMergeListTranslator implements
		Translator<List<StampMerge>, ApiFaxDocumentStampMergeField[]> {
	private Translator<StampMerge, ApiFaxDocumentStampMergeField>
		stampMergeFieldTranslator = new StampMergeFieldTranslator();

	@Override
	public ApiFaxDocumentStampMergeField[] toApi(List<StampMerge> stampMergeFields) {
		ApiFaxDocumentStampMergeField[] apiStampMergeFields =
			new ApiFaxDocumentStampMergeField[stampMergeFields.size()];
		for (int i = 0; i < stampMergeFields.size(); i++) {
			apiStampMergeFields[i] = stampMergeFieldTranslator
				.toApi(stampMergeFields.get(i));
		}
		return apiStampMergeFields;
	}

	@Override
	public List<StampMerge> toWrapper(ApiFaxDocumentStampMergeField[] apiStampMergeFields) {
		if (apiStampMergeFields == null) {
			return null;
		}
		List<StampMerge> stampMergeFields = new ArrayList<>();
		
		for (ApiFaxDocumentStampMergeField apiStampMergeField : apiStampMergeFields) {
			stampMergeFields.add(stampMergeFieldTranslator.toWrapper(apiStampMergeField));
		}
		return stampMergeFields;
	}

}

package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeField;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeFieldImageValue;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeFieldKey;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeFieldTextValue;
import com.monopond.api.wrapper.model.StampMerge;

public class StampMergeFieldTranslator implements
		Translator<StampMerge, ApiFaxDocumentStampMergeField> {
	private Translator<StampMerge.Key, ApiFaxDocumentStampMergeFieldKey>
		stampMergeKeyTranslator = new StampMergeKeyTranslator();
	private Translator<StampMerge.Text, ApiFaxDocumentStampMergeFieldTextValue>
		stampMergeTextTranslator = new StampMergeTextTranslator();
	private Translator<StampMerge.Image, ApiFaxDocumentStampMergeFieldImageValue>
		stampMergeImageTranslator = new StampMergeImageTranslator();

	@Override
	public ApiFaxDocumentStampMergeField toApi(StampMerge stampMergeField) {
		if (stampMergeField == null) {
			return null;
		}
		ApiFaxDocumentStampMergeField apiStampMergeField =
			new ApiFaxDocumentStampMergeField();
		
		ApiFaxDocumentStampMergeFieldKey key =
			stampMergeKeyTranslator.toApi(stampMergeField.getKey());
		ApiFaxDocumentStampMergeFieldTextValue textValue =
			stampMergeTextTranslator.toApi(stampMergeField.getTextValue());
		ApiFaxDocumentStampMergeFieldImageValue imageValue =
			stampMergeImageTranslator.toApi(stampMergeField.getImageValue());
		
		apiStampMergeField.setKey(key);
		apiStampMergeField.setTextValue(textValue);
		apiStampMergeField.setImageValue(imageValue);
		return apiStampMergeField;
	}

	@Override
	public StampMerge toWrapper(ApiFaxDocumentStampMergeField apiStampMergeField) {
		if (apiStampMergeField == null) {
			return null;
		}
		StampMerge stampMergeField = new StampMerge();		
		StampMerge.Key key = stampMergeKeyTranslator
			.toWrapper(apiStampMergeField.getKey());
		StampMerge.Text text = stampMergeTextTranslator
			.toWrapper(apiStampMergeField.getTextValue());
		StampMerge.Image image = stampMergeImageTranslator
			.toWrapper(apiStampMergeField.getImageValue());
		
		stampMergeField.setKey(key);
		stampMergeField.setTextValue(text);
		stampMergeField.setImageValue(image);
		return stampMergeField;
	}

}

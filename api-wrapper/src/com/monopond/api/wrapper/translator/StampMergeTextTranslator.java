package com.monopond.api.wrapper.translator;

import java.math.BigDecimal;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeFieldTextValue;
import com.monopond.api.wrapper.model.StampMerge.Text;

public class StampMergeTextTranslator implements
		Translator<Text, ApiFaxDocumentStampMergeFieldTextValue> {

	@Override
	public ApiFaxDocumentStampMergeFieldTextValue toApi(Text stampMergeText) {
		if (stampMergeText == null) {
			return null;
		}
		ApiFaxDocumentStampMergeFieldTextValue apiStampMergeText =
			new ApiFaxDocumentStampMergeFieldTextValue();
		
		apiStampMergeText.setString(stampMergeText.getString());
		apiStampMergeText.setFontName(stampMergeText.getFontName());
		apiStampMergeText.setFontSize(stampMergeText.getFontSize());
		return apiStampMergeText;
	}

	@Override
	public Text toWrapper(ApiFaxDocumentStampMergeFieldTextValue apiStampMergeText) {
		if (apiStampMergeText == null) {
			return null;
		}
		String string = apiStampMergeText.getString();
		String fontName = apiStampMergeText.getFontName();
		BigDecimal fontSize = apiStampMergeText.getFontSize();
		
		return new Text(string, fontName, fontSize);
	}

}

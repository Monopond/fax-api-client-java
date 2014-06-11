package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeFieldKey;
import com.monopond.api.wrapper.model.StampMerge.Key;

public class StampMergeKeyTranslator implements
		Translator<Key, ApiFaxDocumentStampMergeFieldKey> {

	@Override
	public ApiFaxDocumentStampMergeFieldKey toApi(Key stampMergeKey) {
		if (stampMergeKey == null) {
			return null;
		}
		ApiFaxDocumentStampMergeFieldKey apiStampMergeKey =
			new ApiFaxDocumentStampMergeFieldKey();
		
		apiStampMergeKey.setXCoord(stampMergeKey.getX());
		apiStampMergeKey.setYCoord(stampMergeKey.getY());
		return apiStampMergeKey;
	}

	@Override
	public Key toWrapper(ApiFaxDocumentStampMergeFieldKey apiStampMergeKey) {
		if (apiStampMergeKey == null) {
			return null;
		}
		int x = apiStampMergeKey.getXCoord();
		int y = apiStampMergeKey.getYCoord();
		return new Key(x, y);
	}

}

package com.monopond.api.wrapper.translator;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocumentStampMergeFieldImageValue;
import com.monopond.api.wrapper.model.StampMerge.Image;

public class StampMergeImageTranslator implements
		Translator<Image, ApiFaxDocumentStampMergeFieldImageValue> {

	@Override
	public ApiFaxDocumentStampMergeFieldImageValue toApi(Image stampMergeImage) {
		if (stampMergeImage == null) {
			return null;
		}
		ApiFaxDocumentStampMergeFieldImageValue apiStampMergeImage =
			new ApiFaxDocumentStampMergeFieldImageValue();
		apiStampMergeImage.setFileData(stampMergeImage.getFileData());
		apiStampMergeImage.setFileName(stampMergeImage.getFileName());
		apiStampMergeImage.setWidth(stampMergeImage.getWidth());
		apiStampMergeImage.setHeight(stampMergeImage.getHeight());
		return apiStampMergeImage;
	}

	@Override
	public Image toWrapper(ApiFaxDocumentStampMergeFieldImageValue apiStampMergeImage) {
		if (apiStampMergeImage == null) {
			return null;
		}
		String fileName = apiStampMergeImage.getFileName();
		String fileData = apiStampMergeImage.getFileData();
		
		Image stampMergeImage = new Image(fileName, fileData);
		stampMergeImage.setWidth(apiStampMergeImage.getWidth());
		stampMergeImage.setHeight(apiStampMergeImage.getHeight());
		return stampMergeImage;
	}

}

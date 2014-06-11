package com.monopond.api.wrapper;

import com.monopond.api.wrapper.model.DeleteFaxRequest;
import com.monopond.api.wrapper.model.DocumentPreviewRequest;
import com.monopond.api.wrapper.model.DocumentPreviewResponse;
import com.monopond.api.wrapper.model.FaxStatusRequest;
import com.monopond.api.wrapper.model.FaxStatusResponse;
import com.monopond.api.wrapper.model.PauseFaxRequest;
import com.monopond.api.wrapper.model.PauseFaxResponse;
import com.monopond.api.wrapper.model.ResumeFaxRequest;
import com.monopond.api.wrapper.model.ResumeFaxResponse;
import com.monopond.api.wrapper.model.SaveFaxRequest;
import com.monopond.api.wrapper.model.SendFaxRequest;
import com.monopond.api.wrapper.model.SendFaxResponse;
import com.monopond.api.wrapper.model.StopFaxRequest;
import com.monopond.api.wrapper.model.StopFaxResponse;

public interface FaxApiService {
	SendFaxResponse sendFax(SendFaxRequest request) throws FaxApiException;
	
	FaxStatusResponse faxStatus(FaxStatusRequest request) throws FaxApiException;
	
	StopFaxResponse stopFax(StopFaxRequest request) throws FaxApiException;
	
	PauseFaxResponse pauseFax(PauseFaxRequest request) throws FaxApiException;
	
	ResumeFaxResponse resumeFax(ResumeFaxRequest request) throws FaxApiException;
	
	DocumentPreviewResponse documentPreview(DocumentPreviewRequest request) throws FaxApiException;
	
	void saveFaxDocument(SaveFaxRequest request) throws FaxApiException;
	
	void deleteFaxDocument(DeleteFaxRequest request) throws FaxApiException;
}

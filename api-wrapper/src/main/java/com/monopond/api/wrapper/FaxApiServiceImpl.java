package com.monopond.api.wrapper;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.DeleteFaxDocumentRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxDocumentPreviewRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxDocumentPreviewResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxStatusResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.PauseFaxRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.PauseFaxResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ResumeFaxRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ResumeFaxResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SaveFaxDocumentRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SendFaxRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SendFaxResponseE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.StopFaxRequestE;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.StopFaxResponseE;
import com.monopond.api.fax.soap.v2_1.DocumentContentTypeNotFoundExceptionException;
import com.monopond.api.fax.soap.v2_1.DocumentRefAlreadyExistsExceptionException;
import com.monopond.api.fax.soap.v2_1.DocumentRefDoesNotExistExceptionException;
import com.monopond.api.fax.soap.v2_1.InternalServerExceptionException;
import com.monopond.api.fax.soap.v2_1.InterruptedExceptionException;
import com.monopond.api.fax.soap.v2_1.InvalidArgumentsExceptionException;
import com.monopond.api.fax.soap.v2_1.MergeFieldDoesNotMatchDocumentTypeExceptionException;
import com.monopond.api.fax.soap.v2_1.NoMessagesFoundExceptionException;
import com.monopond.api.fax.soap.v2_1.UnknownHostExceptionException;
import com.monopond.api.fax.soap.v2_1.UnsupportedDocumentContentTypeException;
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
import com.monopond.api.wrapper.translator.DeleteFaxRequestTranslator;
import com.monopond.api.wrapper.translator.DocumentPreviewRequestTranslator;
import com.monopond.api.wrapper.translator.DocumentPreviewResponseTranslator;
import com.monopond.api.wrapper.translator.FaxStatusRequestTranslator;
import com.monopond.api.wrapper.translator.FaxStatusResponseTranslator;
import com.monopond.api.wrapper.translator.PauseFaxRequestTranslator;
import com.monopond.api.wrapper.translator.PauseFaxResponseTranslator;
import com.monopond.api.wrapper.translator.ResumeFaxRequestTranslator;
import com.monopond.api.wrapper.translator.ResumeFaxResponseTranslator;
import com.monopond.api.wrapper.translator.SaveFaxRequestTranslator;
import com.monopond.api.wrapper.translator.SendFaxRequestTranslator;
import com.monopond.api.wrapper.translator.SendFaxResponseTranslator;
import com.monopond.api.wrapper.translator.StopFaxRequestTranslator;
import com.monopond.api.wrapper.translator.StopFaxResponseTranslator;
import com.monopond.api.wrapper.translator.Translator;

public class FaxApiServiceImpl implements FaxApiService {
	
	private ApiServiceStub apiService;
	private final String username;
	private final String password;
	
	public FaxApiServiceImpl(String username, String password, String wsdlUrl) throws FaxApiException {
		this.username = username;
		this.password = password;
		try {
			apiService = new ApiServiceStub(wsdlUrl);
		} catch (AxisFault e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}
	
	public FaxApiServiceImpl(String username, String password, WsdlUrl url) throws FaxApiException {
		this(username, password, url.getUrl());
	}
	
	@Override
	public SendFaxResponse sendFax(SendFaxRequest request) throws FaxApiException {		
		Translator<SendFaxRequest, SendFaxRequestE>
			sendFaxRequestTranslator = new SendFaxRequestTranslator();
		SendFaxRequestE apiRequest = sendFaxRequestTranslator.toApi(request);
		
		try {			
			SendFaxResponseE apiResponse = apiService.sendFax(username, password, apiRequest);
			Translator<SendFaxResponse, SendFaxResponseE>
				sendFaxResponseTranslator = new SendFaxResponseTranslator();
			return sendFaxResponseTranslator.toWrapper(apiResponse);			
		} catch (RemoteException | InternalServerExceptionException
				| UnsupportedDocumentContentTypeException
				| DocumentRefAlreadyExistsExceptionException
				| MergeFieldDoesNotMatchDocumentTypeExceptionException
				| DocumentRefDoesNotExistExceptionException
				| DocumentContentTypeNotFoundExceptionException
				| NoMessagesFoundExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}
	
	@Override
	public FaxStatusResponse faxStatus(FaxStatusRequest request) throws FaxApiException {
		Translator<FaxStatusRequest, FaxStatusRequestE>
			faxStatusRequestTranslator = new FaxStatusRequestTranslator();
		FaxStatusRequestE apiRequest = faxStatusRequestTranslator.toApi(request);
		
		try {
			FaxStatusResponseE apiResponse = apiService.faxStatus(username, password, apiRequest);
			Translator<FaxStatusResponse, FaxStatusResponseE>
				faxStatusResponseTranslator = new FaxStatusResponseTranslator();
			return faxStatusResponseTranslator.toWrapper(apiResponse);
		} catch (RemoteException | InternalServerExceptionException
				| NoMessagesFoundExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}
	
	@Override
	public StopFaxResponse stopFax(StopFaxRequest request) throws FaxApiException {
		Translator<StopFaxRequest, StopFaxRequestE>
			stopFaxRequestTranslator = new StopFaxRequestTranslator();
		StopFaxRequestE apiRequest = stopFaxRequestTranslator.toApi(request);
		
		StopFaxResponseE apiResponse;
		try {
			apiResponse = apiService.stopFax(username, password, apiRequest);
			Translator<StopFaxResponse, StopFaxResponseE>
				stopFaxResponseTranslator = new StopFaxResponseTranslator();
			return stopFaxResponseTranslator.toWrapper(apiResponse);
		} catch (RemoteException | InternalServerExceptionException
				| NoMessagesFoundExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}
	
	@Override
	public PauseFaxResponse pauseFax(PauseFaxRequest request) throws FaxApiException {
		Translator<PauseFaxRequest, PauseFaxRequestE>
			pauseFaxRequestTranslator = new PauseFaxRequestTranslator();
		PauseFaxRequestE apiRequest = pauseFaxRequestTranslator.toApi(request);

		try {
			PauseFaxResponseE apiResponse = apiService.pauseFax(username, password, apiRequest);
			Translator<PauseFaxResponse, PauseFaxResponseE>
				pauseFaxResponseTranslator = new PauseFaxResponseTranslator();
			return pauseFaxResponseTranslator.toWrapper(apiResponse);
		} catch (RemoteException | InternalServerExceptionException
				| NoMessagesFoundExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}
	
	@Override
	public ResumeFaxResponse resumeFax(ResumeFaxRequest request) throws FaxApiException {
		Translator<ResumeFaxRequest, ResumeFaxRequestE>
			resumeFaxRequestTranslator = new ResumeFaxRequestTranslator();
		ResumeFaxRequestE apiRequest = resumeFaxRequestTranslator.toApi(request);
		
		try {
			ResumeFaxResponseE apiResponse = apiService.resumeFax(username, password, apiRequest);
			Translator<ResumeFaxResponse, ResumeFaxResponseE>
				resumeFaxResponseTranslator = new ResumeFaxResponseTranslator();
			return resumeFaxResponseTranslator.toWrapper(apiResponse);
		} catch (RemoteException | InternalServerExceptionException
				| NoMessagesFoundExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}

	@Override
	public DocumentPreviewResponse documentPreview(DocumentPreviewRequest request) throws FaxApiException {
		Translator<DocumentPreviewRequest, FaxDocumentPreviewRequestE>
			documentPreviewRequestTranslator = new DocumentPreviewRequestTranslator();
		FaxDocumentPreviewRequestE apiRequest = documentPreviewRequestTranslator.toApi(request);
		
		Translator<DocumentPreviewResponse, FaxDocumentPreviewResponseE> documentPreviewResponseTranslator;
		FaxDocumentPreviewResponseE apiResponse;
		try {
			documentPreviewResponseTranslator = new DocumentPreviewResponseTranslator();
			apiResponse = apiService.faxDocumentPreview(username, password, apiRequest);
			return documentPreviewResponseTranslator.toWrapper(apiResponse);
		} catch (RemoteException | InternalServerExceptionException
				| UnsupportedDocumentContentTypeException
				| UnknownHostExceptionException
				| MergeFieldDoesNotMatchDocumentTypeExceptionException
				| DocumentRefDoesNotExistExceptionException
				| InterruptedExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}

	@Override
	public void saveFaxDocument(SaveFaxRequest request) throws FaxApiException {
		Translator<SaveFaxRequest, SaveFaxDocumentRequestE>
			saveFaxRequestTranslator = new SaveFaxRequestTranslator();
		SaveFaxDocumentRequestE apiRequest = saveFaxRequestTranslator
			.toApi(request);
		try {
			apiService.saveFaxDocument(username, password, apiRequest);
		} catch (RemoteException | InternalServerExceptionException
				| DocumentRefAlreadyExistsExceptionException
				| DocumentContentTypeNotFoundExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteFaxDocument(DeleteFaxRequest request) throws FaxApiException {
		Translator<DeleteFaxRequest, DeleteFaxDocumentRequestE>
			deleteFaxRequestTranslator = new DeleteFaxRequestTranslator();
		DeleteFaxDocumentRequestE apiRequest = deleteFaxRequestTranslator
			.toApi(request);
		
		try {
			apiService.deleteFaxDocument(username, password, apiRequest);
		} catch (RemoteException | InternalServerExceptionException
				| DocumentRefDoesNotExistExceptionException
				| InvalidArgumentsExceptionException e) {
			throw new FaxApiException(e.getMessage(), e);
		}
	}
	
}

package com.monopond.api.wrapper.translator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocument;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessage;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageBlocklist;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.Documents_type1;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxMessages_type1;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxResolution;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.SendFaxRequestE;
import com.monopond.api.wrapper.model.Blocklists;
import com.monopond.api.wrapper.model.FaxDocument;
import com.monopond.api.wrapper.model.FaxMessage;
import com.monopond.api.wrapper.model.Resolution;
import com.monopond.api.wrapper.model.SendFaxRequest;

public class SendFaxRequestTranslator implements
		Translator<SendFaxRequest, SendFaxRequestE> {
	private Translator<List<FaxMessage>, ApiFaxMessage[]>
		faxMessageListTransalator = new FaxMessageListTranslator();
	private Translator<List<FaxDocument>, ApiFaxDocument[]>
		documentListTranslator = new DocumentListTranslator();
	private Translator<Resolution, FaxResolution>
		resolutionTranslator = new ResolutionTranslator();
	private Translator<Date, String>
		dateTranslator = new DateTranslator();
	private Translator<Blocklists, ApiFaxMessageBlocklist>
		blocklistTranslator = new BlocklistsTranslator();
	
	@Override
	public SendFaxRequestE toApi(SendFaxRequest request) {
		if (request == null) {
			return null;
		}
		SendFaxRequestE rv = new SendFaxRequestE();
		ApiServiceStub.SendFaxRequest apiRequest =
				new ApiServiceStub.SendFaxRequest();
		
		apiRequest.setBroadcastRef(request.getBroadcastRef());
		apiRequest.setSendRef(request.getSendRef());		
		apiRequest.setFaxMessages(wrapArray(faxMessageListTransalator
			.toApi(request.getFaxMessages())));
		apiRequest.setSendFrom(request.getSendFrom());
		
		if (request.getDocuments() != null) {
			apiRequest.setDocuments(wrapArray(documentListTranslator
				.toApi(request.getDocuments())));
		}
		
		apiRequest.setResolution(resolutionTranslator
			.toApi(request.getResolution()));
		apiRequest.setScheduledStartTime(dateTranslator
			.toApi(request.getScheduledStartTime()));
		apiRequest.setBlocklists(blocklistTranslator
			.toApi(request.getBlocklists()));
		apiRequest.setRetries(request.getRetries());
		apiRequest.setBusyRetries(request.getBusyRetries());
		apiRequest.setHeaderFormat(request.getHeaderFormat());
		apiRequest.setMustBeSentBeforeDate(toCalendar(
			request.getMustBeSentBeforeDate()));
		apiRequest.setMaxFaxPages(request.getMaxFaxPages());
		
		rv.setSendFaxRequest(apiRequest);
		return rv;
	}

	private Documents_type1 wrapArray(ApiFaxDocument[] array) {
		Documents_type1 apiDocuments = new Documents_type1();
		apiDocuments.setDocument(array);
		return apiDocuments;
	}

	private FaxMessages_type1 wrapArray(ApiFaxMessage[] array) {
		FaxMessages_type1 apiFaxMessages = new FaxMessages_type1();
		apiFaxMessages.setFaxMessage(array);
		return apiFaxMessages;
	}
	
	private Calendar toCalendar(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
	}

	@Override
	public SendFaxRequest toWrapper(SendFaxRequestE apiRequest) {
		if (apiRequest == null) {
			return null;
		}
		ApiServiceStub.SendFaxRequest apiSendRequest =
			apiRequest.getSendFaxRequest();
		List<FaxMessage> faxMessages = faxMessageListTransalator
			.toWrapper(apiSendRequest.getFaxMessages().getFaxMessage());		
		List<FaxDocument> documents = documentListTranslator
			.toWrapper(apiSendRequest.getDocuments().getDocument());
		
		SendFaxRequest request = new SendFaxRequest(faxMessages, documents);
		request.setBroadcastRef(apiSendRequest.getBroadcastRef());
		request.setSendRef(apiSendRequest.getSendRef());
		request.setSendFrom(apiSendRequest.getSendFrom());
		request.setResolution(resolutionTranslator
			.toWrapper(apiSendRequest.getResolution()));
		request.setScheduledStartTime(dateTranslator
			.toWrapper(apiSendRequest.getScheduledStartTime()));
		request.setBlocklists(blocklistTranslator
			.toWrapper(apiSendRequest.getBlocklists()));
		request.setRetries(apiSendRequest.getRetries());
		request.setBusyRetries(apiSendRequest.getBusyRetries());
		request.setHeaderFormat(apiSendRequest.getHeaderFormat());
		request.setMustBeSentBeforeDate(toDate(apiSendRequest
			.getMustBeSentBeforeDate()));
		request.setMaxFaxPages(apiSendRequest.getMaxFaxPages());
		return request;
	}
	
	private Date toDate(Calendar calendar) {
		return calendar == null? null: calendar.getTime();
	}

}

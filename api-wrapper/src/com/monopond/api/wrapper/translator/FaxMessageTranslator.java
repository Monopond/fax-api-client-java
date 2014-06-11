package com.monopond.api.wrapper.translator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxDocument;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessage;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.ApiFaxMessageBlocklist;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.Documents_type0;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.FaxResolution;
import com.monopond.api.wrapper.model.Blocklists;
import com.monopond.api.wrapper.model.FaxDocument;
import com.monopond.api.wrapper.model.FaxMessage;
import com.monopond.api.wrapper.model.Resolution;

public class FaxMessageTranslator
		implements Translator<FaxMessage, ApiFaxMessage> {
	private Translator<List<FaxDocument>, ApiFaxDocument[]>
		documentListTranslator = new DocumentListTranslator();
	private Translator<Resolution, FaxResolution>
		resolutionTranslator = new ResolutionTranslator();
	private Translator<Date, String>
		dateTranslator = new DateTranslator();
	private Translator<Blocklists, ApiFaxMessageBlocklist>
		blocklistsTranslator = new BlocklistsTranslator();

	@Override
	public ApiFaxMessage toApi(FaxMessage faxMessage) {
		if (faxMessage == null) {
			return null;
		}
		ApiFaxMessage apiFaxMessage = new ApiFaxMessage();
		
		apiFaxMessage.setMessageRef(faxMessage.getMessageRef());
		apiFaxMessage.setSendTo(faxMessage.getSendTo());
		apiFaxMessage.setSendFrom(faxMessage.getSendFrom());		
		apiFaxMessage.setDocuments(wrapArray(documentListTranslator
			.toApi(faxMessage.getDocuments())));
		apiFaxMessage.setResolution(resolutionTranslator
			.toApi(faxMessage.getResolution()));
		apiFaxMessage.setScheduledStartTime(dateTranslator
			.toApi(faxMessage.getScheduledStartTime()));
		apiFaxMessage.setBlocklists(blocklistsTranslator
			.toApi(faxMessage.getBlocklists()));
		apiFaxMessage.setRetries(faxMessage.getRetries());
		apiFaxMessage.setBusyRetries(faxMessage.getBusyRetries());
		apiFaxMessage.setHeaderFormat(faxMessage.getHeaderFormat());
		apiFaxMessage.setMustBeSentBeforeDate(
			toCalendar(faxMessage.getMustBeSentBeforeDate()));
		apiFaxMessage.setMaxFaxPages(faxMessage.getMaxFaxPages());
		apiFaxMessage.setCLI(faxMessage.getCli());		
		return apiFaxMessage;
	}
	
	private Documents_type0 wrapArray(ApiFaxDocument[] array) {
		Documents_type0 documents = new Documents_type0();
		documents.setDocument(array);
		return documents;
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
	public FaxMessage toWrapper(ApiFaxMessage apiFaxMessage) {
		if (apiFaxMessage == null) {
			return null;
		}
		String messageRef = apiFaxMessage.getMessageRef();
		String sendTo = apiFaxMessage.getSendTo();
		List<FaxDocument> documents = documentListTranslator
			.toWrapper(unwrapArray(apiFaxMessage.getDocuments()));
		
		FaxMessage faxMessage = new FaxMessage(messageRef, sendTo, documents);
		faxMessage.setSendFrom(apiFaxMessage.getSendFrom());
		faxMessage.setResolution(resolutionTranslator
			.toWrapper(apiFaxMessage.getResolution()));
		faxMessage.setScheduledStartTime(dateTranslator
			.toWrapper(apiFaxMessage.getScheduledStartTime()));
		faxMessage.setBlocklists(blocklistsTranslator
			.toWrapper(apiFaxMessage.getBlocklists()));
		faxMessage.setRetries(apiFaxMessage.getRetries());
		faxMessage.setBusyRetries(apiFaxMessage.getBusyRetries());
		faxMessage.setHeaderFormat(apiFaxMessage.getHeaderFormat());
		faxMessage.setMustBeSentBeforeDate(toDate(apiFaxMessage
			.getMustBeSentBeforeDate()));
		faxMessage.setMaxFaxPages(apiFaxMessage.getMaxFaxPages());
		faxMessage.setCli(apiFaxMessage.getCLI());
		return faxMessage;
	}
	
	private Date toDate(Calendar cal) {
		return cal == null? null: cal.getTime();
	}

	private ApiFaxDocument[] unwrapArray(Documents_type0 documents) {
		return documents.getDocument();
	}
}

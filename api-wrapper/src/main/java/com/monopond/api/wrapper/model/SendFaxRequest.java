package com.monopond.api.wrapper.model;

import java.util.ArrayList;
import java.util.List;

public class SendFaxRequest extends FaxOptions {
	private List<FaxMessage> faxMessages;
	
	public SendFaxRequest(List<FaxMessage> faxMessages, List<FaxDocument> documents) {
		super(documents);
		setFaxMessages(faxMessages);		
		setResolution(Resolution.NORMAL);
	}
	
	public SendFaxRequest(List<FaxMessage> faxMessages) {
		this(faxMessages, null);
	}

	public List<FaxMessage> getFaxMessages() {
		return new ArrayList<>(faxMessages);
	}

	public void setFaxMessages(List<FaxMessage> faxMessages) {
		this.faxMessages = faxMessages;
	}
}

package com.monopond.api.wrapper.model;

import java.util.ArrayList;
import java.util.List;

public class FaxStatusResponse {
	
	private FaxStatusTotals faxStatusTotals;
	private FaxResultsTotals faxResultsTotals;
	private List<FaxMessageStatus> faxMessages;
	
	public FaxStatusResponse() {
		faxMessages = new ArrayList<>();
	}
	
	public FaxStatusTotals getFaxStatusTotals() {
		return faxStatusTotals;
	}

	public void setFaxStatusTotals(FaxStatusTotals faxStatusTotals) {
		this.faxStatusTotals = faxStatusTotals;
	}

	public FaxResultsTotals getFaxResultsTotals() {
		return faxResultsTotals;
	}

	public void setFaxResultsTotals(
			FaxResultsTotals faxResultsTotals) {
		this.faxResultsTotals = faxResultsTotals;
	}

	public List<FaxMessageStatus> getFaxMessages() {
		return new ArrayList<>(faxMessages);
	}

	public void setFaxMessages(List<FaxMessageStatus> faxMessages) {
		this.faxMessages = faxMessages;
	}
}

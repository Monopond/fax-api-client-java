package com.monopond.api.wrapper;

import org.junit.Before;
import org.junit.Test;

import com.monopond.api.wrapper.model.DeleteFaxRequest;
import com.monopond.api.wrapper.model.SaveFaxRequest;

public class DeleteFaxTests {

	private FaxApiService apiService;
	
	@Before
	public void setUp() throws FaxApiException {
		String username = "timtest";
		String password = "gnome4life";
		apiService = new FaxApiServiceImpl(username, password, WsdlUrl.LOCAL);
	}
	
	@Test
	public void deleteSavedFaxDocument() throws FaxApiException {
		String documentRef = "saved-text-file-" + System.currentTimeMillis();
		
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest saveRequest = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(saveRequest);
		
		DeleteFaxRequest deleteRequest = new DeleteFaxRequest(documentRef);
		apiService.deleteFaxDocument(deleteRequest);
	}
	
	@Test(expected = FaxApiException.class)
	public void deleteNonSavedFaxDocument() throws FaxApiException {		
		DeleteFaxRequest deleteRequest = new DeleteFaxRequest("im-a-document-ref-that-is-not-saved");
		apiService.deleteFaxDocument(deleteRequest);
	}
	
	@Test(expected = FaxApiException.class)
	public void deleteDocumentWithoutDocumentRef() throws FaxApiException {		
		DeleteFaxRequest deleteRequest = new DeleteFaxRequest(null);
		apiService.deleteFaxDocument(deleteRequest);
	}
}

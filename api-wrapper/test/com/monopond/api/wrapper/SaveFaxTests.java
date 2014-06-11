package com.monopond.api.wrapper;

import org.junit.Before;
import org.junit.Test;

import com.monopond.api.wrapper.model.SaveFaxRequest;

public class SaveFaxTests {
	
	private FaxApiService apiService;
	
	@Before
	public void setUp() throws FaxApiException {
		String username = "timtest";
		String password = "gnome4life";
		apiService = new FaxApiServiceImpl(username, password, WsdlUrl.LOCAL);
	}
	
	@Test
	public void saveFaxDocument() throws FaxApiException {
		String documentRef = "saved-text-file-" + System.currentTimeMillis(); // DocumentRef must be unique
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest request = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(request);
	}
	
	@Test(expected = FaxApiException.class)
	public void saveFaxDocumentWithNoFile() throws FaxApiException {
		String documentRef = "saved-text-file-" + System.currentTimeMillis(); // DocumentRef must be unique
		SaveFaxRequest request = new SaveFaxRequest(documentRef, null, null);
		apiService.saveFaxDocument(request);
	}
	
	@Test(expected = FaxApiException.class)
	public void saveFaxDocumentWithNoDocumentRef() throws FaxApiException {
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest request = new SaveFaxRequest(null, fileName, fileData);
		apiService.saveFaxDocument(request);
	}
	
	@Test(expected = FaxApiException.class)
	public void saveFaxWithSameDocumentRef() throws FaxApiException {		
		String documentRef = "repeating-document-ref";
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest request1 = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(request1);
		SaveFaxRequest request2 = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(request2);
	}
	
	@Test
	public void saveFaxWithSameFileDifferentDocumentRef() throws FaxApiException {		
		String documentRef = "save-doc-ref-";
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest request1 = new SaveFaxRequest(documentRef + System.currentTimeMillis(), fileName, fileData);
		apiService.saveFaxDocument(request1);
		SaveFaxRequest request2 = new SaveFaxRequest(documentRef + System.currentTimeMillis(), fileName, fileData);
		apiService.saveFaxDocument(request2);
	}

}

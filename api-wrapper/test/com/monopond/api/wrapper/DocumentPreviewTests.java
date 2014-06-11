package com.monopond.api.wrapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.monopond.api.wrapper.model.DocumentPreviewRequest;
import com.monopond.api.wrapper.model.DocumentPreviewResponse;
import com.monopond.api.wrapper.model.FaxDitheringTechnique;
import com.monopond.api.wrapper.model.Resolution;
import com.monopond.api.wrapper.model.SaveFaxRequest;
import com.monopond.api.wrapper.translator.DocumentPreviewRequestTranslator;
import com.monopond.api.wrapper.translator.DocumentPreviewResponseTranslator;

public class DocumentPreviewTests {
	
	private FaxApiService apiService;
	private XmlPrinter printer = new XmlPrinter(System.out);
	
	@Before
	public void setUp() throws FaxApiException {
		String username = "timtest";
		String password = "gnome4life";
		apiService = new FaxApiServiceImpl(username, password, WsdlUrl.LOCAL);
	}
	
	@Test
	public void previewSavedFax() throws FaxApiException {
		System.out.println("* Preview Saved Fax Document");
		String documentRef = "saved-text-file-" + System.currentTimeMillis();
		
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest saveRequest = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(saveRequest);
		
		DocumentPreviewRequest previewRequest = new DocumentPreviewRequest(documentRef);
		printer.print(new DocumentPreviewRequestTranslator(), previewRequest);
		
		DocumentPreviewResponse response = apiService.documentPreview(previewRequest);
		printer.print(new DocumentPreviewResponseTranslator(), response);
		
		assertNotNull(response.getTiffPreview());
		assertNotEquals(0, response.getNumberOfPages());
	}
	
	@Test(expected = FaxApiException.class)
	public void previewNonSavedFax() throws FaxApiException {
		System.out.println("* Preview Unsaved Fax Document");
		
		DocumentPreviewRequest previewRequest = new DocumentPreviewRequest("im-a-document-ref-that-is-not-saved");
		printer.print(new DocumentPreviewRequestTranslator(), previewRequest);
		
		DocumentPreviewResponse response = apiService.documentPreview(previewRequest);
		printer.print(new DocumentPreviewResponseTranslator(), response);		
	}
	
	@Test
	public void previewWithResolutionSet() throws FaxApiException {
		System.out.println("* Preview Saved Fax Document With Resolution Set");
		String documentRef = "saved-text-file-" + System.currentTimeMillis();
		
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest saveRequest = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(saveRequest);
		
		DocumentPreviewRequest previewRequest = new DocumentPreviewRequest(documentRef);
		printer.print(new DocumentPreviewRequestTranslator(), previewRequest);
		
		DocumentPreviewResponse response = apiService.documentPreview(previewRequest);
		printer.print(new DocumentPreviewResponseTranslator(), response);	
		
		String originalTiff = response.getTiffPreview();
		
		previewRequest.setResolution(Resolution.FINE);
		printer.print(new DocumentPreviewRequestTranslator(), previewRequest);
		response = apiService.documentPreview(previewRequest);
		printer.print(new DocumentPreviewResponseTranslator(), response);
		
		String fineResolutionTiff = response.getTiffPreview();
		
		assertNotEquals(originalTiff, fineResolutionTiff);
	}

	
	@Test
	public void previewWithDitheringSet() throws FaxApiException {
		System.out.println("* Preview Saved Fax Document With Dithering Technique Set");
		String documentRef = "saved-text-file-" + System.currentTimeMillis();
		
		String fileName = "test.txt";
		String fileData = "VGhpcyBpcyBhIGZheA==";
		SaveFaxRequest saveRequest = new SaveFaxRequest(documentRef, fileName, fileData);
		apiService.saveFaxDocument(saveRequest);
		
		DocumentPreviewRequest previewRequest = new DocumentPreviewRequest(documentRef);
		printer.print(new DocumentPreviewRequestTranslator(), previewRequest);
		
		DocumentPreviewResponse response = apiService.documentPreview(previewRequest);
		printer.print(new DocumentPreviewResponseTranslator(), response);	
		
		String originalTiff = response.getTiffPreview();
		
		previewRequest.setDitheringTechnique(FaxDitheringTechnique.DARKEN_EXTRA);
		printer.print(new DocumentPreviewRequestTranslator(), previewRequest);
		response = apiService.documentPreview(previewRequest);
		printer.print(new DocumentPreviewResponseTranslator(), response);
		
		String darkenedTiff = response.getTiffPreview();
		
		assertNotEquals(originalTiff, darkenedTiff);
	}
}

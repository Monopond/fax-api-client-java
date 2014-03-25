package com.monopond.api.fax.soap.v2_1;

import com.monopond.api.fax.soap.v2_1.ApiServiceStub.*;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.databinding.ADBBean;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Client {

	private static final String LOCAL_ENDPOINT = "http://localhost:8000/fax/soap/v2.1";
	private static final String TEST_ENDPONT = "https://test.api.monopond.com/fax/soap/v2.1";
	private static final String PROD_ENDPOINT = "https://api.monopond.com/fax/soap/v2.1";

	private ApiServiceStub apiServiceStub;

	private String username = "timtest";
	private String password = "gnome4life";
	
	@Before
	public void setup() throws Exception {
		apiServiceStub = new ApiServiceStub(LOCAL_ENDPOINT);
	}

	@Test
	public void resumeFaxSample () throws Exception{
		//create a new resumeFax request.
		ResumeFaxRequest resumeFaxRequest = new ResumeFaxRequest();
		resumeFaxRequest.setBroadcastRef("broadcast-ref-1");

		//add the resumeFaxRequest to the resumeFaxRequest wrapper.
		ResumeFaxRequestE resumeFaxRequestE = new ResumeFaxRequestE();
		resumeFaxRequestE.setResumeFaxRequest(resumeFaxRequest);
		printXMLElement(resumeFaxRequestE);

		//call the resumeFax method.
		ResumeFaxResponseE resumeFaxResponseE = apiServiceStub.resumeFax(username, password, resumeFaxRequestE);
		printXMLElement(resumeFaxResponseE);
	}

	@Test
	public void pauseFaxSample() throws Exception{
		//create a new pauseFax request.
		PauseFaxRequest pauseFaxRequest = new PauseFaxRequest();
		pauseFaxRequest.setBroadcastRef("broadcast-ref-1");

		//add the pauseFaxRequest to the PauseFaxRequest wrapper.
		PauseFaxRequestE pauseFaxRequestE = new PauseFaxRequestE();
		pauseFaxRequestE.setPauseFaxRequest(pauseFaxRequest);
		printXMLElement(pauseFaxRequestE);

		//call the pauseFax method.
		PauseFaxResponseE pauseFaxResponseE = apiServiceStub.pauseFax(username, password, pauseFaxRequestE);
		printXMLElement(pauseFaxResponseE);
	}

	@Test
	public void stopFaxSample() throws Exception{
		//create a new stopFax request.
		StopFaxRequest stopFaxRequest = new StopFaxRequest();
		stopFaxRequest.setBroadcastRef("broadcast-ref-1");

		//add the stopFaxRequest to the StopFaxRequest wrapper.
		StopFaxRequestE stopFaxRequestE = new StopFaxRequestE();
		stopFaxRequestE.setStopFaxRequest(stopFaxRequest);
		printXMLElement(stopFaxRequestE);

		//call the stopFax method.
		StopFaxResponseE stopFaxResponseE = apiServiceStub.stopFax(username, password, stopFaxRequestE);
		printXMLElement(stopFaxResponseE);
	}

	@Test
	public void faxStatusSample() throws Exception{
		//create a new faxStatus request.
		FaxStatusRequest faxStatusRequest = new FaxStatusRequest();
		faxStatusRequest.setBroadcastRef("broadcast-ref-1");
		faxStatusRequest.setVerbosity(FaxStatusLevel.brief);

		//add the faxStatusRequest to faxStatusRequest wrapper.
		FaxStatusRequestE faxStatusRequestE = new FaxStatusRequestE();
		faxStatusRequestE.setFaxStatusRequest(faxStatusRequest);
		printXMLElement(faxStatusRequestE);

		//call the FaxStatus method.
		FaxStatusResponseE faxStatusResponseE = apiServiceStub.faxStatus(username, password, faxStatusRequestE);
		printXMLElement(faxStatusResponseE);
	}

	@Test
	public void sendFaxSingleSampleWithDocMergeData() throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("someBase64Data");
		apiFaxDocument.setFileName("some.docx");

		ApiFaxDocumentDocMergeField docMergeField1 = new ApiFaxDocumentDocMergeField();
		docMergeField1.setKey("field1");
		docMergeField1.setValue("newtext1");

		ApiFaxDocumentDocMergeField docMergeField2 = new ApiFaxDocumentDocMergeField();
		docMergeField2.setKey("field2");
		docMergeField2.setValue("newtext2");

		DocMergeData_type0 docMergeData = new DocMergeData_type0();
		docMergeData.addMergeField(docMergeField1);
		docMergeData.addMergeField(docMergeField2);

		apiFaxDocument.setDocMergeData(docMergeData);

		//add the faxDoument to documentType wrapper.
		Documents_type0 documentsType0 = new Documents_type0();
		documentsType0.addDocument(apiFaxDocument);

		//create a new fax message.
		ApiFaxMessage apiFaxMessage = new ApiFaxMessage();
		apiFaxMessage.setMessageRef("test-1-1-1");
		apiFaxMessage.setSendTo("6011111111");
		apiFaxMessage.setSendFrom("Test Fax");
		apiFaxMessage.setResolution(FaxResolution.normal);
		apiFaxMessage.setDocuments(documentsType0);

		//add the fax message to faxMessageType wrapper.
		FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
		faxMessagesType1.addFaxMessage(apiFaxMessage);

		//create a new sendFaxRequest.
		SendFaxRequest sendFaxRequest = new SendFaxRequest();
		sendFaxRequest.setFaxMessages(faxMessagesType1);

		//add the sendFaxRequest to the sendFaxRequest wrapper.
		SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
		sendFaxRequestE.setSendFaxRequest(sendFaxRequest);
		printXMLElement(sendFaxRequestE);

		//call the sendFax method.
		SendFaxResponseE sendFaxResponseE = apiServiceStub.sendFax(username, password, sendFaxRequestE);
		printXMLElement(sendFaxResponseE);
	}

	@Test
	public void sendFaxSingleSampleWithStampMergeDataText() throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("someBase64Data");
		apiFaxDocument.setFileName("some.tiff");

		// create merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey1 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey1.setXCoord(0);
		stampMergeFieldKey1.setXCoord(1);

		ApiFaxDocumentStampMergeField stampMergeField1 = new ApiFaxDocumentStampMergeField();
		stampMergeField1.setKey(stampMergeFieldKey1);

		// create merge field text with text, fontSize and fontName
		ApiFaxDocumentStampMergeFieldTextValue stampMergeFieldTextValue1 = new ApiFaxDocumentStampMergeFieldTextValue();
		stampMergeFieldTextValue1.setFontName("Ubuntu-Bold");
		stampMergeFieldTextValue1.setFontSize(BigDecimal.TEN);
		stampMergeFieldTextValue1.setString("text1");
		stampMergeField1.setTextValue(stampMergeFieldTextValue1);

		// create merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey2 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey2.setXCoord(0);
		stampMergeFieldKey2.setXCoord(1);

		ApiFaxDocumentStampMergeField stampMergeField2 = new ApiFaxDocumentStampMergeField();
		stampMergeField2.setKey(stampMergeFieldKey1);

		// create merge field text with text, fontSize and fontName
		ApiFaxDocumentStampMergeFieldTextValue stampMergeFieldTextValue2 = new ApiFaxDocumentStampMergeFieldTextValue();
		stampMergeFieldTextValue2.setFontName("Ubuntu-Bold");
		stampMergeFieldTextValue2.setFontSize(BigDecimal.TEN);
		stampMergeFieldTextValue2.setString("text2");
		stampMergeField2.setTextValue(stampMergeFieldTextValue2);

		StampMergeData_type0 stampMergeData = new StampMergeData_type0();
		stampMergeData.addMergeField(stampMergeField1);
		stampMergeData.addMergeField(stampMergeField2);

		apiFaxDocument.setStampMergeData(stampMergeData);

		//add the faxDoument to documentType wrapper.
		Documents_type0 documentsType0 = new Documents_type0();
		documentsType0.addDocument(apiFaxDocument);

		//create a new fax message.
		ApiFaxMessage apiFaxMessage = new ApiFaxMessage();
		apiFaxMessage.setMessageRef("test-1-1-1");
		apiFaxMessage.setSendTo("6011111111");
		apiFaxMessage.setSendFrom("Test Fax");
		apiFaxMessage.setResolution(FaxResolution.normal);
		apiFaxMessage.setDocuments(documentsType0);

		//add the fax message to faxMessageType wrapper.
		FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
		faxMessagesType1.addFaxMessage(apiFaxMessage);

		//create a new sendFaxRequest.
		SendFaxRequest sendFaxRequest = new SendFaxRequest();
		sendFaxRequest.setFaxMessages(faxMessagesType1);

		//add the sendFaxRequest to the sendFaxRequest wrapper.
		SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
		sendFaxRequestE.setSendFaxRequest(sendFaxRequest);
		printXMLElement(sendFaxRequestE);

		//call the sendFax method.
		SendFaxResponseE sendFaxResponseE = apiServiceStub.sendFax(username, password, sendFaxRequestE);
		printXMLElement(sendFaxResponseE);
	}

	@Test
	public void sendFaxSingleSampleWithStampMergeDataImage() throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("someBase64Data");
		apiFaxDocument.setFileName("some.pdf");

		// create merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey1 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey1.setXCoord(0);
		stampMergeFieldKey1.setXCoord(1);

		ApiFaxDocumentStampMergeField stampMergeField1 = new ApiFaxDocumentStampMergeField();
		stampMergeField1.setKey(stampMergeFieldKey1);

		// create image to be attached with filename, base64Data, width and height
		ApiFaxDocumentStampMergeFieldImageValue stampMergeFieldImageValue1 = new ApiFaxDocumentStampMergeFieldImageValue();
		stampMergeFieldImageValue1.setWidth(123);
		stampMergeFieldImageValue1.setHeight(234);
		stampMergeFieldImageValue1.setFileName("some.tiff");
		stampMergeFieldImageValue1.setFileData("someBase64Data");

		stampMergeField1.setImageValue(stampMergeFieldImageValue1);

		// create merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey2 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey2.setXCoord(0);
		stampMergeFieldKey2.setXCoord(1);

		ApiFaxDocumentStampMergeField stampMergeField2 = new ApiFaxDocumentStampMergeField();
		stampMergeField2.setKey(stampMergeFieldKey2);

		// create image to be attached with filename, base64Data, width and height
		ApiFaxDocumentStampMergeFieldImageValue stampMergeFieldImageValue2 = new ApiFaxDocumentStampMergeFieldImageValue();
		stampMergeFieldImageValue2.setFileName("some.tiff");
		stampMergeFieldImageValue2.setFileData("someBase64Data");
		stampMergeFieldImageValue2.setWidth(234);
		stampMergeFieldImageValue2.setHeight(345);
		stampMergeField2.setImageValue(stampMergeFieldImageValue2);

		StampMergeData_type0 stampMergeData = new StampMergeData_type0();
		stampMergeData.addMergeField(stampMergeField1);
		stampMergeData.addMergeField(stampMergeField2);

		apiFaxDocument.setStampMergeData(stampMergeData);

		// add the faxDoument to documentType wrapper.
		Documents_type0 documentsType0 = new Documents_type0();
		documentsType0.addDocument(apiFaxDocument);

		//create a new fax message.
		ApiFaxMessage apiFaxMessage = new ApiFaxMessage();
		apiFaxMessage.setMessageRef("test-1-1-1");
		apiFaxMessage.setSendTo("6011111111");
		apiFaxMessage.setSendFrom("Test Fax");
		apiFaxMessage.setResolution(FaxResolution.normal);
		apiFaxMessage.setDocuments(documentsType0);

		//add the fax message to faxMessageType wrapper.
		FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
		faxMessagesType1.addFaxMessage(apiFaxMessage);

		//create a new sendFaxRequest.
		SendFaxRequest sendFaxRequest = new SendFaxRequest();
		sendFaxRequest.setFaxMessages(faxMessagesType1);

		//add the sendFaxRequest to the sendFaxRequest wrapper.
		SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
		sendFaxRequestE.setSendFaxRequest(sendFaxRequest);
		printXMLElement(sendFaxRequestE);

		//call the sendFax method.
		SendFaxResponseE sendFaxResponseE = apiServiceStub.sendFax(username, password, sendFaxRequestE);
		printXMLElement(sendFaxResponseE);
	}

	@Test
	public void sendFaxSingleSample() throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("VGhpcyBpcyBhIGZheA==");
		apiFaxDocument.setFileName("test.txt");

		//add the faxDoument to documentType wrapper.
		Documents_type0 documentsType0 = new Documents_type0();
		documentsType0.addDocument(apiFaxDocument);

		//create a new fax message.
		ApiFaxMessage apiFaxMessage = new ApiFaxMessage();
		apiFaxMessage.setMessageRef("test-1-1-1");
		apiFaxMessage.setSendTo("6011111111");
		apiFaxMessage.setSendFrom("Test Fax");
		apiFaxMessage.setResolution(FaxResolution.normal);
		apiFaxMessage.setDocuments(documentsType0);

		//add the fax message to faxMessageType wrapper.
		FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
		faxMessagesType1.addFaxMessage(apiFaxMessage);

		//create a new sendFaxRequest.
		SendFaxRequest sendFaxRequest = new SendFaxRequest();
		sendFaxRequest.setFaxMessages(faxMessagesType1);

		//add the sendFaxRequest to the sendFaxRequest wrapper.
		SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
		sendFaxRequestE.setSendFaxRequest(sendFaxRequest);
		printXMLElement(sendFaxRequestE);

		//call the sendFax method.
		SendFaxResponseE sendFaxResponseE = apiServiceStub.sendFax(username, password, sendFaxRequestE);
		printXMLElement(sendFaxResponseE);
	}

	@Test
	public void sendFaxMultipleSample() throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("VGhpcyBpcyBhIGZheA==");
		apiFaxDocument.setFileName("test.txt");

		//add the faxDoument to documentType wrapper.
		Documents_type0 documentsType0 = new Documents_type0();
		documentsType0.addDocument(apiFaxDocument);

		//create a new fax message.
		ApiFaxMessage apiFaxMessage1 = new ApiFaxMessage();
		apiFaxMessage1.setMessageRef("test-1-1-1");
		apiFaxMessage1.setSendTo("6011111111");
		apiFaxMessage1.setSendFrom("Test Fax");
		apiFaxMessage1.setResolution(FaxResolution.normal);
		apiFaxMessage1.setDocuments(documentsType0);

		ApiFaxMessage apiFaxMessage2 = new ApiFaxMessage();
		apiFaxMessage2.setMessageRef("test-1-1-1");
		apiFaxMessage2.setSendTo("6011111112");
		apiFaxMessage2.setSendFrom("Test Fax");
		apiFaxMessage2.setResolution(FaxResolution.normal);
		apiFaxMessage2.setDocuments(documentsType0);

		//add the fax messages to faxMessageType wrapper.
		FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
		faxMessagesType1.addFaxMessage(apiFaxMessage1);
		faxMessagesType1.addFaxMessage(apiFaxMessage2);

		//create a new sendFaxRequest.
		SendFaxRequest sendFaxRequest = new SendFaxRequest();
		sendFaxRequest.setFaxMessages(faxMessagesType1);

		//add the sendFaxRequest to the sendFaxRequest wrapper.
		SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
		sendFaxRequestE.setSendFaxRequest(sendFaxRequest);
		printXMLElement(sendFaxRequestE);

		//call the sendFax method.
		SendFaxResponseE sendFaxResponseE = apiServiceStub.sendFax(username, password, sendFaxRequestE);
		printXMLElement(sendFaxResponseE);
	}

	@Test
	public void sendFaxMultipleDestinationsSample() throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("VGhpcyBpcyBhIGZheA==");
		apiFaxDocument.setFileName("test.txt");

		//add the faxDoument to documentType wrapper.
		Documents_type1 documentsType1 = new Documents_type1();
		documentsType1.addDocument(apiFaxDocument);

		//create a new fax message.
		ApiFaxMessage apiFaxMessage1 = new ApiFaxMessage();
		apiFaxMessage1.setMessageRef("test-1-1-1");
		apiFaxMessage1.setSendTo("6011111111");

		ApiFaxMessage apiFaxMessage2 = new ApiFaxMessage();
		apiFaxMessage2.setMessageRef("test-1-1-1");
		apiFaxMessage2.setSendTo("6011111112");

		//add the fax messages to faxMessageType wrapper.
		FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
		faxMessagesType1.addFaxMessage(apiFaxMessage1);
		faxMessagesType1.addFaxMessage(apiFaxMessage2);

		//create a new sendFaxRequest.
		SendFaxRequest sendFaxRequest = new SendFaxRequest();
		sendFaxRequest.setFaxMessages(faxMessagesType1);
		sendFaxRequest.setBroadcastRef("Broadcast-1-1");
		sendFaxRequest.setSendRef("Send-ref-1");
		sendFaxRequest.setDocuments(documentsType1);
		sendFaxRequest.setSendFrom("Test Fax");

		//add the sendFaxRequest to the sendFaxRequest wrapper.
		SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
		sendFaxRequestE.setSendFaxRequest(sendFaxRequest);
		printXMLElement(sendFaxRequestE);

		//call the sendFax method.
		SendFaxResponseE sendFaxResponseE = apiServiceStub.sendFax(username, password, sendFaxRequestE);
		printXMLElement(sendFaxResponseE);
	}

	@Test
	public void saveFaxDocumentDocSample() throws Exception{
		// setup save fax document request
		// setup its document reference, filename and base64 file data
		SaveFaxDocumentRequest saveFaxDocumentRequest = new SaveFaxDocumentRequest();
		saveFaxDocumentRequest.setDocumentRef("test-doc-some-doc");
		saveFaxDocumentRequest.setFileName("some.docx");
		saveFaxDocumentRequest.setFileData("someBase64Data");

		SaveFaxDocumentRequestE saveFaxDocumentRequestE = new SaveFaxDocumentRequestE();
		saveFaxDocumentRequestE.setSaveFaxDocumentRequest(saveFaxDocumentRequest);
		printXMLElement(saveFaxDocumentRequestE);

		// call the saveFaxDocument method
		SaveFaxDocumentResponseE saveFaxDocumentResponseE = apiServiceStub.saveFaxDocument(username, password, saveFaxDocumentRequestE);
		printXMLElement(saveFaxDocumentResponseE);
	}

	@Test
	public void saveFaxDocumentTiffSample() throws Exception{
		// setup save fax document request
		// setup its document reference, filename and base64 file data
		SaveFaxDocumentRequest saveFaxDocumentRequest = new SaveFaxDocumentRequest();
		saveFaxDocumentRequest.setDocumentRef("test-doc-some-tiff");
		saveFaxDocumentRequest.setFileName("some.tiff");
		saveFaxDocumentRequest.setFileData("someBase64Data");

		SaveFaxDocumentRequestE saveFaxDocumentRequestE = new SaveFaxDocumentRequestE();
		saveFaxDocumentRequestE.setSaveFaxDocumentRequest(saveFaxDocumentRequest);
		printXMLElement(saveFaxDocumentRequestE);

		// call the saveFaxDocument method
		SaveFaxDocumentResponseE saveFaxDocumentResponseE = apiServiceStub.saveFaxDocument(username, password, saveFaxDocumentRequestE);
		printXMLElement(saveFaxDocumentResponseE);
	}

	@Test
	public void previewFaxDocumentSample() throws Exception{
		// setup preview fax document request
		// refer to some document reference
		FaxDocumentPreviewRequest faxDocumentPreviewRequest = new FaxDocumentPreviewRequest();
		faxDocumentPreviewRequest.setDocumentRef("test-doc-some-png");

		FaxDocumentPreviewRequestE faxDocumentPreviewRequestE = new FaxDocumentPreviewRequestE();
		faxDocumentPreviewRequestE.setFaxDocumentPreviewRequest(faxDocumentPreviewRequest);
		printXMLElement(faxDocumentPreviewRequestE);

		// call the faxDocumentPreview method
		FaxDocumentPreviewResponseE faxDocumentPreviewResponseE = apiServiceStub.faxDocumentPreview(username, password, faxDocumentPreviewRequestE);
		printXMLElement(faxDocumentPreviewResponseE);
	}

	@Test
	public void previewFaxDocumentDocMergeDataSample() throws Exception{
		// setup preview fax document request
		// refer to some microsoft word document reference
		FaxDocumentPreviewRequest faxDocumentPreviewRequest = new FaxDocumentPreviewRequest();
		faxDocumentPreviewRequest.setDocumentRef("test-doc-some-doc");

		// setup merge field, identify mf keys to be replaced with new values
		ApiFaxDocumentDocMergeField docMergeField1 = new ApiFaxDocumentDocMergeField();
		docMergeField1.setKey("field1");
		docMergeField1.setValue("text1");

		ApiFaxDocumentDocMergeField docMergeField2 = new ApiFaxDocumentDocMergeField();
		docMergeField2.setKey("field1");
		docMergeField2.setValue("text2");

		List<ApiFaxDocumentDocMergeField> docMergeFields = new ArrayList<ApiFaxDocumentDocMergeField>();
		docMergeFields.add(docMergeField1);
		docMergeFields.add(docMergeField2);

		ApiFaxDocumentDocMergeField[] docMergeFieldsArray = new ApiFaxDocumentDocMergeField[2];
		docMergeFieldsArray = docMergeFields.toArray(docMergeFieldsArray);

		DocMergeData_type1 docMergeData = new DocMergeData_type1();
		docMergeData.setMergeField(docMergeFieldsArray);

		faxDocumentPreviewRequest.setDocMergeData(docMergeData);

		FaxDocumentPreviewRequestE faxDocumentPreviewRequestE = new FaxDocumentPreviewRequestE();
		faxDocumentPreviewRequestE.setFaxDocumentPreviewRequest(faxDocumentPreviewRequest);
		printXMLElement(faxDocumentPreviewRequestE);

		// call the faxDocumentPreview method
		FaxDocumentPreviewResponseE faxDocumentPreviewResponseE = apiServiceStub.faxDocumentPreview(username, password, faxDocumentPreviewRequestE);
		printXMLElement(faxDocumentPreviewResponseE);
	}

	@Test
	public void previewFaxDocumentStampMergeDataTextSample() throws Exception{
		// setup fax document preview request
		// refer to some tiff or pdf file for text to be stamped
		FaxDocumentPreviewRequest faxDocumentPreviewRequest = new FaxDocumentPreviewRequest();
		faxDocumentPreviewRequest.setDocumentRef("test-doc-some-tiff");

		// setup merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey1 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey1.setXCoord(123);
		stampMergeFieldKey1.setYCoord(234);

		// setup merge field text with fontName, fontSize and text to be stamped
		ApiFaxDocumentStampMergeFieldTextValue stampMergeFieldTextValue1 = new ApiFaxDocumentStampMergeFieldTextValue();
		stampMergeFieldTextValue1.setFontName("Ubuntu-Bold");
		stampMergeFieldTextValue1.setFontSize(BigDecimal.TEN);
		stampMergeFieldTextValue1.setString("text1");

		ApiFaxDocumentStampMergeField stampMergeField1 = new ApiFaxDocumentStampMergeField();
		stampMergeField1.setKey(stampMergeFieldKey1);
		stampMergeField1.setTextValue(stampMergeFieldTextValue1);

		// setup merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey2 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey2.setXCoord(234);
		stampMergeFieldKey2.setXCoord(345);

		// setup merge field text with fontName, fontSize and text to be stamped
		ApiFaxDocumentStampMergeFieldTextValue stampMergeFieldTextValue2 = new ApiFaxDocumentStampMergeFieldTextValue();
		stampMergeFieldTextValue2.setFontName("Ubuntu-Bold");
		stampMergeFieldTextValue2.setFontSize(BigDecimal.TEN);
		stampMergeFieldTextValue2.setString("text1");

		ApiFaxDocumentStampMergeField stampMergeField2 = new ApiFaxDocumentStampMergeField();
		stampMergeField2.setKey(stampMergeFieldKey2);
		stampMergeField2.setTextValue(stampMergeFieldTextValue2);

		StampMergeData_type1 stampMergeData = new StampMergeData_type1();
		stampMergeData.addMergeField(stampMergeField1);
		stampMergeData.addMergeField(stampMergeField2);

		faxDocumentPreviewRequest.setStampMergeData(stampMergeData);

		FaxDocumentPreviewRequestE faxDocumentPreviewRequestE = new FaxDocumentPreviewRequestE();
		faxDocumentPreviewRequestE.setFaxDocumentPreviewRequest(faxDocumentPreviewRequest);
		printXMLElement(faxDocumentPreviewRequestE);

		// call the faxDocumentPreview method
		FaxDocumentPreviewResponseE faxDocumentPreviewResponseE = apiServiceStub.faxDocumentPreview(username, password, faxDocumentPreviewRequestE);
		printXMLElement(faxDocumentPreviewResponseE);
	}

	@Test
	public void previewFaxDocumentStampMergeDataImageSample() throws Exception{
		// setup fax document preview request
		// refer to some tiff or pdf file for text to be stamped
		FaxDocumentPreviewRequest faxDocumentPreviewRequest = new FaxDocumentPreviewRequest();
		faxDocumentPreviewRequest.setDocumentRef("test-doc-some-tiff");

		// setup merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey1 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey1.setXCoord(123);
		stampMergeFieldKey1.setYCoord(234);

		// setup merge field image to be stamped
		ApiFaxDocumentStampMergeFieldImageValue stampMergeFieldImageValue1 = new ApiFaxDocumentStampMergeFieldImageValue();
		stampMergeFieldImageValue1.setFileName("some.tiff");
		stampMergeFieldImageValue1.setFileData("someBase64Data");
		stampMergeFieldImageValue1.setWidth(123);
		stampMergeFieldImageValue1.setHeight(234);

		ApiFaxDocumentStampMergeField stampMergeField1 = new ApiFaxDocumentStampMergeField();
		stampMergeField1.setKey(stampMergeFieldKey1);
		stampMergeField1.setImageValue(stampMergeFieldImageValue1);

		// setup merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey2 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey2.setXCoord(234);
		stampMergeFieldKey2.setXCoord(345);

		// setup merge field image to be stamped
		ApiFaxDocumentStampMergeFieldImageValue stampMergeFieldImageValue2 = new ApiFaxDocumentStampMergeFieldImageValue();
		stampMergeFieldImageValue2.setFileName("some.tiff");
		stampMergeFieldImageValue2.setFileData("someBase64Data");
		stampMergeFieldImageValue2.setWidth(234);
		stampMergeFieldImageValue2.setHeight(345);

		ApiFaxDocumentStampMergeField stampMergeField2 = new ApiFaxDocumentStampMergeField();
		stampMergeField2.setKey(stampMergeFieldKey2);
		stampMergeField2.setImageValue(stampMergeFieldImageValue2);

		StampMergeData_type1 stampMergeData = new StampMergeData_type1();
		stampMergeData.addMergeField(stampMergeField1);
		stampMergeData.addMergeField(stampMergeField2);

		faxDocumentPreviewRequest.setStampMergeData(stampMergeData);

		FaxDocumentPreviewRequestE faxDocumentPreviewRequestE = new FaxDocumentPreviewRequestE();
		faxDocumentPreviewRequestE.setFaxDocumentPreviewRequest(faxDocumentPreviewRequest);
		printXMLElement(faxDocumentPreviewRequestE);

		// call the faxDocumentPreview method
		FaxDocumentPreviewResponseE faxDocumentPreviewResponseE = apiServiceStub.faxDocumentPreview(username, password, faxDocumentPreviewRequestE);
		printXMLElement(faxDocumentPreviewResponseE);
	}

	@Test
	public void deleteFaxDocumentSample() throws Exception{
		// setup delete fax document
		// setup document reference to be deleted
		DeleteFaxDocumentRequest deleteFaxDocumentRequest = new DeleteFaxDocumentRequest();
		deleteFaxDocumentRequest.setDocumentRef("test-doc-some-png");

		DeleteFaxDocumentRequestE deleteFaxDocumentRequestE = new DeleteFaxDocumentRequestE();
		deleteFaxDocumentRequestE.setDeleteFaxDocumentRequest(deleteFaxDocumentRequest);
		printXMLElement(deleteFaxDocumentRequestE);

		// call the deleteFaxDocument method
		DeleteFaxDocumentResponseE deleteFaxDocumentResponseE = apiServiceStub.deleteFaxDocument(username, password, deleteFaxDocumentRequestE);
		printXMLElement(deleteFaxDocumentResponseE);
	}

	private static void printXMLElement(ADBBean adbBeanElement) throws XMLStreamException {
		System.out.println(adbBeanElement.getOMElement(null, OMAbstractFactory.getOMFactory()).toStringWithConsume());
	}
}


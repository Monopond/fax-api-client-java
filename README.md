api-client-java
===============

Monopond Fax API Java Client

###Overview:
* This is a SOAP Web Service client for Monopond Web Services built on top of Java.
* Requires <a href="http://axis.apache.org/axis2/java/core/">Axis2</a> libraries.
* Provides concrete classes that you can use to map values to requests and read responses.

###Basic Usage:
* Import ApiServiceStub and Axis2 libraries.

#Building A Request
```java
import com.monopond.api.fax.soap.v2_1.ApiServiceStub;
import com.monopond.api.fax.soap.v2_1.ApiServiceStub.*;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.databinding.ADBBean;

import javax.xml.stream.XMLStreamException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SampleClient {

        private final static STRING WSDL_TEST_URL = "https://test.api.monopond.com/fax/soap/v2.1";

        private final static STRING WSDL_PROD_URL = "https://api.monopond.com/fax/soap/v2.1";

	public static void main(String[] args) throws Exception {
		//create a new apiServiceStub.
		ApiServiceStub apiServiceStub = new ApiServiceStub(WSDL_PROD_URL);

		//TODO: change user credentials
		String username = "username";
		String password = "password";
        
        SendFaxRequest sendFaxRequest = new SendFaxRequest();
        
        // some private method to display the request
        printXMLElement(sendFaxRequest);
        
        // call some api methods
        SendFaxResponse sendFaxResponse = apiServiceStub.sendFax(username, password, sendFaxRequest);
        
        // some private method to display the response
        printXMLElement(sendFaxResponse);
    }

	private static void printXMLElement(ADBBean adbBeanElement) throws XMLStreamException {
		System.out.println(adbBeanElement.getOMElement(null, OMAbstractFactory.getOMFactory()).toStringWithConsume());
	}
}
```


#SendFax
###Description
This is the core function in the API allowing you to send faxes on the platform. 

Your specific faxing requirements will dictate which send request type below should be used. The two common use cases would be the sending of a single fax document to one destination and the sending of a single fax document to multiple destinations.

###Sending a single fax:
To send a fax to a single destination a request similar to the following example can be used:

```java
public static void sendFaxSingleSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception {
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
```

###Sending multiple faxes:
To send faxes to multiple destinations a request similar to the following example can be used. Please note the addition of another “FaxMessage”:
```java
public static void sendFaxMultipleSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception {
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
```
###Sending faxes to multiple destinations with the same document (broadcasting):
To send the same fax content to multiple destinations (broadcasting) a request similar to the example below can be used.

This method is recommended for broadcasting as it takes advantage of the multiple tiers in the send request. This eliminates the repeated parameters out of the individual fax message elements which are instead inherited from the parent send fax request. An example below shows “SendFrom” being used for both FaxMessages. While not shown in the example below further control can be achieved over individual fax elements to override the parameters set in the parent.

```java
public static void sendFaxMultipleDestinationsSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception {
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
```

When sending multiple faxes in batch it is recommended to group them into requests of around 600 fax messages for optimal performance. If you are sending the same document to multiple destinations it is strongly advised to only attach the document once in the root of the send request rather than attaching a document for each destination.

###Sending Microsoft Documents With DocMergeData:
(This request only works in version 2.1(or higher) of the fax-api.)

This request is used to send a Microsoft document with replaceable variables or merge fields. The merge field follows the pattern ```<mf:key>```.  If your key is ```field1```, it should be typed as ```<mf:field1>``` in the document. Note that the key must be unique within the whole document. The screenshots below are examples of what the request does.

Original .doc file:

![before](https://github.com/Monopond/fax-api/raw/master/img/DocMergeData/before.png)

This is what the file looks like after the fields ```field1```,```field2``` and ```field3``` have been replaced with values ```lazy dog```, ```fat pig``` and ```fat pig```:

![stamp](https://github.com/Monopond/fax-api/raw/master/img/DocMergeData/after.png)

##### Sample Request
The example below shows ```field1``` will be replaced by the value of ```Test```.


```java
public static void sendFaxWithDocMergeData(ApiServiceStub apiServiceStub, String username, String password) throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("someBase64Data");
		apiFaxDocument.setFileName("some.docx");

		ApiFaxDocumentDocMergeField docMergeField1 = new ApiFaxDocumentDocMergeField();
		docMergeField1.setKey("field1");
		docMergeField1.setValue("Test");

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
```
###Sending Tiff and PDF files with StampMergeData:
(This request only works in version 2.1(or higher) of the fax-api.)

This request allows a PDF or TIFF file to be stamped with an image or text, based on X-Y coordinates. The x and y coordinates (0,0) starts at the top left part of the document. The screenshots below are examples of what the request does.

Original tiff file:

![before](https://github.com/Monopond/fax-api/raw/master/img/StampMergeData/image_stamp/before.png)

Sample stamp image:

![stamp](https://github.com/Monopond/fax-api/raw/master/img/StampMergeData/image_stamp/stamp.png)

This is what the tiff file looks like after stamping it with the image above:

![after](https://github.com/Monopond/fax-api/raw/master/img/StampMergeData/image_stamp/after.png) 

The same tiff file, but this time, with a text stamp:

![after](https://github.com/Monopond/fax-api/raw/master/img/StampMergeData/text_stamp/after.png) 

##### Sample Request

The example below shows a PDF that will be stamped with the text “Hello” at xCoord=“1287” and yCoord=“421”, and an image at xCoord=“283” and yCoord=“120”

```java
public static void sendFaxSingleSampleWithStampMergeDataText(ApiServiceStub apiServiceStub, String username, String password) throws Exception {
		//create a new faxDocument.
		ApiFaxDocument apiFaxDocument = new ApiFaxDocument();
		apiFaxDocument.setFileData("someBase64Data");
		apiFaxDocument.setFileName("some.tiff");

		// create merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey1 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey1.setXCoord(1287);
		stampMergeFieldKey1.setYCoord(421);

		ApiFaxDocumentStampMergeField stampMergeField1 = new ApiFaxDocumentStampMergeField();
		stampMergeField1.setKey(stampMergeFieldKey1);

		// create merge field text with text, fontSize and fontName
		ApiFaxDocumentStampMergeFieldTextValue stampMergeFieldTextValue1 = new ApiFaxDocumentStampMergeFieldTextValue();
		stampMergeFieldTextValue1.setFontName("Ubuntu-Bold");
		stampMergeFieldTextValue1.setFontSize(BigDecimal.TEN);
		stampMergeFieldTextValue1.setString("Hello");
		stampMergeField1.setTextValue(stampMergeFieldTextValue1);

		// create merge field key with x and y coordinates
		ApiFaxDocumentStampMergeFieldKey stampMergeFieldKey2 = new ApiFaxDocumentStampMergeFieldKey();
		stampMergeFieldKey2.setXCoord(283);
		stampMergeFieldKey2.setYCoord(120);

		ApiFaxDocumentStampMergeField stampMergeField2 = new ApiFaxDocumentStampMergeField();
		stampMergeField2.setKey(stampMergeFieldKey1);
		
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
```
###Request
**SendFaxRequestE methods:**

|**Name**|**Required**|**Type**|**Description**|
|-----|-----|-----|-----|
| **setSendFaxRequest** | **X** | *SendFaxRequest* | SendFaxRequest to be sent. |

**SendFaxRequest methods:**

**Name**|**Required**|**Type**|**Description**|**Default**
-----|-----|-----|-----|-----
 **setBroadcastRef** ||String|Allows the user to tag all faxes in this request with a user-defined broadcastreference. These faxes can then be retrieved at a later point based on this reference.|
 **setSendRef** ||String|Similar to the BroadcastRef, this allows the user to tag all faxes in this request with a send reference. The SendRef is used to represent all faxes in this request only, so naturally it must be unique.|
 **setFaxMessages** |**X**| *FaxMessages_type1* | FaxMessages describe each individual fax message and its destination. See below for details.|
 **setSendFrom** ||Alphanumeric String|A customisable string used to identify the sender of the fax. Also known as the Transmitting Subscriber Identification (TSID). The maximum string length is 32 characters|Fax
 **setDocuments** |**X**| *Documents_type0* |Each FaxDocument object describes a fax document to be sent. Multiple documents can be defined here which will be concatenated and sent in the same message. See below for details.|
 **setResolution** ||Resolution|Resolution setting of the fax document. Refer to the resolution table below for possible resolution values.|normal
 **setScheduledStartTime** ||DateTime|The date and time the transmission of the fax will start.|Current time (immediate sending)
 **setBlocklists** ||Blocklists|The blocklists that will be checked and filtered against before sending the message. See below for details.WARNING: This feature is inactive and non-functional in this (2.1) version of the Fax API.|
 **setRetries** ||Unsigned Integer|The number of times to retry sending the fax if it fails. Each account has a maximum number of retries that can be changed by consultation with your account manager.|Account Default
 **setBusyRetries** ||Unsigned Integer|Certain fax errors such as “NO_ANSWER” or “BUSY” are not included in the above retries limit and can be set separately. Each account has a maximum number of busy retries that can be changed by consultation with your account manager.|Account default
 **setHeaderFormat** ||String|Allows the header format that appears at the top of the transmitted fax to be changed. See below for an explanation of how to format this field.| From: X, To: X
 **setMustBeSentBeforeDate** | | DateTime |  Specifies a time the fax must be delivered by. Once the specified time is reached the fax will be cancelled across the system. | 
 **setMaxFaxPages** | | Unsigned Integer |  Sets a limit on the amount of pages allowed in a single fax transmission. Especially useful if the user is blindly submitting their customer's documents to the platform. | 20

**FaxMessages_type1 methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **addFaxMessage** | | *An Array of ApiFaxMessage* | An array holder for *ApiFaxMessage* |

***ApiFaxMessage methods:***
This represents a single fax message being sent to a destination.

**Name** | **Required** | **Type** | **Description** | **Default** 
-----|-----|-----|-----|-----
 **setMessageRef** | **X** | String | A unique user-provided identifier that is used to identify the fax message. This can be used at a later point to retrieve the results of the fax message. |
 **setSendTo** | **X** | String | The phone number the fax message will be sent to. |
 **setSendFrom** | | Alphanumeric String | A customisable string used to identify the sender of the fax. Also known as the Transmitting Subscriber Identification (TSID). The maximum string length is 32 characters | Empty
 **setDocuments** | **X** | *Documents_type0* | Each FaxDocument object describes a fax document to be sent. Multiple documents can be defined here which will be concatenated and sent in the same message. See below for details. | 
 **setResolution** | | Resolution|Resolution setting of the fax document. Refer to the resolution table below for possible resolution values.| normal
 **setScheduledStartTime** | | DateTime | The date and time the transmission of the fax will start. | Start now
 **setBlocklists** | | Blocklists | The blocklists that will be checked and filtered against before sending the message. See below for details. WARNING: This feature is inactive and non-functional in this (2.1) version of the Fax API. |
 **setRetries** | | Unsigned Integer | The number of times to retry sending the fax if it fails. Each account has a maximum number of retries that can be changed by consultation with your account manager. | Account Default
 **setBusyRetries** | | Unsigned Integer | Certain fax errors such as “NO_ANSWER” or “BUSY” are not included in the above retries limit and can be set separately. Please consult with your account manager in regards to maximum value.|account default
 **setHeaderFormat** | | String | Allows the header format that appears at the top of the transmitted fax to be changed. See below for an explanation of how to format this field. | From： X, To: X
 **setMustBeSentBeforeDate** | | DateTime |  Specifies a time the fax must be delivered by. Once the specified time is reached the fax will be cancelled across the system. | 
 **setMaxFaxPages** | | Unsigned Integer |  Sets a limit on the amount of pages allowed in a single fax transmission. Especially useful if the user is blindly submitting their customer's documents to the platform. | 20
 **setCLI** | | String| Allows a customer called ID. Note: Must be enabled on the account before it can be used.
 
**Documents_type0 methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **addDocument** | | *An Array of ApiFaxDocument* | An array holder for *ApiFaxDocument* |

***ApiFaxDocument methods:***
Represents a fax document to be sent through the system. Supported file types are: PDF, TIFF, PNG, JPG, GIF, TXT, PS, RTF, DOC, DOCX, XLS, XLSX, PPT, PPTX.

**Name**|**Required**|**Type**|**Description**|**Default**
-----|-----|-----|-----|-----
**setDocumentRef**| **X** | *String* | Unique identifier for the document to be uploaded. |
 **setFileName** |**X**|String|The document filename including extension. This is important as it is used to help identify the document MIME type.|
 **setFileData** |**X**|Base64|The document encoded in Base64 format.|
 **setOrder** | | Integer|If multiple documents are defined on a message this value will determine the order in which they will be transmitted.|0|
 **setDocMergeData** ||| *DocMergeData_type0* |
 **setStampMergeData** ||| *StampMergeData_type0* |

***Resolution Levels:***

| **Value** | **Description** |
| --- | --- |
| **normal** | Normal standard resolution (98 scan lines per inch) |
| **fine** | Fine resolution (196 scan lines per inch) |

***Header Format:iff***
Determines the format of the header line that is printed on the top of the transmitted fax message.
This is set to **rom %from%, To %to%|%a %b %d %H:%M %Y”**y default which produces the following:

From TSID, To 61022221234 Mon Aug 28 15:32 2012 1 of 1

**Value** | **Description**
--- | ---
**%from%**|The value of the **SendFrom** field in the message.
**%to%**|The value of the **SendTo** field in the message.
**%a**|Weekday name (abbreviated)
**%A**|Weekday name
**%b**|Month name (abbreviated)
**%B**|Month name
**%d**|Day of the month as a decimal (01 – 31)
**%m**|Month as a decimal (01 – 12)
**%y**|Year as a decimal (abbreviated)
**%Y**|Year as a decimal
**%H**|Hour as a decimal using a 24-hour clock (00 – 23)
**%I**|Hour as a decimal using a 12-hour clock (01 – 12)
**%M**|Minute as a decimal (00 – 59)
**%S**|Second as a decimal (00 – 59)
**%p**|AM or PM
**%j**|Day of the year as a decimal (001 – 366)
**%U**|Week of the year as a decimal (Monday as first day of the week) (00 – 53)
**%W**|Day of the year as a decimal (001 – 366)
**%w**|Day of the week as a decimal (0 – 6) (Sunday being 0)
**%%**|A literal % character

TODO: The default value is set to: “From %from%, To %to%|%a %b %d %H:%M %Y”

<a name="docMergeDataParameters"></a> 

**DocMergeData_type0 methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **addMergeField** | | *An Array of ApiFaxDocumentDocMergeField* | An array holder for *ApiFaxDocumentDocMergeField* |

**StampMergeData_type0 methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **addMergeField** | | *An Array of ApiFaxDocumentDocMergeField* | An array holder for *ApiFaxDocumentStampMergeField* |

**ApiFaxDocumentDocMergeField methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setKey** | | *String* | A unique identifier used to determine which fields need replacing. |
| **setValue** | | *String* | The value that replaces the key. |

<a name="stampMergeDataParameters"></a> 
**ApiFaxDocumentStampMergeField methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setKey** |  | *StampMergeFieldKey* | Contains x and y coordinates where the ImageValue or TextValue should be placed. |
| **setTextValue** |  | *StampMergeFieldTextValue* | The text value that replaces the key. |
| **setImageValue** |  | *StampMergeFieldImageValue* | The image value that replaces the key. |

 **ApiFaxDocumentStampMergeFieldKey methods:**

| **Name** | **Required** | **Type** | **Description** |
|----|-----|-----|-----|
| **setXCoord** | **X** | *Int* | X coordinate. |
| **setYCoord** | **X** | *Int* | Y coordinate. |

**ApiFaxDocumentStampMergeFieldTextValue methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setFontName** | **X** | *String* | Font name to be used. See list of support font names [here](#list-of-supported-font-names-for-stampmergefield-textvalue). |
| **setFontSize** | **X** | *Decimal* | Font size to be used. |
| **setTextValue** | **X** | *String* | Text to be stamped. |

**ApiFaxDocumentStampMergeFieldImageValue methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setFileName** |  | *String* | The document filename including extension. This is important as it is used to help identify the document MIME type. |
| **setFileData** |  | *Base64* | The document encoded in Base64 format. |

###Response
The response received from a `SendFaxRequest` matches the response you receive when calling the `FaxStatus` method call with a `send` verbosity level.

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:
**InvalidArgumentsException, NoMessagesFoundException, DocumentContentTypeNotFoundException, or InternalServerException.**
You can find more details on these faults [here](#section5).

##FaxStatus
###Description

This function provides you with a method of retrieving the status, details and results of fax messages sent. While this is a legitimate method of retrieving results we strongly advise that you take advantage of our callback service, which will push these fax results to you as they are completed.

When making a status request, you must provide at least a `BroadcastRef`, `SendRef` or `MessageRef`. The 
function will also accept a combination of these to further narrow the request query.
- Limiting by a `BroadcastRef` allows you to retrieve faxes contained in a group of send requests.
- Limiting by `SendRef` allows you to retrieve faxes contained in a single send request.
- Limiting by `MessageRef` allows you to retrieve a single fax message.

There are multiple levels of verbosity available in the request; these are explained in detail below.

**FaxStatusRequestE methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setFaxStatusRequest** | **X**  | *FaxStatusRequest* | FaxStatusRequest to be sent. |

**FaxStatusRequest methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setBroadcastRef** |  | *String* | User-defined broadcast reference. |
| **setSendRef** |  | *String* | User-defined send reference. |
| **setMessageRef** |  | *String* | User-defined message reference. |
| **setVerbosity** |  | *String* | Verbosity String The level of detail in the status response. Please see below for a list of possible values.| |

**Verbosity Levels:**	
  
| **Value** | **Description** |
| --- | --- |
| **brief** | Gives you an overall view of the messages. This simply shows very high-level statistics, consisting of counts of how many faxes are at each status (i.e. processing, queued,sending) and totals of the results of these faxes (success, failed, blocked). |
| **send** | send Includes the results from ***“brief”*** while also including an itemised list of each fax message in the request. |
| **details** | details Includes the results from ***“send”*** along with details of the parameters used to send the fax messages. |
| **results** |Includes the results from ***“send”*** along with the sending results of the fax messages. |
| **all** | all Includes the results from both ***“details”*** and ***“results”*** along with some extra uncommon fields. |

###Response
The response received depends entirely on the verbosity level specified.

**FaxStatusResponseE methods:**

| Name | Type | Description |
| --- | --- | --- | --- |
| **getFaxStatusResponse** | *FaxStatusResponse* | Retrieve received FaxStatusResponse. |

**FaxStatusResponse methods:**

| Name | Type | Verbosity | Description |
| --- | --- | --- | --- |
|  **getFaxStatusTotals** | *FaxStatusTotals* | *brief* | Counts of how many faxes are at each status. See below for more details. |
|  **getFaxResultsTotals** | *FaxResultsTotals* | *brief* | FaxResultsTotals FaxResultsTotals brief Totals of the end results of the faxes. See below for more details. |
|  **getFaxMessages** | *Array of FaxMessage* | *send* | send List of each fax in the query. See below for more details. |

**FaxStatusTotals:**

Contains the total count of how many faxes are at each status. 
To see more information on each fax status, view the FaxStatus table below.

| Name | Type | Verbosity | Description |
| --- | --- | --- | --- |
| **getPending** | *Long* | *brief* | Fax is pending on the system and waiting to be processed.|
| **getProcessing** | *Long* | *brief* | Fax is in the initial processing stages. |
| **getQueued** | *Long* | *brief* | Fax has finished processing and is queued, ready to send out at the send time. |
| **getStarting** | *Long* | *brief* | Fax is ready to be sent out. |
| **getSending** | *Long* | *brief* | Fax has been spooled to our servers and is in the process of being sent out. |
| **getFinalizing** | *Long* | *brief* | Fax has finished sending and the results are being processed.|
| **getDone** | *Long* | *brief* | Fax has completed and no further actions will take place. The detailed results are available at this status. |

**FaxResultsTotals:**

Contains the total count of how many faxes ended in each result, as well as some additional totals. To view more information on each fax result, view the FaxResults table below.

| Name | Type | Verbosity | Description |
| --- | --- | --- | --- |
| **getSuccess** | *Long* | *brief* | Fax has successfully been delivered to its destination.|
| **getBlocked** | *Long* |  *brief* | Destination number was found in one of the block lists. |
| **getFailed** | *Long* | *brief* | Fax failed getting to its destination.|
| **getTotalAttempts** | *Long* | *brief* |Total attempts made in the reference context.|
| **getTotalFaxDuration** | *Long* | *brief* |totalFaxDuration Long brief Total time spent on the line in the reference context.|
| **getTotalPages** | *Long* | *brief* | Total pages sent in the reference context.|


**apiFaxMessageStatus:**

| Name | Type | Verbosity | Description |
| --- | --- | --- | --- |
|  **getMessageRef** | *String* | *send* | |
|  **getSendRef** | *String* | *send* | |
|  **getBroadcastRef** | *String* | *send* | |
|  **getSendTo** | *String* | *send* | |
|  **getStatus** |  | *send* | The current status of the fax message. See the FaxStatus table above for possible status values. |
|  **getFaxDetails** | *FaxDetails* | *details* | Contains the details and settings the fax was sent with. See below for more details. |
|  **getFaxResults** | *Array of FaxResult* | *results* | Contains the results of each attempt at sending the fax message and their connection details. See below for more details. |

**FaxDetails:**

| Name | Type | Verbosity |
| --- | --- | --- | --- |
|  **getSendFrom** | *Alphanumeric String* | *details* |
|  **getResolution** | *String* | *details* |
|  **getRetries** | *Integer* | *details* |
|  **getBusyRetries** | *Integer* | *details* |
|  **getHeaderFormat** | *String* | *details* |

**FaxResults:**

| Name | Type | Verbosity | Description |
| --- | --- | --- | --- |
|  **getAttempt** | *Integer* | *results* | The attempt number of the FaxResult. |
|  **getResult** | *String* | *results* | The result of the fax message. See the FaxResults table above for all possible results values. |
|  **getError** | *FaxError* | *results* |  The fax error code if the fax was not successful. See below for all possible values. |
|  **getCost** | *BigDecimal* | *results* | The final cost of the fax message. | 
|  **getPages** | *Integer* | *results* | Total pages sent to the end fax machine. |
|  **getScheduledStartTime** | *DateTime* | *results* | The date and time the fax is scheduled to start. |
|  **getDateCallStarted** | *DateTime* | *results* | Date and time the fax started transmitting. |
|  **getDateCallEnded** | *DateTime* | *results* | Date and time the fax finished transmitting. |

**FaxError:**

| Value | Error Name |
| --- | --- |
| **DOCUMENT_EXCEEDS_PAGE_LIMIT** | Document exceeds page limit |
| **DOCUMENT_UNSUPPORTED** | Unsupported document type |
| **DOCUMENT_FAILED_CONVERSION** | Document failed conversion |
| **FUNDS_INSUFFICIENT** | Insufficient funds |
| **FUNDS_FAILED** | Failed to transfer funds |
| **BLOCK_ACCOUNT** | Number cannot be sent from this account |
| **BLOCK_GLOBAL** | Number found in the Global blocklist |
| **BLOCK_SMART** | Number found in the Smart blocklist |
| **BLOCK_DNCR** | Number found in the DNCR blocklist |
| **BLOCK_CUSTOM** | Number found in a user specified blocklist |
| **FAX_NEGOTIATION_FAILED** | Negotiation failed |
| **FAX_EARLY_HANGUP** | Early hang-up on call |
| **FAX_INCOMPATIBLE_MACHINE** | Incompatible fax machine |
| **FAX_BUSY** | Phone number busy |
| **FAX_NUMBER_UNOBTAINABLE** | Number unobtainable |
| **FAX_SENDING_FAILED** | Sending fax failed |
| **FAX_CANCELLED** | Cancelled |
| **FAX_NO_ANSWER** | No answer |
| **FAX_UNKNOWN** | Unknown fax error |

###SOAP Faults

This function will throw one of the following SOAP faults/exceptions if something went wrong:

**InvalidArgumentsException**, **NoMessagesFoundException**, or **InternalServerException**.
You can find more details on these faults [here](#section5).


###Sending a faxStatus Request with “brief” verbosity:

```java
	public static void faxStatusSampleWithBriefVerbosity(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

###Sending a faxStatus Request with “send” verbosity:

```java
	public static void faxStatusSampleWithSendVerbosity(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new faxStatus request.
		FaxStatusRequest faxStatusRequest = new FaxStatusRequest();
		faxStatusRequest.setBroadcastRef("broadcast-ref-1");
		faxStatusRequest.setVerbosity(FaxStatusLevel.send);

		//add the faxStatusRequest to faxStatusRequest wrapper.
		FaxStatusRequestE faxStatusRequestE = new FaxStatusRequestE();
		faxStatusRequestE.setFaxStatusRequest(faxStatusRequest);
		printXMLElement(faxStatusRequestE);

		//call the FaxStatus method.
		FaxStatusResponseE faxStatusResponseE = apiServiceStub.faxStatus(username, password, faxStatusRequestE);
		printXMLElement(faxStatusResponseE);
	}
```

###Sending a faxStatus Request with “details” verbosity:

```java
	public static void faxStatusSampleWithDetailsVerbosity(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new faxStatus request.
		FaxStatusRequest faxStatusRequest = new FaxStatusRequest();
		faxStatusRequest.setBroadcastRef("broadcast-ref-1");
		faxStatusRequest.setVerbosity(FaxStatusLevel.details);

		//add the faxStatusRequest to faxStatusRequest wrapper.
		FaxStatusRequestE faxStatusRequestE = new FaxStatusRequestE();
		faxStatusRequestE.setFaxStatusRequest(faxStatusRequest);
		printXMLElement(faxStatusRequestE);

		//call the FaxStatus method.
		FaxStatusResponseE faxStatusResponseE = apiServiceStub.faxStatus(username, password, faxStatusRequestE);
		printXMLElement(faxStatusResponseE);
	}
```

###Sending a faxStatus Request with “results” verbosity:

```java
	public static void faxStatusSampleWithResultsVerbosity(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new faxStatus request.
		FaxStatusRequest faxStatusRequest = new FaxStatusRequest();
		faxStatusRequest.setBroadcastRef("broadcast-ref-1");
		faxStatusRequest.setVerbosity(FaxStatusLevel.results);

		//add the faxStatusRequest to faxStatusRequest wrapper.
		FaxStatusRequestE faxStatusRequestE = new FaxStatusRequestE();
		faxStatusRequestE.setFaxStatusRequest(faxStatusRequest);
		printXMLElement(faxStatusRequestE);

		//call the FaxStatus method.
		FaxStatusResponseE faxStatusResponseE = apiServiceStub.faxStatus(username, password, faxStatusRequestE);
		printXMLElement(faxStatusResponseE);
	}
```

##StopFax

###Description
Stops a fax message from sending. This fax message must either be paused, queued, starting or sending. Please note the fax cannot be stopped if the fax is currently in the process of being transmitted to the destination device.

When making a stop request you must provide at least a `BroadcastRef`, `SendRef` or `MessageRef`. The function will also accept a combination of these to further narrow down the request.

###Request
**StopFaxRequestE methods**

| Name | Required | Type | Description |
| --- | --- | --- | --- | --- |
| **setStopFaxRequest** | | *StopFaxRequest* | StopFaxRequest to be sent. |

**StopFaxRequest methods**

| Name | Required | Type | Description |
| --- | --- | --- | --- | --- |
|  **setBroadcastRef** | | *String* | User-defined broadcast reference. |
|  **setSendRef** |  | *String* | User-defined send reference. |
|  **setMessageRef** |  | *String* | User-defined message reference. |

###Response
The response received from a `StopFaxRequest` is the same response you would receive when calling the `FaxStatus` method call with the `send` verbosity level.

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:

**InvalidArgumentsException**, **NoMessagesFoundException**, or **InternalServerException**.
You can find more details on these faults [here](#section5).

###StopFax Request limiting by BroadcastRef:
```java
	public static void stopFaxSampleWithBroadcastReference(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

###StopFax Request limiting by SendRef:

```java
	public static void stopFaxSampleWithSendReference(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new stopFax request.
		StopFaxRequest stopFaxRequest = new StopFaxRequest();
		stopFaxRequest.setSendRef("send-ref-1");

		//add the stopFaxRequest to the StopFaxRequest wrapper.
		StopFaxRequestE stopFaxRequestE = new StopFaxRequestE();
		stopFaxRequestE.setStopFaxRequest(stopFaxRequest);
		printXMLElement(stopFaxRequestE);

		//call the stopFax method.
		StopFaxResponseE stopFaxResponseE = apiServiceStub.stopFax(username, password, stopFaxRequestE);
		printXMLElement(stopFaxResponseE);
	}
```

###StopFax Request limiting by MessageRef:
```java
	public static void stopFaxSampleWithMessageReference(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new stopFax request.
		StopFaxRequest stopFaxRequest = new StopFaxRequest();
		stopFaxRequest.setMessageRef("message-ref-1");

		//add the stopFaxRequest to the StopFaxRequest wrapper.
		StopFaxRequestE stopFaxRequestE = new StopFaxRequestE();
		stopFaxRequestE.setStopFaxRequest(stopFaxRequest);
		printXMLElement(stopFaxRequestE);

		//call the stopFax method.
		StopFaxResponseE stopFaxResponseE = apiServiceStub.stopFax(username, password, stopFaxRequestE);
		printXMLElement(stopFaxResponseE);
	}
```

##PauseFax

###Description
Pauses a fax message before it starts transmitting. This fax message must either be queued, starting or sending. Please note the fax cannot be paused if the message is currently being transmitted to the destination device.

When making a pause request, you must provide at least a `BroadcastRef`, `SendRef` or `MessageRef`. The function will also accept a combination of these to further narrow down the request. 

###Request
**PauseFaxRequestE methods:**

| Name | Required | Type | Description |
| --- | --- | --- | --- |
| **setPauseFaxRequest** | *X* | *PauseFaxRequest* | PauseFaxRequest to be sent. |

**PauseFaxRequest methods:**

| Name | Required | Type | Description |
| --- | --- | --- | --- |
|  **setBroadcastRef** | | *String* | User-defined broadcast reference. |
|  **setSendRef** | | *String* | User-defined send reference. |
|  **setMessageRef** | | *String* | User-defined message reference. |

###Response
The response received from a `PauseFaxRequest` is the same response you would receive when calling the `FaxStatus` method call with the `send` verbosity level. 

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:
**InvalidArgumentsException**, **NoMessagesFoundException**, or **InternalServerException**.
You can find more details on these faults in [here](#section5).

###PauseFax Request limiting by BroadcastRef:
```java
public static void pauseFaxSampleWithBroadcastReference(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

###PauseFax Request limiting by SendRef:
```java
public static void pauseFaxSampleWithSendReference(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new pauseFax request.
		PauseFaxRequest pauseFaxRequest = new PauseFaxRequest();
		pauseFaxRequest.setSendRef("send-ref-1");

		//add the pauseFaxRequest to the PauseFaxRequest wrapper.
		PauseFaxRequestE pauseFaxRequestE = new PauseFaxRequestE();
		pauseFaxRequestE.setPauseFaxRequest(pauseFaxRequest);
		printXMLElement(pauseFaxRequestE);

		//call the pauseFax method.
		PauseFaxResponseE pauseFaxResponseE = apiServiceStub.pauseFax(username, password, pauseFaxRequestE);
		printXMLElement(pauseFaxResponseE);
	}
```
###PauseFax Request limiting by MessageRef:
```java
public static void pauseFaxSampleWithMessageReference(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new pauseFax request.
		PauseFaxRequest pauseFaxRequest = new PauseFaxRequest();
		pauseFaxRequest.setMessageRef("message-ref-1");

		//add the pauseFaxRequest to the PauseFaxRequest wrapper.
		PauseFaxRequestE pauseFaxRequestE = new PauseFaxRequestE();
		pauseFaxRequestE.setPauseFaxRequest(pauseFaxRequest);
		printXMLElement(pauseFaxRequestE);

		//call the pauseFax method.
		PauseFaxResponseE pauseFaxResponseE = apiServiceStub.pauseFax(username, password, pauseFaxRequestE);
		printXMLElement(pauseFaxResponseE);
	}
```

##ResumeFax

When making a resume request, you must provide at least a `BroadcastRef`, `SendRef` or `MessageRef`. The function will also accept a combination of these to further narrow down the request. 

###Request
**ResumeFaxRequestE methods:**

| Name | Required | Type | Description |
| --- | --- | --- | --- |
| **setResumeFaxRequest** | *X* | *ResumeFaxRequest* | ResumeFaxRequest to be sent. |

**ResumeFaxRequest methods:**

| Name | Required | Type | Description |
| --- | --- | --- | --- |
|  **setBroadcastRef** | | *String* | User-defined broadcast reference. |
|  **setSendRef** | | *String* | User-defined send reference. |
|  **setMessageRef** | | *String* | User-defined message reference. |

###Response
The response received from a `ResumeFaxRequest` is the same response you would receive when calling the `FaxStatus` method call with the `send` verbosity level. 

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:
**InvalidArgumentsException**, **NoMessagesFoundException**, or **InternalServerException**.
You can find more details on these faults [here](#section5).

###ResumeFax request limiting by BroadcastRef:
```java
public static void resumeFaxSampleWithBroadcastReference (ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```
###ResumeFax request limiting by SendRef:
```java
public static void resumeFaxSampleWithSendReference (ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new resumeFax request.
		ResumeFaxRequest resumeFaxRequest = new ResumeFaxRequest();
		resumeFaxRequest.setSendRef("send-ref-1");

		//add the resumeFaxRequest to the resumeFaxRequest wrapper.
		ResumeFaxRequestE resumeFaxRequestE = new ResumeFaxRequestE();
		resumeFaxRequestE.setResumeFaxRequest(resumeFaxRequest);
		printXMLElement(resumeFaxRequestE);

		//call the resumeFax method.
		ResumeFaxResponseE resumeFaxResponseE = apiServiceStub.resumeFax(username, password, resumeFaxRequestE);
		printXMLElement(resumeFaxResponseE);
	}
```
###ResumeFax request limiting by MessageRef:
```java
public static void resumeFaxSampleWithMessageReference (ApiServiceStub apiServiceStub, String username, String password) throws Exception{
		//create a new resumeFax request.
		ResumeFaxRequest resumeFaxRequest = new ResumeFaxRequest();
		resumeFaxRequest.setMessageRef("message-ref-1");

		//add the resumeFaxRequest to the resumeFaxRequest wrapper.
		ResumeFaxRequestE resumeFaxRequestE = new ResumeFaxRequestE();
		resumeFaxRequestE.setResumeFaxRequest(resumeFaxRequest);
		printXMLElement(resumeFaxRequestE);

		//call the resumeFax method.
		ResumeFaxResponseE resumeFaxResponseE = apiServiceStub.resumeFax(username, password, resumeFaxRequestE);
		printXMLElement(resumeFaxResponseE);
	}
```

##FaxDocumentPreview
###Description

This function provides you with a method to generate a preview of a saved document at different resolutions with various dithering settings. It returns a tiff data in base64 along with a page count.

###Request
**FaxDocumentPreviewRequestE methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setFaxDocumentPreviewRequest** |  | *FaxDocumentPreviewRequest* | FaxDocumentPreviewRequest to be sent. |

**FaxDocumentPreviewRequest methods:**

| **Name** | **Required** | **Type** | **Description** | **Default** |
|--- | --- | --- | --- | ---|
|**setDocumentRef**| **X** | *String* | Unique identifier for the document to be uploaded. ||
| **setResolution** |  | *Resolution* |Resolution setting of the fax document. Refer to the resolution table below for possible resolution values.| normal |
| **setDitheringTechnique** | | *FaxDitheringTechnique* | Applies a custom dithering method to the fax document before transmission. | |
| **setDocMergeData** | | *DocMergeData_type1* | Each mergefield has a key and a value. The system will look for the keys in a document and replace them with their corresponding value. ||
| **setStampMergeData** | | *StampMergeData_type1* | Each mergefield has a key a corressponding TextValue/ImageValue. The system will look for the keys in a document and replace them with their corresponding value. | | |

**DocMergeData_type1 methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setMergeFields** | | *An Array of ApiFaxDocumentDocMergeField* | An array setter for ApiFaxDocumentDocMergeField. |

**StampMergeData_type1 methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setMergeFields** | | *An Array of ApiFaxDocumentStampMergeField* | An array setter for ApiFaxDocumentDocMergeField. |

**ApiFaxDocumentDocMergeField methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setKey** | | *String* | A unique identifier used to determine which fields need replacing. |
| **setValue** | | *String* | The value that replaces the key. |

**ApiFaxDocumentStampMergeField methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setKey** |  | *StampMergeFieldKey* | Contains x and y coordinates where the ImageValue or TextValue should be placed. |
| **setTextValue** |  | *StampMergeFieldTextValue* | The text value that replaces the key. |
| **setImageValue** |  | *StampMergeFieldImageValue* | The image value that replaces the key. |

 **ApiFaxDocumentStampMergeFieldKey methods:**

| **Name** | **Required** | **Type** | **Description** |
|----|-----|-----|-----|
|  **setXCoord** |  | *Int* | X coordinate. |
|  **setYCoord** |  | *Int* | Y coordinate. |

**ApiFaxDocumentStampMergeFieldTextValue methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setFontName** |  | *String* | Font name to be used. See list of support font names [here](#list-of-supported-font-names-for-stampmergefield-textvalue). |
| **setFontSize** |  | *Decimal* | Font size to be used. |

**ApiFaxDocumentStampMergeFieldImageValue methods:**

|**Name** | **Required** | **Type** | **Description** |
|-----|-----|-----|-----|
| **setFileName** |  | *String* | The document filename including extension. This is important as it is used to help identify the document MIME type. |
| **setFileData** |  | *Base64* | The document encoded in Base64 format. |

**FaxDitheringTechnique:**

| Value | Fax Dithering Technique |
| --- | --- |
| **none** | No dithering. |
| **normal** | Normal dithering.|
| **turbo** | Turbo dithering.|
| **darken** | Darken dithering.|
| **darken_more** | Darken more dithering.|
| **darken_extra** | Darken extra dithering.|
| **ligthen** | Lighten dithering.|
| **lighten_more** | Lighten more dithering. |
| **crosshatch** | Crosshatch dithering. |
| **DETAILED** | Detailed dithering. |

**Resolution Levels:**

| **Value** | **Description** |
| --- | --- |
| **normal** | Normal standard resolution (98 scan lines per inch) |
| **fine** | Fine resolution (196 scan lines per inch) |

###Response
**FaxDocumentPreviewResponseE methods**

|**Name** | **Type** | **Description** |
|-----|-----|-----|
| **getFaxDocumentPreviewResponse** | *FaxDocumentPreviewResponse* | Retrieve received FaxDocumentPreviewResponse. |

**FaxDocumentPreviewResponse methods**

|**Name** | **Type** | **Description** |
|-----|-----|-----
| **getTiffPreview** | *String* | A preview version of the document encoded in Base64 format. |
| **getNumberOfPages** | *Int* | Total number of pages in the document preview. |

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:
**DocumentRefDoesNotExistException**, **InternalServerException**, **UnsupportedDocumentContentType**, **MergeFieldDoesNotMatchDocumentTypeException**, **UnknownHostException**.
You can find more details on these faults [here](#section5).

###FaxDocumentPreview request
```java
	public static void previewFaxDocumentSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

###FaxDocumentPreview request with Document Merge Data
```java
	public static void previewFaxDocumentDocMergeDataSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

###FaxDocumentPreview request with Stamped Text
```java
	public static void previewFaxDocumentStampMergeDataTextSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

###FaxDocumentPreview request with Stamped Image
```java
	public static void previewFaxDocumentStampMergeDataImageSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

##SaveFaxDocument
###Description

This function allows you to upload a document and save it under a document reference (DocumentRef) for later use. (Note: These saved documents only last 30 days on the system.)

###Request
**SaveFaxDocumentRequestE methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setSaveFaxDocumentRequest** | **X** | *SaveFaxDocumentRequest* | SaveFaxDocumentRequest to be sent. |

**SaveFaxDocumentRequest methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setDocumentRef** | **X** | *String* | Unique identifier for the document to be uploaded. |
| **setFileName** | **X** | *String* | The document filename including extension. This is important as it is used to help identify the document MIME type. |
| **setFileData** |**X**| *Base64* |The document encoded in Base64 format.| |

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:
**DocumentRefAlreadyExistsException**, **DocumentContentTypeNotFoundException**, **InternalServerException**.
You can find more details on these faults [here](#section5).

###SaveFaxDocument request
```java
	public static void saveFaxDocumentDocSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

##DeleteFaxDocument
###Description

This function removes a saved fax document from the system.

###Request
**DeleteFaxDocumentRequestE methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setDeleteFaxDocumentRequest** | **X** | *DeleteFaxDocumentRequest* | DeleteFaxDocumentRequest to be sent. |


**DeleteFaxDocumentRequest methods:**

| **Name** | **Required** | **Type** | **Description** |
|--- | --- | --- | --- | ---|
| **setDocumentRef** | **X** | *String* | Unique identifier for the document to be deleted. |

###SOAP Faults
This function will throw one of the following SOAP faults/exceptions if something went wrong:
**DocumentRefDoesNotExistException**, **InternalServerException**.
You can find more details on these faults [here](#section5).

###DeleteFaxDocument request
```java
	public static void deleteFaxDocumentSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
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
```

<a name="section5"></a> 
#More Information
##Exceptions/SOAP Faults
If an error occurs during a request on the Monopond Fax API the service will throw a SOAP fault or exception. Each exception is listed in detail below. 
###InvalidArgumentsException
One or more of the arguments passed in the request were invalid. Each element that failed validation is included in the fault details along with the reason for failure.
###DocumentContentTypeNotFoundException
There was an error while decoding the document provided; we were unable to determine its content type.
###DocumentRefAlreadyExistsException
There is already a document on your account with this DocumentRef.
###DocumentContentTypeNotFoundException
Content type could not be found for the document.
###NoMessagesFoundException
Based on the references sent in the request no messages could be found that match the criteria.
###InternalServerException
An unusual error occurred on the platform. If this error occurs please contact support for further instruction.

##General Parameters and File Formatting
###File Encoding
All files are encoded in the Base64 encoding specified in RFC 2045 - MIME (Multipurpose Internet Mail Extensions). The Base64 encoding is designed to represent arbitrary sequences of octets in a form that need not be humanly readable. A 65-character subset ([A-Za-z0-9+/=]) of US-ASCII is used, enabling 6 bits to be represented per printable character. For more information see http://tools.ietf.org/html/rfc2045 and http://en.wikipedia.org/wiki/Base64

###Dates
Dates are always passed in ISO-8601 format with time zone. For example: “2012-07-17T19:27:23+08:00”

## List of Supported font names for StampMergeField TextValue
```
Andale-Mono-Regular
Arial-Black-Regular
Arial-Bold
Arial-Bold-Italic
Arial-Italic
Arial-Regular
AvantGarde-Book
AvantGarde-BookOblique
AvantGarde-Demi
AvantGarde-DemiOblique
Bitstream-Charter-Bold
Bitstream-Charter-Bold-Italic
Bitstream-Charter-Italic
Bitstream-Charter-Regular
Bitstream-Vera-Sans-Bold
Bitstream-Vera-Sans-Bold-Oblique
Bitstream-Vera-Sans-Mono-Bold
Bitstream-Vera-Sans-Mono-Bold-Oblique
Bitstream-Vera-Sans-Mono-Oblique
Bitstream-Vera-Sans-Mono-Roman
Bitstream-Vera-Sans-Oblique
Bitstream-Vera-Sans-Roman
Bitstream-Vera-Serif-Bold
Bitstream-Vera-Serif-Roman
Bookman-Demi
Bookman-DemiItalic
Bookman-Light
Bookman-LightItalic
Century-Schoolbook-Bold
Century-Schoolbook-Bold-Italic
Century-Schoolbook-Italic
Century-Schoolbook-Roman
Comic-Sans-MS-Bold
Comic-Sans-MS-Regular
Courier
Courier-Bold
Courier-BoldOblique
Courier-New-Bold
Courier-New-Bold-Italic
Courier-New-Italic
Courier-New-Regular
Courier-Oblique
Dingbats-Regular
Georgia-Bold
Georgia-Bold-Italic
Georgia-Italic
Georgia-Regular
Helvetica
Helvetica-Bold
Helvetica-BoldOblique
Helvetica-Narrow
Helvetica-Narrow-Bold
Helvetica-Narrow-BoldOblique
Helvetica-Narrow-Oblique
Helvetica-Oblique
Impact-Regular
NewCenturySchlbk-Bold
NewCenturySchlbk-BoldItalic
NewCenturySchlbk-Italic
NewCenturySchlbk-Roman
Nimbus-Mono-Bold
Nimbus-Mono-Bold-Oblique
Nimbus-Mono-Regular
Nimbus-Mono-Regular-Oblique
Nimbus-Roman-No9-Bold
Nimbus-Roman-No9-Bold-Italic
Nimbus-Roman-No9-Regular
Nimbus-Roman-No9-Regular-Italic
Nimbus-Sans-Bold
Nimbus-Sans-Bold-Italic
Nimbus-Sans-Condensed-Bold
Nimbus-Sans-Condensed-Bold-Italic
Nimbus-Sans-Condensed-Regular
Nimbus-Sans-Condensed-Regular-Italic
Nimbus-Sans-Regular
Nimbus-Sans-Regular-Italic
Palatino-Bold
Palatino-BoldItalic
Palatino-Italic
Palatino-Roman
Standard-Symbols-Regular
Symbol
Tahoma-Regular
Times-Bold
Times-BoldItalic
Times-Italic
Times-New-Roman-Bold
Times-New-Roman-Bold-Italic
Times-New-Roman-Italic
Times-New-Roman-Regular
Times-Roman
Trebuchet-MS-Bold
Trebuchet-MS-Bold-Italic
Trebuchet-MS-Italic
Trebuchet-MS-Regular
URW-Bookman-Demi-Bold
URW-Bookman-Demi-Bold-Italic
URW-Bookman-Light
URW-Bookman-Light-Italic
URW-Chancery-Medium-Italic
URW-Gothic-Book
URW-Gothic-Book-Oblique
URW-Gothic-Demi
URW-Gothic-Demi-Oblique
URW-Palladio-Bold
URW-Palladio-Bold-Italic
URW-Palladio-Italic
URW-Palladio-Roman
Utopia-Bold
Utopia-Bold-Italic
Utopia-Italic
Utopia-Regular
Verdana-Bold
Verdana-Bold-Italic
Verdana-Italic
Verdana-Regular
Webdings-Regular
```

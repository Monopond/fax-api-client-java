
//prod
import com.monopond.api.fax.soap.v2.ApiServiceStub;
import com.monopond.api.fax.soap.v2.ApiServiceStub.*;

import org.apache.axiom.om.OMAbstractFactory;

public class Client {

    public static void main(String[] args) throws Exception {

        ApiServiceStub apiServiceStub = new ApiServiceStub();

        ApiFaxDocument apiFaxDocument = new ApiFaxDocument();

        apiFaxDocument.setFileData("VGhpcyBpcyBhIGZheA==");
        apiFaxDocument.setFileName("test.txt");

        Documents_type0 documentsType0 = new Documents_type0();
        documentsType0.addDocument(apiFaxDocument);

        ApiFaxMessage apiFaxMessage = new ApiFaxMessage();
        apiFaxMessage.setMessageRef("test-1-1-1");
        apiFaxMessage.setSendTo("6011111111");
        apiFaxMessage.setSendFrom("Test Fax");
        apiFaxMessage.setResolution(FaxResolution.normal);
        apiFaxMessage.setDocuments(documentsType0);

        FaxMessages_type1 faxMessagesType1 = new FaxMessages_type1();
        faxMessagesType1.addFaxMessage(apiFaxMessage);

        SendFaxRequest sendFaxRequest = new SendFaxRequest();
        sendFaxRequest.setFaxMessages(faxMessagesType1);

        SendFaxRequestE sendFaxRequestE = new SendFaxRequestE();
        sendFaxRequestE.setSendFaxRequest(sendFaxRequest);

//        apiServiceStub.faxStatus();
//        apiServiceStub.pauseFax();
//        apiServiceStub.resumeFax();
//        apiServiceStub.stopFax();
        SendFaxResponseE response = apiServiceStub.sendFax("timtest", "gnome4life", sendFaxRequestE);

        System.out.println(response.getOMElement(null, OMAbstractFactory.getOMFactory()).toStringWithConsume());
    }
}

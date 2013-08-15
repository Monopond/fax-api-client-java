
//prod
import com.monopond.api.fax.soap.v2.ApiServiceStub;
import com.monopond.api.fax.soap.v2.ApiServiceStub.*;

import org.apache.axiom.om.OMAbstractFactory;

public class Client {

    public static void main(String[] args) throws Exception {

        //create a new apiServiceStub.
        ApiServiceStub apiServiceStub = new ApiServiceStub();

        //TODO: change user credentials
        String username = "username";
        String password = "password";

        //examples
        sendFaxSample(apiServiceStub, username, password);
        faxStatusSample(apiServiceStub, username, password);
        stopFaxSample(apiServiceStub, username, password);
        pauseFaxSample(apiServiceStub, username, password);
        resumeFaxSample(apiServiceStub, username, password);
    }

    public static void resumeFaxSample (ApiServiceStub apiServiceStub, String username, String password) throws Exception{
        //create a new resumeFax request.
        ResumeFaxRequest resumeFaxRequest = new ResumeFaxRequest();
        resumeFaxRequest.setBroadcastRef("broadcast-ref-1");

        //add the resumeFaxRequest to the resumeFaxRequest wrapper.
        ResumeFaxRequestE resumeFaxRequestE = new ResumeFaxRequestE();
        resumeFaxRequestE.setResumeFaxRequest(resumeFaxRequest);

        //call the resumeFax method.
        ResumeFaxResponseE resumeFaxResponseE = apiServiceStub.resumeFax(username, password, resumeFaxRequestE);
    }

    public static void pauseFaxSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
        //create a new pauseFax request.
        PauseFaxRequest pauseFaxRequest = new PauseFaxRequest();
        pauseFaxRequest.setBroadcastRef("broadcast-ref-1");

        //add the pauseFaxRequest to the PauseFaxRequest wrapper.
        PauseFaxRequestE pauseFaxRequestE = new PauseFaxRequestE();
        pauseFaxRequestE.setPauseFaxRequest(pauseFaxRequest);

        //call the pauseFax method.
        PauseFaxResponseE pauseFaxResponseE = apiServiceStub.pauseFax(username, password, pauseFaxRequestE);
    }

    public static void stopFaxSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
        //create a new stopFax request.
        StopFaxRequest stopFaxRequest = new StopFaxRequest();
        stopFaxRequest.setBroadcastRef("broadcast-ref-1");

        //add the stopFaxRequest to the StopFaxRequest wrapper.
        StopFaxRequestE stopFaxRequestE = new StopFaxRequestE();
        stopFaxRequestE.setStopFaxRequest(stopFaxRequest);

        //call the stopFax method.
        StopFaxResponseE stopFaxResponse = apiServiceStub.stopFax(username, password,stopFaxRequestE);
    }

    public static void faxStatusSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception{
        //create a new faxStatus request.
        FaxStatusRequest faxStatusRequest = new FaxStatusRequest();
        faxStatusRequest.setBroadcastRef("broadcast-ref-1");
        faxStatusRequest.setVerbosity(FaxStatusLevel.brief);

        //add the faxStatusRequest to faxStatusRequest wrapper.
        FaxStatusRequestE faxStatusRequestE = new FaxStatusRequestE();
        faxStatusRequestE.setFaxStatusRequest(faxStatusRequest);

        //call the FaxStatus method.
        FaxStatusResponseE faxStatusResponse = apiServiceStub.faxStatus(username, password, faxStatusRequestE);
    }

    public static void sendFaxSample(ApiServiceStub apiServiceStub, String username, String password) throws Exception {
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

        //call the sendFax method.
        SendFaxResponseE sendFaxResponse = apiServiceStub.sendFax(username, password, sendFaxRequestE);
        System.out.println(sendFaxResponse.getOMElement(null, OMAbstractFactory.getOMFactory()).toStringWithConsume());
    }
}

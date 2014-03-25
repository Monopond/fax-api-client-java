
/**
 * UnsupportedDocumentContentTypeException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.monopond.api.fax.soap.v2_1;

public class UnsupportedDocumentContentTypeException extends java.lang.Exception{

    private static final long serialVersionUID = 1392711448815L;
    
    private com.monopond.api.fax.soap.v2_1.ApiServiceStub.UnsupportedDocumentContentTypeE faultMessage;

    
        public UnsupportedDocumentContentTypeException() {
            super("UnsupportedDocumentContentTypeException");
        }

        public UnsupportedDocumentContentTypeException(java.lang.String s) {
           super(s);
        }

        public UnsupportedDocumentContentTypeException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public UnsupportedDocumentContentTypeException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.monopond.api.fax.soap.v2_1.ApiServiceStub.UnsupportedDocumentContentTypeE msg){
       faultMessage = msg;
    }
    
    public com.monopond.api.fax.soap.v2_1.ApiServiceStub.UnsupportedDocumentContentTypeE getFaultMessage(){
       return faultMessage;
    }
}
    
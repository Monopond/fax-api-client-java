
/**
 * DocumentRefDoesNotExistExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.monopond.api.fax.soap.v2_1;

public class DocumentRefDoesNotExistExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1392711448891L;
    
    private com.monopond.api.fax.soap.v2_1.ApiServiceStub.DocumentRefDoesNotExistException faultMessage;

    
        public DocumentRefDoesNotExistExceptionException() {
            super("DocumentRefDoesNotExistExceptionException");
        }

        public DocumentRefDoesNotExistExceptionException(java.lang.String s) {
           super(s);
        }

        public DocumentRefDoesNotExistExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public DocumentRefDoesNotExistExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.monopond.api.fax.soap.v2_1.ApiServiceStub.DocumentRefDoesNotExistException msg){
       faultMessage = msg;
    }
    
    public com.monopond.api.fax.soap.v2_1.ApiServiceStub.DocumentRefDoesNotExistException getFaultMessage(){
       return faultMessage;
    }
}
    

/**
 * InternalServerExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.monopond.api.fax.soap.v2;

public class InternalServerExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1375778949745L;
    
    private com.monopond.api.fax.soap.v2.ApiServiceStub.InternalServerException faultMessage;

    
        public InternalServerExceptionException() {
            super("InternalServerExceptionException");
        }

        public InternalServerExceptionException(java.lang.String s) {
           super(s);
        }

        public InternalServerExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InternalServerExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.monopond.api.fax.soap.v2.ApiServiceStub.InternalServerException msg){
       faultMessage = msg;
    }
    
    public com.monopond.api.fax.soap.v2.ApiServiceStub.InternalServerException getFaultMessage(){
       return faultMessage;
    }
}
    
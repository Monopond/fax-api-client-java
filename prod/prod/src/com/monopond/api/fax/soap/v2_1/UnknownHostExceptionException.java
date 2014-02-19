
/**
 * UnknownHostExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.monopond.api.fax.soap.v2_1;

public class UnknownHostExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1392711448875L;
    
    private com.monopond.api.fax.soap.v2_1.ApiServiceStub.UnknownHostExceptionE faultMessage;

    
        public UnknownHostExceptionException() {
            super("UnknownHostExceptionException");
        }

        public UnknownHostExceptionException(java.lang.String s) {
           super(s);
        }

        public UnknownHostExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public UnknownHostExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.monopond.api.fax.soap.v2_1.ApiServiceStub.UnknownHostExceptionE msg){
       faultMessage = msg;
    }
    
    public com.monopond.api.fax.soap.v2_1.ApiServiceStub.UnknownHostExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    
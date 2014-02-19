
/**
 * InterruptedExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.monopond.api.fax.soap.v2_1;

public class InterruptedExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1392711448902L;
    
    private com.monopond.api.fax.soap.v2_1.ApiServiceStub.InterruptedExceptionE faultMessage;

    
        public InterruptedExceptionException() {
            super("InterruptedExceptionException");
        }

        public InterruptedExceptionException(java.lang.String s) {
           super(s);
        }

        public InterruptedExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InterruptedExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.monopond.api.fax.soap.v2_1.ApiServiceStub.InterruptedExceptionE msg){
       faultMessage = msg;
    }
    
    public com.monopond.api.fax.soap.v2_1.ApiServiceStub.InterruptedExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    
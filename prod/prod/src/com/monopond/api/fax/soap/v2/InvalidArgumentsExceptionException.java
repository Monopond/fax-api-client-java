
/**
 * InvalidArgumentsExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.monopond.api.fax.soap.v2;

public class InvalidArgumentsExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1375778949854L;
    
    private com.monopond.api.fax.soap.v2.ApiServiceStub.InvalidArgumentsException faultMessage;

    
        public InvalidArgumentsExceptionException() {
            super("InvalidArgumentsExceptionException");
        }

        public InvalidArgumentsExceptionException(java.lang.String s) {
           super(s);
        }

        public InvalidArgumentsExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InvalidArgumentsExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.monopond.api.fax.soap.v2.ApiServiceStub.InvalidArgumentsException msg){
       faultMessage = msg;
    }
    
    public com.monopond.api.fax.soap.v2.ApiServiceStub.InvalidArgumentsException getFaultMessage(){
       return faultMessage;
    }
}
    
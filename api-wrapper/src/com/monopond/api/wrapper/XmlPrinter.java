package com.monopond.api.wrapper;

import java.io.PrintStream;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axis2.databinding.ADBBean;

import com.monopond.api.wrapper.translator.Translator;

public class XmlPrinter {
	private PrintStream printer;
	
	public XmlPrinter(PrintStream printer) {
		this.printer = printer;
	}
	
	private void toXml(ADBBean adbBean) throws FaxApiException {
		try {
			String xml = adbBean.getOMElement(null, OMAbstractFactory.getOMFactory())
					.toStringWithConsume();
			printer.println(xml);
		} catch (XMLStreamException e) {
			throw new FaxApiException("Failed printing XML.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void print(@SuppressWarnings("rawtypes") Translator translator,
						Object object) throws FaxApiException {
		ADBBean apiObject = null;
		try {
			apiObject = (ADBBean) translator.toApi(object);		
			toXml(apiObject);
		} catch (ClassCastException e) {
			String message = String.format("Unable to print [%s] object. Translated from class [%s]",
					apiObject == null? "null" : apiObject.getClass().getName(),
					object == null? "null": object.getClass().getName());
			throw new FaxApiException(message, e);
		}
	}
}

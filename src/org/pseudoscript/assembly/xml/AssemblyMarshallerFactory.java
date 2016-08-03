package org.pseudoscript.assembly.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.assembly.xml.jaxb.Assembly;

class AssemblyMarshallerFactory {

	private static final Logger LOGGER = Logger.getLogger(
			AssemblyMarshallerFactory.class.getSimpleName());
	
	private static Marshaller marshaller;
	
	private static Unmarshaller unmarshaller;
	
	public static Marshaller getMarshaller() {
		if (marshaller == null) {
			initialize();
		}
		return marshaller;
	}
	
	public static Unmarshaller getUnmarshaller() {
		if (unmarshaller == null) {
			initialize();
		}
		return unmarshaller;
	}
	
	private static void initialize() {
		try {
			JAXBContext context = JAXBContext.newInstance(Assembly.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException ex) {
			LOGGER.error("Initialize JAXB failed.", ex);
		}
	}
	
}

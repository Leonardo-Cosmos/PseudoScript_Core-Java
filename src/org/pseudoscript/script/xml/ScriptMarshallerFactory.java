package org.pseudoscript.script.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.script.xml.jaxb.Script;

class ScriptMarshallerFactory {

	private static final Logger LOGGER = Logger.getLogger(
			ScriptMarshallerFactory.class.getSimpleName());

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
			JAXBContext context = JAXBContext.newInstance(Script.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException ex) {
			LOGGER.error("Initialize JAXB failed.", ex);
		}
	}
}

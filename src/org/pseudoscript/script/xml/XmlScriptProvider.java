package org.pseudoscript.script.xml;

import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.script.Script;
import org.pseudoscript.script.ScriptProvider;

public class XmlScriptProvider extends ScriptProvider {

	private static final Logger LOGGER = Logger.getLogger(
			XmlScriptProvider.class.getSimpleName());
	
	private Marshaller marshaller;
	
	private Writer writer;
	
	public XmlScriptProvider() {
		marshaller = ScriptMarshallerFactory.getMarshaller();
	}
	
	@Override
	public void provide(Script script) throws IOException {
		org.pseudoscript.script.xml.jaxb.Script jaxbScript = 
				new org.pseudoscript.script.xml.jaxb.Script();
		
		try {
			marshaller.marshal(jaxbScript, writer);
		} catch (JAXBException ex) {
			LOGGER.error("Marshall Script XML failed.", ex);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public void setOutput(Writer writer) {
		this.writer = writer;
	}
	
}

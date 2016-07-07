package org.pseudoscript.script.xml;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.script.Script;
import org.pseudoscript.script.ScriptConsumer;
import org.pseudoscript.script.ScriptImpl;

public class XmlScriptConsumer extends ScriptConsumer {

	private static final Logger LOGGER = Logger.getLogger(
			XmlScriptConsumer.class.getSimpleName());
	
	private Unmarshaller unmarshaller;
	
	private Reader reader;
	
	public XmlScriptConsumer() {
		unmarshaller = ScriptMarshallerFactory.getUnmarshaller();
	}
	
	@Override
	public Script consume() throws IOException {
		try {
			org.pseudoscript.script.xml.jaxb.Script jaxbScript = 
					(org.pseudoscript.script.xml.jaxb.Script) unmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			LOGGER.error("Unmarshall Script XML failed.", ex);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		Script script = new ScriptImpl();
		
		return script;
	}
	
	public void setInput(Reader reader) {
		this.reader = reader;
	}

}

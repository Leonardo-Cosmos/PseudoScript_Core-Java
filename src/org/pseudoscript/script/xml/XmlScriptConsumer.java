package org.pseudoscript.script.xml;

import javax.xml.bind.Unmarshaller;

import org.pseudoscript.script.Script;
import org.pseudoscript.script.ScriptConsumer;

public class XmlScriptConsumer extends ScriptConsumer {

	private Unmarshaller unmarshaller;
	
	public XmlScriptConsumer() {
		unmarshaller = ScriptMarshallerFactory.getUnmarshaller();
	}
	
	@Override
	public Script consume(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}

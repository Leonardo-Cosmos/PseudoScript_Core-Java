package org.pseudoscript.script.xml;

import javax.xml.bind.Marshaller;

import org.pseudoscript.script.Script;
import org.pseudoscript.script.ScriptProvider;

public class XmlScriptProvider extends ScriptProvider {

	private Marshaller marshaller;
	
	public XmlScriptProvider() {
		marshaller = ScriptMarshallerFactory.getMarshaller();
	}
	
	@Override
	public String provide(Script script) {
		// TODO Auto-generated method stub
		return null;
	}

}

package org.pseudoscript.script.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.script.ArgumentInfo;
import org.pseudoscript.script.ReferredArgumentInfo;
import org.pseudoscript.script.ReferredArgumentInfoImpl;
import org.pseudoscript.script.ReferredDataSource;
import org.pseudoscript.script.ReferredDataSourceImpl;
import org.pseudoscript.script.OperationInfo;
import org.pseudoscript.script.OperationInfoImpl;
import org.pseudoscript.script.Script;
import org.pseudoscript.script.ScriptConsumer;
import org.pseudoscript.script.ScriptImpl;
import org.pseudoscript.script.SimpleArgumentInfo;
import org.pseudoscript.script.SimpleArgumentInfoImpl;

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
		org.pseudoscript.script.xml.jaxb.Script xmlScript = null;
		try {
			xmlScript = 
					(org.pseudoscript.script.xml.jaxb.Script) unmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			LOGGER.error("Unmarshall Script XML failed.");
			throw new IOException("Unmarshall Script XML failed.", ex);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		Script script = new ScriptImpl();
		loadFromXml(script, xmlScript);
		return script;
	}
	
	public void setInput(Reader reader) {
		this.reader = reader;
	}
	
	protected void loadFromXml(Script script, org.pseudoscript.script.xml.jaxb.Script xmlScript) {
		if (script == null || xmlScript == null) {
			return;
		}
		
		List<org.pseudoscript.script.xml.jaxb.DataSource> xmlDataSources = 
				xmlScript.getDataSources().getDataSource();
		for (org.pseudoscript.script.xml.jaxb.DataSource xmlDataSource : xmlDataSources) {
			ReferredDataSource referredDataSource = new ReferredDataSourceImpl();
			referredDataSource.setId(xmlDataSource.getId());
			referredDataSource.setBase(xmlDataSource.getBase());
			referredDataSource.setKey(xmlDataSource.getKey());
			script.getDataSources().put(xmlDataSource.getId(), referredDataSource);
		}
		
		List<org.pseudoscript.script.xml.jaxb.Operation> xmlOperations = 
				xmlScript.getOperations().getOperation();
		for (org.pseudoscript.script.xml.jaxb.Operation xmlOperation : xmlOperations) {
			OperationInfo operationInfo = new OperationInfoImpl();
			operationInfo.setExecutor(xmlOperation.getExecutor());
			operationInfo.setName(xmlOperation.getName());
			
			List<org.pseudoscript.script.xml.jaxb.Argument> xmlArguments = 
					xmlOperation.getArguments().getArgument();
			for (org.pseudoscript.script.xml.jaxb.Argument xmlArgument : xmlArguments) {
				ArgumentInfo argumentInfo;
				if (xmlArgument.getSimpleData() != null) {
					org.pseudoscript.script.xml.jaxb.SimpleData xmlSimpleData = 
							xmlArgument.getSimpleData();
					SimpleArgumentInfo simpleArgumentInfo = new SimpleArgumentInfoImpl();
					simpleArgumentInfo.setValue(xmlSimpleData.getValue());
					argumentInfo = simpleArgumentInfo;
				} else if (xmlArgument.getReferredData() != null) {
					org.pseudoscript.script.xml.jaxb.ReferredData xmlReferredData = 
							xmlArgument.getReferredData(); 
					ReferredArgumentInfo referredArgumentInfo = new ReferredArgumentInfoImpl();
					referredArgumentInfo.setDataSource(script.getDataSources().get(
							xmlReferredData.getDataSource()));
					referredArgumentInfo.setKey(xmlReferredData.getKey());
					argumentInfo = referredArgumentInfo;
				} else {
					org.pseudoscript.script.xml.jaxb.SimpleData xmlSimpleData = 
							xmlArgument.getSimpleData();
					SimpleArgumentInfo simpleArgumentInfo = new SimpleArgumentInfoImpl();
					simpleArgumentInfo.setValue(xmlSimpleData.getValue());
					argumentInfo = simpleArgumentInfo;
				}
				
				argumentInfo.setName(xmlArgument.getName());
				argumentInfo.setType(xmlArgument.getType());
				
				operationInfo.getArguments().add(argumentInfo);
			}
			
			script.getOperations().add(operationInfo);
		}
	}
	
}

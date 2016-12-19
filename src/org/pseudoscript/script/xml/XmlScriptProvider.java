package org.pseudoscript.script.xml;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.script.ArgumentInfo;
import org.pseudoscript.script.ReferredArgumentInfo;
import org.pseudoscript.script.ReferredDataSource;
import org.pseudoscript.script.OperationInfo;
import org.pseudoscript.script.Script;
import org.pseudoscript.script.ScriptProvider;
import org.pseudoscript.script.SimpleArgumentInfo;

public class XmlScriptProvider extends ScriptProvider {

	private static final Logger LOGGER = Logger.getLogger(
			XmlScriptProvider.class.getSimpleName());
	
	private final Marshaller marshaller;
	
	protected final org.pseudoscript.script.xml.jaxb.ObjectFactory objectFactory = 
			new org.pseudoscript.script.xml.jaxb.ObjectFactory();
	
	private Writer writer;
	
	public XmlScriptProvider() {
		marshaller = ScriptMarshallerFactory.getMarshaller();
	}
	
	@Override
	public void provide(Script script) throws IOException {
		org.pseudoscript.script.xml.jaxb.Script xmlScript = 
				new org.pseudoscript.script.xml.jaxb.Script();
		saveToXml(script, xmlScript);
		
		try {
			marshaller.marshal(xmlScript, writer);
		} catch (JAXBException ex) {
			LOGGER.error("Marshall Script XML failed.");
			throw new IOException("Marshall Script XML failed.", ex);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public void setOutput(Writer writer) {
		this.writer = writer;
	}
	
	protected void saveToXml(Script script, org.pseudoscript.script.xml.jaxb.Script xmlScript) {
		if (script == null || xmlScript == null) {
			return;
		}
		
		Map<String, ReferredDataSource> dataSources = script.getDataSources();
		for (Entry<String, ReferredDataSource> entry : dataSources.entrySet()) {
			ReferredDataSource referredDataSource = entry.getValue();
			org.pseudoscript.script.xml.jaxb.DataSource xmlDataSource = 
					new org.pseudoscript.script.xml.jaxb.DataSource();
			xmlDataSource.setId(referredDataSource.getId());
			xmlDataSource.setBase(referredDataSource.getBase());
			xmlDataSource.setKey(referredDataSource.getKey());
			xmlScript.getDataSources().getDataSource().add(xmlDataSource);
		}
		
		List<OperationInfo> operations = script.getOperations();
		for (OperationInfo operationInfo : operations) {
			org.pseudoscript.script.xml.jaxb.Operation  xmlOperation = 
					new org.pseudoscript.script.xml.jaxb.Operation();
			xmlOperation.setExecutor(operationInfo.getExecutor());
			xmlOperation.setName(operationInfo.getName());
			
			List<ArgumentInfo> arguments = operationInfo.getArguments();
			for (ArgumentInfo argument : arguments) {
				org.pseudoscript.script.xml.jaxb.Argument xmlArgument = 
						new org.pseudoscript.script.xml.jaxb.Argument();
				if (argument instanceof ReferredArgumentInfo) {
					org.pseudoscript.script.xml.jaxb.ReferredData xmlReferredData = 
							new org.pseudoscript.script.xml.jaxb.ReferredData();
					ReferredArgumentInfo referredArgument = (ReferredArgumentInfo) argument;
					ReferredDataSource dataSource = referredArgument.getDataSource();
					for (Entry<String, ReferredDataSource> entry : script.getDataSources().entrySet()) {
						if (dataSource == entry.getValue()) {
							xmlReferredData.setDataSource(entry.getKey());
							break;
						}
					}
					xmlReferredData.setKey(referredArgument.getKey());
				} else {
					org.pseudoscript.script.xml.jaxb.SimpleData xmlSimpleData = 
							new org.pseudoscript.script.xml.jaxb.SimpleData();
					SimpleArgumentInfo simpleArgument = (SimpleArgumentInfo) argument;
					xmlSimpleData.setValue(simpleArgument.getValue().toString());
				}
				
				xmlArgument.setName(argument.getName());
				xmlArgument.setType(argument.getType());
				
				xmlOperation.getArguments().getArgument().add(xmlArgument);
			}
			
			xmlScript.getOperations().getOperation().add(xmlOperation);
		}
	}
}

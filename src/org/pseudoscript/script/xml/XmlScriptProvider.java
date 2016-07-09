package org.pseudoscript.script.xml;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.assembly.ArgumentInfo;
import org.pseudoscript.assembly.DataSourceArgumentInfo;
import org.pseudoscript.assembly.OperationInfo;
import org.pseudoscript.data.DataSource;
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
		org.pseudoscript.script.xml.jaxb.Script xmlScript = 
				new org.pseudoscript.script.xml.jaxb.Script();
		saveToXml(script, xmlScript);
		
		try {
			marshaller.marshal(xmlScript, writer);
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
	
	protected void saveToXml(Script script, org.pseudoscript.script.xml.jaxb.Script xmlScript) {
		if (script == null || xmlScript == null) {
			return;
		}
		
		Map<String, DataSource> dataSources = script.getDataSources();
		for (Entry<String, DataSource> entry : dataSources.entrySet()) {
			org.pseudoscript.script.xml.jaxb.DataSource xmlDataSource = 
					new org.pseudoscript.script.xml.jaxb.DataSource();
			xmlDataSource.setId(entry.getKey());
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
				if (argument instanceof DataSourceArgumentInfo) {
					org.pseudoscript.script.xml.jaxb.ReferredData xmlReferredData = 
							new org.pseudoscript.script.xml.jaxb.ReferredData();
					DataSourceArgumentInfo dataSourceArgument = (DataSourceArgumentInfo) argument;
					DataSource dataSource = dataSourceArgument.getDataSource();
					for (Entry<String, DataSource> entry : environmentDataSources.entrySet()) {
						if (dataSource == entry.getValue()) {
							xmlReferredData.setDataSource(entry.getKey());
							break;
						}
					}
					xmlReferredData.setKey(dataSourceArgument.getKey());
				} else {
					org.pseudoscript.script.xml.jaxb.SimpleData xmlSimpleData = 
							new org.pseudoscript.script.xml.jaxb.SimpleData();
					xmlSimpleData.setValue(argument.getValue().toString());
				}
				
				xmlArgument.setName(argument.getName());
				xmlArgument.setType(argument.getType());
				
				xmlOperation.getArguments().getArgument().add(xmlArgument);
			}
			
			xmlScript.getOperations().getOperation().add(xmlOperation);
		}
	}
}

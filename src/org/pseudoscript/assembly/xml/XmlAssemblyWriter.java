package org.pseudoscript.assembly.xml;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.assembly.Assembly;
import org.pseudoscript.assembly.AssemblyWriter;
import org.pseudoscript.assembly.OperationDef;
import org.pseudoscript.assembly.ParameterDef;

public class XmlAssemblyWriter extends AssemblyWriter {

	private static final Logger LOGGER = Logger.getLogger(
			AssemblyWriter.class.getSimpleName());
	
	protected final Marshaller marshaller = AssemblyMarshallerFactory.getMarshaller();
	
	protected final org.pseudoscript.assembly.xml.jaxb.ObjectFactory objectFactory = 
			new org.pseudoscript.assembly.xml.jaxb.ObjectFactory();
	
	@Override
	public void write(Assembly assembly, Writer writer) throws IOException {
		org.pseudoscript.assembly.xml.jaxb.Assembly xmlAssembly = objectFactory.createAssembly();
		writeToXml(assembly, xmlAssembly);
		
		try {
			marshaller.marshal(xmlAssembly, writer);
		} catch (JAXBException ex) {
			LOGGER.error("Failed to marshaller Assembly XML.");
			throw new IOException("Failed to marshaller Assembly XML.", ex);
		}
	}
	
	protected void writeToXml(Assembly assembly, org.pseudoscript.assembly.xml.jaxb.Assembly xmlAssembly) {
		if (assembly == null || xmlAssembly == null) {
			return;
		}
		
		org.pseudoscript.assembly.xml.jaxb.Executor xmlExecutor = objectFactory.createExecutor();
		xmlExecutor.setName(assembly.getExecutor());
		xmlAssembly.setExecutor(xmlExecutor);
		
		xmlAssembly.setOperations(objectFactory.createAssemblyOperations());
		
		List<OperationDef> operations = assembly.getOperations();
		for (OperationDef operation : operations) {
			org.pseudoscript.assembly.xml.jaxb.Operation xmlOperation = objectFactory.createOperation();
			xmlOperation.setName(operation.getName());
			
			xmlOperation.setParameters(objectFactory.createOperationParameters());
			for (ParameterDef parameter : operation.getParameters()) {
				org.pseudoscript.assembly.xml.jaxb.Parameter xmlParameter = objectFactory.createParameter();
				xmlParameter.setName(parameter.getName());
				xmlParameter.setType(parameter.getType());
				xmlParameter.setDefaultValue(parameter.getDefaultValue().toString());
				
				xmlOperation.getParameters().getParameter().add(xmlParameter);
			}
			
			xmlAssembly.getOperations().getOperation().add(xmlOperation);
		}
	}

}

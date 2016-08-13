package org.pseudoscript.assembly.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.pseudoscript.assembly.Assembly;
import org.pseudoscript.assembly.AssemblyImpl;
import org.pseudoscript.assembly.AssemblyReader;
import org.pseudoscript.assembly.OperationDef;
import org.pseudoscript.assembly.OperationDefImpl;
import org.pseudoscript.assembly.ParameterDef;
import org.pseudoscript.assembly.ParameterDefImpl;

public class XmlAssemblyReader extends AssemblyReader {

	private static final Logger LOGGER = Logger.getLogger(
			XmlAssemblyReader.class.getSimpleName());
	
	protected final Unmarshaller unmarshaller = AssemblyMarshallerFactory.getUnmarshaller();
	
	protected final org.pseudoscript.assembly.xml.jaxb.ObjectFactory objectFactory =
			new org.pseudoscript.assembly.xml.jaxb.ObjectFactory();
	
	@Override
	public Assembly read(Reader reader) throws IOException {
		org.pseudoscript.assembly.xml.jaxb.Assembly xmlAssembly = null;
		try {
			xmlAssembly =
					(org.pseudoscript.assembly.xml.jaxb.Assembly) unmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			LOGGER.error("Failed to unmarshall Assembly XML.");
			throw new IOException("Failed to unmarshall Assembly XML.", ex);
		}
		
		Assembly assembly = new AssemblyImpl();
		readFromXml(assembly, xmlAssembly);
		return assembly;
	}

	protected void readFromXml(Assembly assembly, org.pseudoscript.assembly.xml.jaxb.Assembly xmlAssembly) {
		if (assembly == null || xmlAssembly == null) {
			 return;
		}
		
		org.pseudoscript.assembly.xml.jaxb.Executor xmlExecutor = xmlAssembly.getExecutor();
		assembly.setExecutor(xmlExecutor.getName());
		
		List<org.pseudoscript.assembly.xml.jaxb.Operation> xmlOperations = 
				xmlAssembly.getOperations().getOperation();
		for (org.pseudoscript.assembly.xml.jaxb.Operation xmlOperation : xmlOperations) {
			OperationDef operation = new OperationDefImpl();
			operation.setName(xmlOperation.getName());
			
			List<org.pseudoscript.assembly.xml.jaxb.Parameter> xmlParameters =
					xmlOperation.getParameters().getParameter();
			for (org.pseudoscript.assembly.xml.jaxb.Parameter xmlParameter : xmlParameters) {
				ParameterDef parameter = new ParameterDefImpl();
				parameter.setName(xmlParameter.getName());
				parameter.setType(xmlParameter.getType());
				parameter.setDefaultValue(xmlParameter.getDefaultValue());
				
				operation.getParameters().add(parameter);
			}
			
			assembly.getOperations().add(operation);
		}
	}
	
}

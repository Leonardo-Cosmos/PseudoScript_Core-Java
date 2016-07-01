package org.pseudoscript.operation;

import java.util.List;

public interface OperationDef {

	String getName();
	void setName(String name);
	
	String getExecutor();
	void setExecutor(String executor);
	
	List<ParameterDef> getParameters();
	void setParameters(List<ParameterDef> parameters);
	
}

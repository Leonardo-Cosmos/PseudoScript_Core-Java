package org.pseudoscript.assembly;

import java.util.List;

public interface OperationDef {

	String getName();
	void setName(String name);
	
	List<ParameterDef> getParameters();
	void setParameters(List<ParameterDef> parameters);
	
}

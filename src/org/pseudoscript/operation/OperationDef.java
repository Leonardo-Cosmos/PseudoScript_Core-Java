package org.pseudoscript.operation;

import java.util.List;

public interface OperationDef {

	public String getName();
	public void setName(String name);
	
	public String getExecutor();
	public void setExecutor(String executor);
	
	public List<ParameterDef> getParameters();
	public void setParameters(List<ParameterDef> parameters);
	
}

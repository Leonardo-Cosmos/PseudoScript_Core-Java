package org.pseudoscript.operation;

import java.util.List;

public interface OperationInfo {

	public String getName();
	public void setName(String name);
	
	public String getExecutor();
	public void setExecutor(String executor);
	
	public List<ArgumentInfo> getArguments();
	public void setArguments(List<ArgumentInfo> arguments);
	
}

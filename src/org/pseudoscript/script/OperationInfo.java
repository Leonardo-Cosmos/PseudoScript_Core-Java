package org.pseudoscript.script;

import java.util.List;

public interface OperationInfo {

	String getName();
	void setName(String name);
	
	String getExecutor();
	void setExecutor(String executor);
	
	List<ArgumentInfo> getArguments();
	void setArguments(List<ArgumentInfo> arguments);
	
}

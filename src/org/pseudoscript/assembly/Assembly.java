package org.pseudoscript.assembly;

import java.util.List;

public interface Assembly {

	String getExecutor();
	void setExecutor(String executor);
	
	List<OperationDef> getOperations();
	void setOperations(List<OperationDef> operations);
	
}

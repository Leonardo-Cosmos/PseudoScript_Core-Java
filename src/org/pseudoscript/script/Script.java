package org.pseudoscript.script;

import java.util.List;
import java.util.Map;

public interface Script {

	public Map<String, ReferredDataSource> getDataSources();

	public void setDataSources(Map<String, ReferredDataSource> dataSources);

	public List<OperationInfo> getOperations();

	public void setOperations(List<OperationInfo> operations);
	
}

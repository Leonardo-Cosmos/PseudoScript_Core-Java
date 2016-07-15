package org.pseudoscript.script;

import java.util.List;
import java.util.Map;

import org.pseudoscript.data.DataSource;

public interface Script {

	public Map<String, DataSource> getDataSources();

	public void setDataSources(Map<String, DataSource> dataSources);

	public List<OperationInfo> getOperations();

	public void setOperations(List<OperationInfo> operations);
	
}

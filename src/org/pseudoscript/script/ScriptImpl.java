package org.pseudoscript.script;

import java.util.List;
import java.util.Map;

import org.pseudoscript.assembly.OperationInfo;
import org.pseudoscript.data.DataSource;

public class ScriptImpl implements Script {

	private Map<String, DataSource> dataSources;
	
	private List<OperationInfo> operations;

	@Override
	public Map<String, DataSource> getDataSources() {
		return dataSources;
	}

	@Override
	public void setDataSources(Map<String, DataSource> dataSources) {
		this.dataSources = dataSources;
	}

	@Override
	public List<OperationInfo> getOperations() {
		return operations;
	}

	@Override
	public void setOperations(List<OperationInfo> operations) {
		this.operations = operations;
	}
	
}

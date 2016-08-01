package org.pseudoscript.script;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScriptImpl implements Script {

	private Map<String, ReferredDataSource> dataSources = new LinkedHashMap<>();
	
	private List<OperationInfo> operations = new ArrayList<>();

	@Override
	public Map<String, ReferredDataSource> getDataSources() {
		return dataSources;
	}

	@Override
	public void setDataSources(Map<String, ReferredDataSource> dataSources) {
		this.dataSources.clear();
		this.dataSources.putAll(dataSources);
	}

	@Override
	public List<OperationInfo> getOperations() {
		return operations;
	}

	@Override
	public void setOperations(List<OperationInfo> operations) {
		this.operations.clear();
		operations.addAll(operations);
	}
	
}

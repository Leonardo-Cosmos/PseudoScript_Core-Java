package org.pseudoscript.operation;

import java.util.ArrayList;
import java.util.List;

public class OperationDefImpl implements OperationDef {

	private String name;
	private String executor;
	private final List<ParameterDef> parameters = new ArrayList<>();	
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getExecutor() {
		return executor;
	}
	@Override
	public void setExecutor(String executor) {
		this.executor = executor;
	}

	@Override
	public List<ParameterDef> getParameters() {
		return parameters;
	}
	@Override
	public void setParameters(List<ParameterDef> parameters) {
		this.parameters.clear();
		this.parameters.addAll(parameters);
	}

}

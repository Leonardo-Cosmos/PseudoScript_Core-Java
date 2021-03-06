package org.pseudoscript.assembly;

import java.util.ArrayList;
import java.util.List;

public class OperationDefImpl implements OperationDef {

	private String name;
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
	public List<ParameterDef> getParameters() {
		return parameters;
	}
	@Override
	public void setParameters(List<ParameterDef> parameters) {
		this.parameters.clear();
		this.parameters.addAll(parameters);
	}

}

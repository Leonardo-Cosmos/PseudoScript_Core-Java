package org.pseudoscript.assembly;

import java.util.ArrayList;
import java.util.List;

public class AssemblyImpl implements Assembly {

	private String executor;
	
	private List<OperationDef> operations = new ArrayList<>();
	
	@Override
	public String getExecutor() {
		return executor;
	}

	@Override
	public void setExecutor(String executor) {
		this.executor = executor;
	}

	@Override
	public List<OperationDef> getOperations() {
		return this.operations;
	}

	@Override
	public void setOperations(List<OperationDef> operations) {
		this.operations.clear();
		operations.addAll(operations);
	}

}

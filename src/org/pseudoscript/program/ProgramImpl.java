package org.pseudoscript.program;

import java.util.ArrayList;
import java.util.List;

public class ProgramImpl implements Program {

	private List<Operation> operations = new ArrayList<>();
	
	@Override
	public List<Operation> getOperations() {
		return operations;
	}

	@Override
	public void setOperations(List<Operation> operations) {
		this.operations.clear();
		this.operations.addAll(operations);
	}

	@Override
	public void execute() {
		for (Operation operation : operations) {
			operation.execute();
		}
	}

}

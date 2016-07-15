package org.pseudoscript.program;

import java.util.List;

public interface Program {

	List<Operation> getOperations();
	void setOperations(List<Operation> operations);
	
	void execute();
	
}

package org.pseudoscript.assembly;

public interface Operation {

	String getName();
	void setName(String name);
	
	ExecutorInfo getExecutor();
	void setExecutor(ExecutorInfo executor);
	
	Object[] getArguments();
	void setArguments(Object... arguments);
	
	void execute();
	
}

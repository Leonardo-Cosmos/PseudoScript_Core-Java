package org.pseudoscript.program;

public class OperationImpl implements Operation {

	private String name;
	private ExecutorInfo executor;
	
	private Object[] arguments;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ExecutorInfo getExecutor() {
		return executor;
	}

	@Override
	public void setExecutor(ExecutorInfo executor) {
		this.executor = executor;
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public void setArguments(Object... arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public void execute() {
		executor.execute(name, arguments);
	}

}

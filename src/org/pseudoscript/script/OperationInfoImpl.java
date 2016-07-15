package org.pseudoscript.script;

import java.util.ArrayList;
import java.util.List;

public class OperationInfoImpl implements OperationInfo {

	private String name;
	private String executor;
	private final List<ArgumentInfo> arguments = new ArrayList<>();
	
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
	public List<ArgumentInfo> getArguments() {
		return arguments;
	}
	@Override
	public void setArguments(List<ArgumentInfo> arguments) {
		this.arguments.clear();
		this.arguments.addAll(arguments);
	}
	
}

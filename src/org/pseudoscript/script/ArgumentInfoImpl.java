package org.pseudoscript.script;

public abstract class ArgumentInfoImpl implements ArgumentInfo {

	private String type;
	private String name;
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

}

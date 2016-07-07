package org.pseudoscript.assembly;

public class ParameterDefImpl implements ParameterDef {

	private String type;
	private String name;
	private Object defaultValue;
	
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

	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}
	@Override
	public void setDefaultValue(Object value) {
		this.defaultValue = value;
	}

}

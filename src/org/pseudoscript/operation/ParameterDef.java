package org.pseudoscript.operation;

public interface ParameterDef {

	String getType();
	void setType(String type);
	
	String getName();
	void setName(String name);
	
	Object getDefaultValue();
	void setDefaultValue(Object value);
	
}

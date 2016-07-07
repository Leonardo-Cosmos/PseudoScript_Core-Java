package org.pseudoscript.assembly;

public interface ParameterDef {

	String getType();
	void setType(String type);
	
	String getName();
	void setName(String name);
	
	Object getDefaultValue();
	void setDefaultValue(Object value);
	
}

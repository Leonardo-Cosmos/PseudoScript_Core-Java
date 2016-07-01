package org.pseudoscript.operation;

public interface ArgumentInfo {

	String getType();
	void setType(String type);
	
	String getName();
	void setName(String name);
	
	Object getValue();
	void setValue(Object value);
	
}

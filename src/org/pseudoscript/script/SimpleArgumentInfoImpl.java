package org.pseudoscript.script;

public class SimpleArgumentInfoImpl extends ArgumentInfoImpl implements SimpleArgumentInfo {

	private Object value;
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

}

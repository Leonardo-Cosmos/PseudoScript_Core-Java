package org.pseudoscript.assembly;

import java.lang.reflect.Method;
import java.util.Map;

public interface ExecutorInfo {

	Object getInstance();
	void setInstance(Object instance);
	
	Map<String, Method> getMethods();
	void setMethods(Map<String, Method> methods);
	
	void execute(String operationName, Object... arguments);
	
}

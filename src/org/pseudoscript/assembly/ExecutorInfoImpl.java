package org.pseudoscript.assembly;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ExecutorInfoImpl implements ExecutorInfo {

	private static final Logger LOGGER = Logger.getLogger(ExecutorInfoImpl.class.getSimpleName());
	
	private Object instance;
	private Map<String, Method> methods = new HashMap<>();
	
	@Override
	public Object getInstance() {
		return instance;
	}

	@Override
	public void setInstance(Object instance) {
		this.instance = instance;
	}

	@Override
	public Map<String, Method> getMethods() {
		return methods;
	}

	@Override
	public void setMethods(Map<String, Method> methods) {
		this.methods.clear();
		this.methods.putAll(methods);
	}

	@Override
	public void execute(String operationName, Object... arguments) {
		Method method = methods.get(operationName);
		if (method == null) {
			throw new IllegalArgumentException(
					"Executor doesn't contain operation \"" + operationName + "\".");
		}
		try {
			method.invoke(instance, arguments);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			LOGGER.error("Invoke method failed.", ex);
		}
	}

}

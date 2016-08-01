package org.pseudoscript.program.exception;

public class ExecutorNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2707499252312446683L;
	
	private String executor;
	
	public ExecutorNotFoundException() {
		
	}
	
	public ExecutorNotFoundException(String message) {
		super(message);
	}
	
	public ExecutorNotFoundException(String executor, String message) {
		super(message);
		this.executor = executor;
	}
	
	/**
	 * Returns name of executor that cannot be found.
	 * @return name of executor.
	 */
	public String getExecutor() {
		return executor;
	}
	
}

package org.pseudoscript.program.exception;

public class OperationNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1529251408609391878L;
	
	private String operation;
	
	public OperationNotFoundException() {
		
	}
	
	public OperationNotFoundException(String message) {
		super(message);
	}
	
	public OperationNotFoundException(String operation, String message) {
		super(message);
		this.operation = operation;
	}
	
	/**
	 * Returns name of operation that cannot be found.
	 * @return name of operation.
	 */
	public String getOperation() {
		return operation;
	}

}

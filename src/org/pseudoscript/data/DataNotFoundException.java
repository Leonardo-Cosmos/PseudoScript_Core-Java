package org.pseudoscript.data;

public class DataNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8800403572619568914L;

	private String key;	
	
	public DataNotFoundException() {
		
	}
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
	public DataNotFoundException(String key, String message) {
		super(message);
		this.key = key;
	}
	
}

package org.pseudoscript.program.exception;

public class DataSourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2266537818043627492L;
	
	private String dataSource;
	
	public DataSourceNotFoundException() {
		
	}
	
	public DataSourceNotFoundException(String message) {
		super(message);
	}
	
	public DataSourceNotFoundException(String dataSource, String message) {
		super(message);
		this.dataSource = dataSource;
	}
	
	/**
	 * Returns name of data source that cannot be found.
	 * @return name of data source.
	 */
	public String getDataSource() {
		return dataSource;
	}
	
}

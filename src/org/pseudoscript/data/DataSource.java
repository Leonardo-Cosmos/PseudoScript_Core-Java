package org.pseudoscript.data;

public interface DataSource {
	
	String SEPARATOR = ".";
	
	Object get(String key) throws DataNotFoundException, IllegalKeyException;
	
	void set(String key, Object value) throws IllegalKeyException;
	
	void load();
	
	void save();
	
}

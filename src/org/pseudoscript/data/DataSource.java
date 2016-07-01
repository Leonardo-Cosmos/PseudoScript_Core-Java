package org.pseudoscript.data;

import java.io.IOException;

public interface DataSource {
	
	String SEPARATOR = ".";
	
	Object get(String key) throws DataNotFoundException, IllegalKeyException;
	
	void set(String key, Object value) throws IllegalKeyException;
	
	void load() throws IOException;
	
	void save() throws IOException;
	
}

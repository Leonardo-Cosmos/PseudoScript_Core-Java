package org.pseudoscript.assembly;

import org.apache.log4j.Logger;
import org.pseudoscript.data.DataNotFoundException;
import org.pseudoscript.data.DataSource;
import org.pseudoscript.data.IllegalKeyException;
import org.pseudoscript.script.ArgumentInfoImpl;

public class DataSourceArgumentInfoImpl extends ArgumentInfoImpl implements DataSourceArgumentInfo {

	private static final Logger LOGGER = Logger.getLogger(DataSourceArgumentInfoImpl.class.getSimpleName());
	
	private DataSource dataSource;
	private String key;
	
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public Object getValue() {
		Object value = null;
		try {
			value = dataSource.get(key);
		} catch (IllegalKeyException | DataNotFoundException ex) {
			LOGGER.error("Failed to get value from data source.", ex);
		}
		return value;
	}
	
	@Override
	public void setValue(Object value) {
		try {
			dataSource.set(getKey(), value);
		} catch (IllegalKeyException ex) {
			LOGGER.error("Failed to set value to data source.", ex);
		}
	}
	
}

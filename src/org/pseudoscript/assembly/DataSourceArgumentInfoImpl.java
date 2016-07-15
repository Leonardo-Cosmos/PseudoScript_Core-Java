package org.pseudoscript.assembly;

import org.pseudoscript.data.DataSource;
import org.pseudoscript.script.ArgumentInfoImpl;

public class DataSourceArgumentInfoImpl extends ArgumentInfoImpl implements DataSourceArgumentInfo {

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

}

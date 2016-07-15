package org.pseudoscript.assembly;

import org.pseudoscript.data.DataSource;
import org.pseudoscript.script.ArgumentInfo;

public interface DataSourceArgumentInfo extends ArgumentInfo {

	DataSource getDataSource();
	void setDataSource(DataSource dataSource);
	
	String getKey();
	void setKey(String key);
	
}

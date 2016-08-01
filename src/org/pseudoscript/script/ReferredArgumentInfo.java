package org.pseudoscript.script;

public interface ReferredArgumentInfo extends ArgumentInfo {

	ReferredDataSource getDataSource();
	void setDataSource(ReferredDataSource dataSource);
	
	String getKey();
	void setKey(String key);
	
}

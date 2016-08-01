package org.pseudoscript.script;

public class ReferredArgumentInfoImpl extends ArgumentInfoImpl implements ReferredArgumentInfo {

	private ReferredDataSource dataSource;
	private String key;
	
	@Override
	public ReferredDataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(ReferredDataSource dataSource) {
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

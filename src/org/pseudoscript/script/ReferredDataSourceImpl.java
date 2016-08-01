package org.pseudoscript.script;

public class ReferredDataSourceImpl implements ReferredDataSource {

	private String id;
	private String base;
	private String key;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getBase() {
		return base;
	}

	@Override
	public void setBase(String base) {
		this.base = base;
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

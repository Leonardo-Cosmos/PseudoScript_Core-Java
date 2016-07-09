package org.pseudoscript.script;

import java.io.IOException;
import java.util.Map;

import org.pseudoscript.data.DataSource;

public abstract class ScriptConsumer {

	protected Map<String, DataSource> environmentDataSources;
	
	public abstract Script consume() throws IOException;

	public void setEnvironmentDataSources(Map<String, DataSource> dataSources) {
		this.environmentDataSources = dataSources;
	}
	
}

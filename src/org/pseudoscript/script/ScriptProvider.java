package org.pseudoscript.script;

import java.io.IOException;
import java.util.Map;

import org.pseudoscript.data.DataSource;

public abstract class ScriptProvider {

	protected Map<String, DataSource> environmentDataSources;
	
	public abstract void provide(Script script) throws IOException;
	
	public void setEnvironmentDataSources(Map<String, DataSource> dataSources) {
		this.environmentDataSources = dataSources;
	}
	
}

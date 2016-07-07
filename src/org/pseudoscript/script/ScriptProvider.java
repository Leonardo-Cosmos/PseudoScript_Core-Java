package org.pseudoscript.script;

import java.io.IOException;

public abstract class ScriptProvider {

	public abstract void provide(Script script) throws IOException;
}

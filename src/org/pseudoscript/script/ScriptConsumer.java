package org.pseudoscript.script;

import java.io.IOException;

public abstract class ScriptConsumer {

	public abstract Script consume() throws IOException;
}

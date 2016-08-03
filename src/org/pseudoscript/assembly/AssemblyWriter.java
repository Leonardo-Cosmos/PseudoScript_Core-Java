package org.pseudoscript.assembly;

import java.io.IOException;
import java.io.Writer;

public abstract class AssemblyWriter {

	public abstract void write(Assembly assembly, Writer writer) throws IOException;
	
}

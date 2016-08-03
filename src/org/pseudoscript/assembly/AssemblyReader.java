package org.pseudoscript.assembly;

import java.io.IOException;
import java.io.Reader;

public abstract class AssemblyReader {

	public abstract Assembly read(Reader reader) throws IOException;
	
}

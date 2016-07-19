package org.pseudoscript.data.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

public class CsvDataSource extends FileDataSource {

	private static final String COMMA = ",";
	
	public CsvDataSource(File file) {
		super(file);
	}
	
	@Override
	public void load() throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] splitted = line.split(CsvDataSource.COMMA);
				if (splitted.length == 2) {
					dataMap.put(splitted[0], splitted[1]);
				}
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	@Override
	public void save() throws IOException {
		if (!isChanged()) {
			return;
		}
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			String line;
			for (Entry<String, Object> entry : dataMap.entrySet()) {
				line = String.join(CsvDataSource.COMMA, 
						entry.getKey(), entry.getValue().toString());
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}

package org.pseudoscript.data.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.pseudoscript.data.DataNotFoundException;
import org.pseudoscript.data.DataSource;
import org.pseudoscript.data.IllegalKeyException;

class DirDataSource implements DataSource {

	private final Map<String, DirDataSource> dirDataSourceMap;
	private final Map<String, FileDataSource> fileDataSourceMap;

	protected final File file;
	
	public DirDataSource(File file) {
		dirDataSourceMap = new HashMap<>();
		fileDataSourceMap = new HashMap<>();

		if (!file.isDirectory()) {
			throw new IllegalArgumentException();
		}
		this.file = file;
	}

	@Override
	public Object get(String key) throws DataNotFoundException, IllegalKeyException {
		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		SeparatedKey separatedKey = separateKey(key);		
		DataSource dataSource = findDataSource(separatedKey);
		return dataSource.get(separatedKey.innerKey);
	}

	@Override
	public void set(String key, Object value) throws IllegalKeyException {
		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		SeparatedKey separatedKey = separateKey(key);
		DataSource dataSource = findDataSource(separatedKey);
		dataSource.set(separatedKey.innerKey, value);
	}

	@Override
	public void load() throws IOException {
		dirDataSourceMap.clear();
		fileDataSourceMap.clear();
		
		File[] files = file.listFiles();
		DataSource dataSource = null;
		for (File file : files) {
			try {
				dataSource = FileDataSourceFactory.newDataSource(file);
			} catch (FileNotFoundException ex) {
				
			}
			if (dataSource instanceof DirDataSource) {
				dirDataSourceMap.put(file.getName(), (DirDataSource) dataSource);
			} else {
				fileDataSourceMap.put(file.getName(), (FileDataSource) dataSource);
			}
		}
		
		for (Entry<String, FileDataSource> entry : fileDataSourceMap.entrySet()) {
			entry.getValue().load();
		}
		
		for (Entry<String, DirDataSource> entry : dirDataSourceMap.entrySet()) {
			entry.getValue().load();
		}
	}

	@Override
	public void save() throws IOException {
		for (Entry<String, FileDataSource> entry : fileDataSourceMap.entrySet()) {
			entry.getValue().save();
		}
		
		for (Entry<String, DirDataSource> entry : dirDataSourceMap.entrySet()) {
			entry.getValue().save();
		}
	}

	protected SeparatedKey separateKey(String key) throws IllegalKeyException {
		int index = key.indexOf(DataSource.SEPARATOR);
		
		SeparatedKey separatedKey = null;
		if (index == -1) {
			return null;
		} else if (index == 0) {
			throw new IllegalKeyException("Key separator \"" + DataSource.SEPARATOR + 
					"\" cannot be start of key.");
		} else if (index == (key.length() - 1)) {
			throw new IllegalKeyException("Key separator \"" + DataSource.SEPARATOR +
					"\" cannot be end of key.");	
		} else {
			separatedKey = new SeparatedKey();
			separatedKey.dataSourceKey = key.substring(0, index);
			separatedKey.innerKey = key.substring(index + 1);
			return separatedKey;
		}
	}
	
	protected DataSource findDataSource(SeparatedKey separatedKey) throws IllegalKeyException {
		DirDataSource dirDataSource = dirDataSourceMap.get(separatedKey.dataSourceKey);
		if (dirDataSource != null) {
			if (!separatedKey.innerKey.contains(DataSource.SEPARATOR)) {
				throw new IllegalKeyException("Directory cannot be the last level of key.");
			}
			return dirDataSource;
		}
		
		FileDataSource fileDataSource = fileDataSourceMap.get(separatedKey.dataSourceKey);
		if (fileDataSource != null) {
			return fileDataSource;
		}
		
		throw new IllegalKeyException("Cannot find corresponding directory or file.");
	}

	protected class SeparatedKey {
		public String dataSourceKey;
		public String innerKey;
	}
}

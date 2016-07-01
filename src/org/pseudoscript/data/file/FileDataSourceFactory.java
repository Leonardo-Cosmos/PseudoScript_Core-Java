package org.pseudoscript.data.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pseudoscript.data.DataSource;

public class FileDataSourceFactory {

	private static final Pattern xmlPattern = Pattern.compile(".+\\.[xX][mM][lL]");
	private static final Pattern jsonPattern = Pattern.compile(".+\\.[jJ][sS][oO][nN]");

	public static boolean isXml(String fileName) {
		Matcher matcher = xmlPattern.matcher(fileName);
		return matcher.matches();
	}

	public static boolean isJson(String fileName) {
		Matcher matcher = jsonPattern.matcher(fileName);
		return matcher.matches();
	}

	public static DataSource newDataSource(String path) throws FileNotFoundException {
		File file = new File(path);
		return newDataSource(file);
	}
	
	public static DataSource newDataSource(File file) throws FileNotFoundException {
		if (!file.exists()) {
			throw new FileNotFoundException();
		}

		DataSource dataSource = null;
		if (file.isFile()) {
			if (isXml(file.getName())) {
				dataSource = new XmlDataSource(file);
			} else if (isJson(file.getName())) {
				dataSource = new JsonDataSource(file);
			} else {
				dataSource = new CsvDataSource(file);
			}
		} else {
			dataSource = new DirDataSource(file);
		}

		return dataSource;
	}

}

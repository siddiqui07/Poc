package com.yash.poc.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyReader {
	Logger log = Logger.getLogger(PropertyReader.class);
	int data;

	/**
	 * This method return the integer value by reading properties file
	 * 
	 * @param getData
	 * @return
	 */
	
	public int reader(String getData) {
		File file = new File("Test_Data\\data.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			log.error("Property file not found" + exception);
		}
		Properties property = new Properties();
		try {
			property.load(fileInput);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		String stringData = property.getProperty(getData);
		int data = Integer.parseInt(stringData);
		return data;
	}
}

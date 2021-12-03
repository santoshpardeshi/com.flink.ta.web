package com.flink.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {
	
	/** The instance. */
	private static ConfigurationReader instance = null;

	/** The properties. */
	private Properties properties = null;

	/**
	 * Instantiates a new configuration reader.
	 */
	private ConfigurationReader() {
		properties = new Properties();
		try {
			System.out.println("path: " + getClass().getClassLoader().getResource("config.properties").getPath());
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			if(null!=inputStream){
				properties.load(inputStream);
			}
		} catch (Exception e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

	/**
	 * Gets the single instance of ConfigurationReader.
	 *
	 * @return single instance of ConfigurationReader
	 */
	public synchronized static ConfigurationReader getInstance() {
		if (instance == null)
			instance = new ConfigurationReader();
		return instance;
	}

	/**
	 * get property value by name
	 *
	 * @param key the key
	 * @return the property
	 */
	public String getProperty(String key) {
		String value = null;
		if (properties.containsKey(key))
			value = (String) properties.get(key);
		else {
			System.out.println("the property is absent");
		}
		return value;
	}

}

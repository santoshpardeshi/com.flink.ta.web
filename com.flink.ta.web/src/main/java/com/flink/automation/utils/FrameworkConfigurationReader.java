package com.flink.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public class FrameworkConfigurationReader {

	/** The instance. */
	private static FrameworkConfigurationReader instance = null;

	/** The properties. */
	private Properties properties = null;

	/**
	 * Instantiates a new framework configuration reader.
	 */
	private FrameworkConfigurationReader() {
		properties = new Properties();
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("framework-config.properties");
			if (null != inputStream) {
				properties.load(inputStream);
			}
		} catch (Exception excep) {
			System.out.println(excep.getMessage());
		}
	}

	/**
	 * Gets the single instance of FrameworkConfigurationReader.
	 *
	 * @return single instance of FrameworkConfigurationReader
	 */
	public synchronized static FrameworkConfigurationReader getInstance() {
		if (instance == null)
			instance = new FrameworkConfigurationReader();
		return instance;
	}

	/**
	 * Gets the property.
	 *
	 * @param key the property name
	 * @return the property
	 */
	// get property value by name
	public String getProperty(String key) {
		String value = null;
		if (properties.containsKey(key))
			value = (String) properties.get(key);
		else {
			System.out.println("Property value with key: " + key + " not found.");
		}
		return value;
	}
}

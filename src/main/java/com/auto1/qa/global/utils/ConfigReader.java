package com.auto1.qa.global.utils;

import java.io.*;
import java.util.Properties;

/**
 * This class provides instance of ConfigReader for reading values from "config.properties", <env>/env.config and ".log" files
*/
public class ConfigReader {

	private FileInputStream fis = null;
	private Properties pro = new Properties();
	private final String runnerConfigFilePath = System.getProperty("user.dir") + "/src/test/resources/runner.config";
	private final String logFilePath = System.getProperty("user.dir") + "/src/test/resources/Logs";

	private static ConfigReader configReaderInstance = null;

	private ConfigReader() {

	}

	public static ConfigReader getInstance() {

		if (configReaderInstance == null)
			configReaderInstance = new ConfigReader();

		return configReaderInstance;
	}

	/**
	 * This method provides reading values from "<env>/env.config",
	 *  @param "propName" whose value needs to be returned
	 *  @returns "propNameValue"
	 */
	public String getProperty(String propName) {
		
		try {
			fis = new FileInputStream(new File(getFilePath()));
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {
			pro.load(fis);
		} catch (IOException e) {

			e.printStackTrace();

		}

		return pro.getProperty(propName);
	}

	/**
	 * This method provides writing values from "<env>/env.config",
	 *  @param "propName" whose value needs to be set
	 *  @returns "propNameValue"
	 */
	public void setProperty(String propName, String propNameValue) {

		try {
			fis = new FileInputStream(new File(getFilePath()));
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {
			pro.load(fis);
			pro.setProperty(propName, propNameValue);

			pro.store(new FileOutputStream(getFilePath()), null);
		} catch (IOException e) {

			e.printStackTrace();

		}



	}

	/**
	 * This method provides reading values from "runner.config",
	 *  @param "propName" whose value needs to be returned
	 */
	public String getRunnerConfigProperty(String propName) {
	
		try {
			fis = new FileInputStream(new File(runnerConfigFilePath));
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {
			pro.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return pro.getProperty(propName);
	}

	/**
	 * This method provides reading values from ".log" files,
	 * @param "propName" whose value needs to be returned
	 * @param "fileName" log file to be referenced
	 */
	public String getLogs(String fileName, String propName) {

		try {
			fis = new FileInputStream(new File(logFilePath + "/" + fileName));
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {
			pro.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return pro.getProperty(propName);
	}

	private String getFilePath() {

		String envFilePath = null;
		if (null != System.getProperty("env")) {
			envFilePath = System.getProperty("user.dir") + "/src/test/resources/environments/"
					+ System.getProperty("env") + "/env.config";
		} else {
			envFilePath = System.getProperty("user.dir") + "/src/test/resources/environments/"
					+ getRunnerConfigProperty("ENV") + "/env.config";
		}

		return envFilePath;
	}

}
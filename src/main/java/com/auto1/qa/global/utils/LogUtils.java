package com.auto1.qa.global.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This is a Singleton class, which provides instance of LogUtils for Logging capabilities
 */
public class LogUtils {

	private Logger logger;
	private String logFilePath;
	private String fileName;
	private static LogUtils logUtilsInstance = null;

	private LogUtils(Class<?> ClassName) {
		Properties props = new Properties();
		try {
			props.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + "log4j.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		PropertyConfigurator.configure(props);

		logger = Logger.getLogger(ClassName);

		ConfigReader configReader = ConfigReader.getInstance();

		new File(System.getProperty("user.dir") + "/output/" + (null != System.getProperty("env") ? System.getProperty("env")
				: configReader.getRunnerConfigProperty("ENV")) + "/logs/").mkdirs();

		logFilePath = System.getProperty("user.dir") + "/output/" +(null != System.getProperty("env") ? System.getProperty("env")
				: configReader.getRunnerConfigProperty("ENV")) + "/logs/";

		fileName = "TestLog_" + System.getProperty("TEST_RUN_TIMESTAMP") + ".log";

	}

	public static LogUtils getInstance(Class<?> ClassName) {

		if (logUtilsInstance == null)
			logUtilsInstance = new LogUtils(ClassName);

		return logUtilsInstance;
	}

	public void error(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.ERROR);

		logger.error("ERROR" + " " + currentTime + ": " + message);
		writeToLogFile("ERROR" + " " + currentTime + ": " + message);

	}

	public void info(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("INFO" + " " + currentTime + ": " + message);
		writeToLogFile("INFO" + " " + currentTime + ": " + message);

	}

	/*
	 * This is a method for logging start of a test
	 */
	public void start(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("START" + " " + currentTime + ": " + message);
		writeToLogFile("START" + " " + currentTime + ": " + message);

	}

	/*
	 * This is a method for logging actual code/response in <pre> format
	 */
	public void code(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("CODE" + " " + currentTime + ": " + message);
		writeToLogFile("CODE" + " " + currentTime + ": " + message);
		writeToLogFile("CODE-END");

	}

	public void debug(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.DEBUG);

		logger.debug("DEBUG" + " " + currentTime + ": " + message);
		writeToLogFile("DEBUG" + " " + currentTime + ": " + message);

	}

	public void warn(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.WARN);

		logger.warn("WARN" + " " + currentTime + ": " + message);
		writeToLogFile("WARN" + " " + currentTime + ": " + message);

	}

	public void pass(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.INFO);

		logger.info("PASS" + " " + currentTime + ": " + message);
		writeToLogFile("PASS" + " " + currentTime + ": " + message);

	}

	/*
	 * This method provides failure log by explicitly throwing 'TestFailedException'
	 */
	public void fail(String message) {

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		Logger.getRootLogger().setLevel(Level.ERROR);

		logger.error("FAIL" + " " + currentTime + ": " + message);
		writeToLogFile("FAIL" + " " + currentTime + ": " + message);
		writeToLogFile("CODE-END");
		throw new TestFailedException(message + ". Test step failed..");


	}

	/*
	 * This method writes losg to output/<env>/logs folder with timestamp
	 */
	public void writeToLogFile(String content) {

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(logFilePath + "/" + fileName, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write("Thread #" + Thread.currentThread().getId() + " :" + content);
			bw.flush();
			fw.close();
			bw.close();
		} catch (IOException e) {

			Logger.getRootLogger().setLevel(Level.ERROR);

			logger.error("ERROR" + " " + "Can't write to Log File, IOException occurred!!!!!!!!!!!!!!!!! \n");
			e.printStackTrace();
		}

	}

}
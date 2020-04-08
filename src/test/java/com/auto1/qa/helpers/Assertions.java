package com.auto1.qa.helpers;

import org.testng.Assert;
import com.auto1.qa.global.utils.LogUtils;

/***
 * This class provides a wrapper around TestNG assertions; with logging
 *
 * @author Kushal Bhalaik
 */
public class Assertions {

	private LogUtils LOGGER;
	private static Assertions assertInstance = null;

	private Assertions() {
		LOGGER = LogUtils.getInstance(Assertions.class);
	}

	public static Assertions getInstance() {

		if (assertInstance == null)
			assertInstance = new Assertions();

		return assertInstance;
	}

	public void assertIsNotNull(String condition, String message) {

		try {
			Assert.assertNotNull(condition, message);

		} catch (AssertionError e) {

			LOGGER.error(message + " : " + e);
			Assert.fail(e.getMessage());

		}

	}

	public void assertTrue(boolean condition, String message) {

		try {
			Assert.assertTrue(condition, message);

		} catch (AssertionError e) {

			LOGGER.error(message + " : " + e);
			Assert.fail(e.getMessage());

		}

	}

	public void assertFalse(boolean condition, String message) {

		try {
			Assert.assertFalse(condition, message);

		} catch (AssertionError e) {

			LOGGER.error(message + " : " + e);
			Assert.fail(e.getMessage());

		}

	}

	public void assertEquals(String actual, String expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);

		} catch (AssertionError e) {

			LOGGER.error(message + " : " + e);
			Assert.fail(e.getMessage());
		}
	}

	public void assertEquals(int actual, int expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);

		} catch (AssertionError e) {

			LOGGER.error(message + " : " + e);
			Assert.fail(e.getMessage());
		}
	}

}
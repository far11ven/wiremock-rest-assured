package com.auto1.qa.helpers;

import java.io.FileNotFoundException;
import java.util.*;

import com.auto1.qa.utils.RestUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.FileOperations;
import com.auto1.qa.global.utils.LogUtils;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;



/*
 * This is class contains all the helper methods required by ApiTestSuite class
 */

public class TestUtils {

	private static ConfigReader configReader = ConfigReader.getInstance();
	private static Assertions assertions = Assertions.getInstance();
	private static final LogUtils LOGGER = LogUtils.getInstance(TestUtils.class);
	private static RestUtils restUtils  = RestUtils.getInstance();

	//This method verifies the http response status returned
	public static void checkStatusIs(Response res, int statusCode) {

		try {
			assertions.assertEquals(res.getStatusCode(), statusCode, "HTTP Response Status code is not as expected.");
			LOGGER.info("Http Response Status code is as expected : " + statusCode);
		} catch (AssertionError e) {

			LOGGER.fail("API Response Http Status expected was [" + statusCode + "] and actual is [" + res.getStatusCode()  +"]");
		}
	}

	//This method validates a response against selected schema
	public static void validateResponseSchema(Response res, String schemaName) {

		try {

			String schema = FileOperations.readFromFile("./src/test/resources/schemas/" + schemaName);
			res.then().assertThat().body(matchesJsonSchema(schema));
			LOGGER.info("Response Schema Validation is PASS");

		} catch (FileNotFoundException e) {
			LOGGER.error("Couldn't find [" +  schemaName + "] schema in src/test/resources/schemas folder");

		}catch (AssertionError ex) {
			ex.printStackTrace();
			LOGGER.error("Response schema validation failed!!");
			LOGGER.fail("STACKTRACE:" + Joiner.on("\n").join(Iterables.limit(Arrays.asList(ex.getStackTrace()), 10)));  //stores only 10 lines from error stacktrace in log file
		}
	}

	//This method verifies if there are duplicates in a list List<T>
	public static <T> Set<T> findDuplicates(Collection<T> collection) {

		Set<T> duplicates = new LinkedHashSet<>();
		Set<T> uniques = new HashSet<>();

		for(T t : collection) {
			if(!uniques.add(t)) {
				duplicates.add(t);
			}
		}

		return duplicates;
	}
}

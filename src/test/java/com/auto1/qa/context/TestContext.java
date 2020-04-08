package com.auto1.qa.context;

import com.auto1.qa.global.utils.LogUtils;
import com.auto1.qa.helpers.ExtentHelper;
import com.auto1.qa.utils.RestUtils;

import com.auto1.qa.global.utils.ConfigReader;

/**
 * Using PicoContainer to share state between stepdefs in a scenario
 *
** Registering a class in TestContext makes sure there's only single instance of that in use
 *
 * @author Kushal Bhalaik
 */

public class TestContext {
	private LogUtils logUtils;
	private RestUtils restUtils;
	private ExtentHelper extentHelper;

	public ScenarioContext scenarioContext;
	private static ConfigReader configReader = ConfigReader.getInstance();

	public TestContext(){
		scenarioContext = new ScenarioContext();
		logUtils = LogUtils.getInstance(TestContext.class);
		extentHelper = ExtentHelper.getInstance();

		// no need to initiate for api test
		if (null != System.getProperty("mode")) {
			if (System.getProperty("mode").equalsIgnoreCase("api")){
				restUtils = RestUtils.getInstance();
			}
		} else if (null != configReader.getRunnerConfigProperty("MODE")) {

			if (configReader.getRunnerConfigProperty("MODE").equalsIgnoreCase("api")) {
				restUtils = RestUtils.getInstance();
			}
		}

	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}

	public LogUtils getLogUtils() {
		return logUtils;
	}

	public RestUtils getRestUtils() {
		return restUtils;
	}

	public ExtentHelper getExtentHelper () {
		return extentHelper;
	}

}
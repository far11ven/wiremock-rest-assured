package com.auto1.qa.stepdefs.api;

import com.auto1.qa.context.TestContext;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.LogUtils;
import com.auto1.qa.utils.RestUtils;
import cucumber.api.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

class MockedSteps {

    private Response res = null; // Response
    private JsonPath jp = null; // JsonPath

    private RestUtils restUtils;
    private LogUtils LOGGER;
    private ConfigReader configReader;

    private TestContext testContext;

    public MockedSteps(TestContext context) {
        testContext = context;
        LOGGER = testContext.getLogUtils();
        restUtils = RestUtils.getInstance();
        configReader = ConfigReader.getInstance();
    }

    //mocked steps are in ApiCommonSteps for now
}

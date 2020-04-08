package com.auto1.qa.stepdefs.api;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auto1.qa.context.TestContext;
import com.auto1.qa.context.enums.ContextEnums;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.LogUtils;
import com.auto1.qa.utils.RestUtils;
import com.auto1.qa.helpers.TestUtils;
import com.auto1.qa.global.utils.FileOperations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

/***
 * This class contains all the common cucumber step definitions
 */
public class ApiCommonSteps {

    private Response res = null; // Response
    private JsonPath jp = null; // JsonPath

    private RestUtils restUtils;
    private LogUtils LOGGER;
    private ConfigReader configReader;

    private TestContext testContext;

    public ApiCommonSteps(TestContext context) {
        testContext = context;
        LOGGER = testContext.getLogUtils();
        restUtils = RestUtils.getInstance();
        configReader = ConfigReader.getInstance();
    }

    public void beforeNewRequest() {
        restUtils.resetRestAssured();                           //reset and get new instance
        restUtils = RestUtils.getInstance();
    }

    @Given("^As a user I want to execute mocked endpoint one$")
    public void testStatusCodePositive() {
        System.out.println("Test 1 >>>>");
        given().
                when().
                get("http://localhost:8090/an/endpoint").
                then().
                assertThat().statusCode(200);
    }

    @Given("^As a user I want to execute mocked endpoint two$")
    public void testStatusCodeNegative() {
        System.out.println("Test 2 >>>>");
        given().
                when().
                get("http://localhost:8090/another/endpoint").
                then().
                assertThat().statusCode(404);
    }

    @Given("^As a user I want to execute mocked endpoint three$")
    public void testResponseContents() {
        System.out.println("Test 3 >>>>");
        Response response = given().when().get("http://localhost:8090/an/endpoint");

        System.out.println("Response >>>>" + response.asString());
        String title = response.jsonPath().get("glossary.title");
        System.out.println("Title >>>>" + title);
        Assert.assertEquals(title, "example glossary");
    }


    @Given("^As a user I want to execute '([^\"]+)' endpoint$")
    public void user_wants_to_execute_api_endpoint(String endpoint) {
        beforeNewRequest();
        restUtils.setBaseURI(configReader.getProperty("BASE_URL"));
        LOGGER.info("Setting BASE_URL as :" + configReader.getProperty("BASE_URL"));
        // Setup API Version & Base Path
        restUtils.setBasePath(configReader.getProperty("API_VERSION") + "/" + configReader.getProperty("API_BASE_PATH"));
        LOGGER.info("Setting API_VERSION as :" + configReader.getProperty("API_VERSION"));
        LOGGER.info("Setting BASE_PATH as :" + configReader.getProperty("API_BASE_PATH"));

        // Used for ignoring ssl checks
        restUtils.relaxedHTTPSValidation();

        // save API_ENDPOINT in context
        testContext.scenarioContext.setContext(ContextEnums.API_ENDPOINT, endpoint);
        LOGGER.info("Setting API_ENDPOINT as :" + endpoint);

        restUtils.setRequestQueryParams("wa_key", configReader.getProperty("wa_key"));

    }

    @Given("^As a user I want to execute '([^\"]+)' endpoint without 'wa_key'$")
    public void user_wants_to_execute_api_endpoint_without_wa_key(String endpoint) {
        beforeNewRequest();
        restUtils.setBaseURI(configReader.getProperty("BASE_URL"));
        LOGGER.info("Setting BASE_URL as :" + configReader.getProperty("BASE_URL"));
        // Setup API Version & Base Path
        restUtils.setBasePath(configReader.getProperty("API_VERSION") + "/" + configReader.getProperty("API_BASE_PATH"));
        LOGGER.info("Setting API_VERSION as :" + configReader.getProperty("API_VERSION"));
        LOGGER.info("Setting BASE_PATH as :" + configReader.getProperty("API_BASE_PATH"));

        // Used for ignoring ssl checks
        restUtils.relaxedHTTPSValidation();

        // save API_ENDPOINT in context
        testContext.scenarioContext.setContext(ContextEnums.API_ENDPOINT, endpoint);
        LOGGER.info("Setting API_ENDPOINT as :" + endpoint);

    }

    @When("^I set query params as$")
    public void user_sets_query_params(Map<String, String> queryParams) {
        LOGGER.info("Setting query params DATA as..");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {

            if (entry.getValue().contains(",")) {
                String currentParam = entry.getValue();
                currentParam = currentParam.replace(" ", "");
                String[] keyValues = currentParam.split(",");
                StringBuilder queryString = new StringBuilder();
                for (int i = 0; i < keyValues.length; i++) {

                    if (i == 0) {
                        queryString = new StringBuilder(keyValues[i]);
                    } else {
                        queryString.append("&").append(entry.getKey()).append("=").append(keyValues[i]);
                    }
                }
                try {
                    queryParams.put(entry.getKey(), queryString.toString());
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                }

            }
            LOGGER.info(entry.getKey() + ":" + entry.getValue());
        }

        restUtils.setRequestQueryParams(queryParams);

    }

    @When("^I set query param '([^\"]+)' as '([^\"]+)'$")
    public void user_sets_query_param_as(String queryParamName, String queryParamValue) {
        LOGGER.info("Setting query params DATA as..");
        Map<String, String> newRuleIDParams = new HashMap<>();
        newRuleIDParams.put(queryParamName, queryParamValue);
        restUtils.setRequestQueryParams(newRuleIDParams);

    }

    @When("^I set path params as$")
    public void user_sets_path_params(DataTable pathParams) {
        List<List<String>> pathData = pathParams.asLists();

        LOGGER.info("Setting path params DATA.." + pathData.get(0).get(0));
        restUtils.setRequestPathParams(pathData.get(0).get(0), pathData.get(0).get(1));

        testContext.scenarioContext.setContext(ContextEnums.CURRENT_PATH_PARAM, pathData.get(0).get(1));

    }


    @When("^I set path params '([^\"]+)' as \"([^\"]*)\"$")
    public void user_sets_path_params_as_key_and_value(String pathParam, String pathParamValue) {

        LOGGER.info("Setting path params DATA.." + pathParam);
        restUtils.setRequestPathParams(pathParam, pathParamValue);


    }

    @When("^I set headers as$")
    public void user_sets_headers(Map<String, String> headers) {
        LOGGER.info("Setting headers..");
        restUtils.setRequestHeader(headers);
        restUtils.setContentType(ContentType.JSON);

        int i = 1;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            LOGGER.info("Setting headers.." + i++ + entry.getKey());
        }
    }

    @When("^I set request body as '([^\"]+)'$")
    public void user_sets_request_body(String fileName) {
        LOGGER.info("Setting request body DATA..");
        String requestBody = null;

        try {
            requestBody = FileOperations.readFromFile("src/test/resources/request-payloads/" + fileName);

        } catch (FileNotFoundException e) {
            LOGGER.error("Error reading Request body json file - " + fileName);
            e.printStackTrace();
        }

        restUtils.setRequestBody(requestBody);


    }


    @When("^I as a user submit the 'GET' request$")
    public void user_submits_the_GET_request() {
        LOGGER.info("GETTING DATA.. FROM :" + testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT));

        res = restUtils.getResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());

        LOGGER.code("GET Response :" + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^I as a user submit the 'POST' request$")
    public void user_submits_the_POST_request() {
        LOGGER.info("POSTING DATA..");
        res = restUtils
                .getPOSTResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());


        LOGGER.code("POST Response : " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^I as a user submit the 'DELETE' request$")
    public void user_submits_the_DELETE_request() {
        LOGGER.info("DELETING DATA..");
        res = restUtils
                .getDELETEResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());


        LOGGER.code("DELETE Response : " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^I as a user submit the 'PUT' request$")
    public void user_submits_the_PUT_request() {
        LOGGER.info("UPDATING DATA..");
        res = restUtils
                .getPUTResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());

        LOGGER.code("PUT Response " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @When("^I as a user submit the 'PATCH' request$")
    public void user_submits_the_PATCH_request() {
        LOGGER.info("UPDATING DATA..");
        res = restUtils
                .getPATCHResponsebyPath(testContext.scenarioContext.getContext(ContextEnums.API_ENDPOINT).toString());

        LOGGER.code("PATCH Response " + res.asString());

        // save response
        testContext.scenarioContext.setResponse(ContextEnums.RESPONSE, res);

    }

    @Then("^Verify response status code is '([^\"]+)'$")
    public void verify_user_validates_status_code(int statusCode) {
        jp = restUtils.getJsonPath(res);

        // verify if the HTTP Status received in response was [statusCode]
        TestUtils.checkStatusIs(res, statusCode);

    }

}

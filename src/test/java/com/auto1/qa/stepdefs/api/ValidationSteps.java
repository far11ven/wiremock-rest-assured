package com.auto1.qa.stepdefs.api;

import com.auto1.qa.context.TestContext;
import com.auto1.qa.context.enums.ContextEnums;
import com.auto1.qa.global.utils.ConfigReader;
import com.auto1.qa.global.utils.LogUtils;
import com.auto1.qa.helpers.Assertions;

import com.auto1.qa.helpers.TestUtils;
import com.auto1.qa.models.ApiResponse;
import com.auto1.qa.models.ErrorResponse;
import com.auto1.qa.utils.RestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/***
 * This is class contains all the validation steps
 */
public class ValidationSteps {

    private Response res = null; // Response
    private JsonPath jp = null; // JsonPath

    private RestUtils restUtils;
    private LogUtils LOGGER;
    private ConfigReader configReader;
    private static Assertions assertions;

    private TestContext testContext;

    public ValidationSteps(TestContext context) {
        testContext = context;
        LOGGER = testContext.getLogUtils();
        restUtils = RestUtils.getInstance();
        configReader = ConfigReader.getInstance();
        assertions = Assertions.getInstance();
    }

    @Then("^I store all the available car manufacturer$")
    public void store_all_available_manufacturers() {
        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);

        ApiResponse schemaObj = res.as(ApiResponse.class);

        LOGGER.code("schemaObj:" + schemaObj.getWkda());
        testContext.scenarioContext.setContext(ContextEnums.LIST_OF_ALL_MANUFACTURERS, schemaObj.getWkda());
    }

    @Then("^Verify actual schema of response$")
    public void verify_schema_of_response() {
        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);

        ApiResponse schemaObj = res.as(ApiResponse.class);

        LOGGER.code("Total available Wkda codes:" + schemaObj.getWkda().size());
        TestUtils.validateResponseSchema(res,"ApiResponseSchema.json");
    }

    @Then("^Verify all manufacturer codes are part of list of all available manufacturer codes$")
    public void verify_received_manufacturer_codes() {
        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);

        ApiResponse schemaObj = res.as(ApiResponse.class);
        Map<String,String> franceCarManufacturersList = schemaObj.getWkda();

        //validate response schema
        TestUtils.validateResponseSchema(res,"ApiResponseSchema.json");

        ObjectMapper oMapper = new ObjectMapper();

        // Convert stored LIST_OF_ALL_MANUFACTURERS object -> Map
        Map<String, String> allCarManufacturersList =  oMapper.convertValue(testContext.scenarioContext.getResponse(ContextEnums.LIST_OF_ALL_MANUFACTURERS), Map.class);

        assertions.assertTrue(allCarManufacturersList.entrySet().containsAll(franceCarManufacturersList.entrySet()), "GET 'manufacturer' response has unknown car manufacturer codes");


        //verify each individual car manufacturer code
        for (Map.Entry<String, String> entry : franceCarManufacturersList.entrySet()) {

            assertions.assertEquals(entry.getValue(), allCarManufacturersList.get(entry.getKey()), entry.getKey() + " has invalid value");

        }

    }


    @Then("^Verify 'wkda' does-not contain any values$")
    public void verify_wkda_does_not_have_any_values() {

        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);

        Map<String, Object> wkdaList = null;
        ObjectMapper oMapper = new ObjectMapper();
        
        try {
            wkdaList =  oMapper.readValue(res.asString(), Map.class);
            wkdaList =  oMapper.readValue(wkdaList.get("wkda").toString() , Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        LOGGER.code("Total available Wkda codes:" + wkdaList.size());

        assertions.assertTrue(wkdaList.size() == 0, "'wkda' is not empty");


    }



    @Then("^Verify error is received$")
    public void verify_error_response(DataTable pathParams) {
        List<List<String>> errorCode = pathParams.asLists();

        res = (Response) testContext.scenarioContext.getResponse(ContextEnums.RESPONSE);
        
        ErrorResponse errorObj = res.as(ErrorResponse.class);
        assertions.assertIsNotNull(errorObj.getError(), "Error Code is null");
        assertions.assertEquals(errorObj.getError(), errorCode.get(0).get(0), "Error code doesn't match");
        assertions.assertIsNotNull(errorObj.getMessage(), "Error Message is null");

    }

}

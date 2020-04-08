package com.auto1.qa.utils;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import com.google.gson.JsonArray;

import static io.restassured.config.ParamConfig.UpdateStrategy.REPLACE;
import static io.restassured.config.ParamConfig.paramConfig;

/***
 * This is class contains all the restAssured related getter/setter methods
 * @author Kushal Bhalaik
 */
public class RestUtils {

    private static RestUtils apiUtilsInstance = null;
    private RequestSpecification httpRequest;

    private RestUtils() {
        httpRequest = RestAssured.given().config(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)));

    }

    public static RestUtils getInstance() {

        if (apiUtilsInstance == null)
            apiUtilsInstance = new RestUtils();

        return apiUtilsInstance;
    }

    // How different parameter types should be updated on "collision", in this case REPLACED with latest value
    public void setRequestSpecificationParamConfig() {
        httpRequest.config(RestAssured.config().paramConfig(paramConfig().queryParamsUpdateStrategy(REPLACE)));
    }

    // Replaces parameter values for all kinds of parameter types
    public void replaceAllRequestSpecificationParamConfig() {
        httpRequest.config(RestAssured.config().paramConfig(paramConfig().replaceAllParameters()));
    }

    public RequestSpecification getRequestSpecification() {
        return httpRequest;
    }

    public void resetRestAssured() {
        apiUtilsInstance = null;
    }

    // Gets Base URI
    public static String getBaseURI() {
        return RestAssured.baseURI;
    }

    // Gets base path
    public static String getBasePath() {
        return RestAssured.basePath;
    }

    // Sets Base URI
    public void setBaseURI(String baseURI) {
        httpRequest.baseUri(baseURI);
    }

    // Sets base path
    public void setBasePath(String basePathTerm) {
        httpRequest.basePath(basePathTerm);
    }

    // Reset Base URI (after test)
    public void resetBaseURI() {
        httpRequest.basePath("");
    }

    // Reset base path
    public void resetBasePath() {
        httpRequest.basePath("");
    }

    // Sets relaxedHTTPSValidation
    public void relaxedHTTPSValidation() {
        httpRequest.relaxedHTTPSValidation();
    }

    // Sets ContentType
    public void setContentType(ContentType Type) {
        httpRequest.contentType(Type);
    }

    // Sets RequestBody using String
    public void setRequestBody(String body) {
        httpRequest.body(body);
    }

    // Sets RequestBody using JsonPath
    public void setRequestBody(JsonPath requestBodyArray) {
        httpRequest.body(requestBodyArray);
    }

    // Sets RequestBody using JsonArray
    public void setRequestBody(JsonObject obj) {
        httpRequest.body(obj);
    }

    // Sets RequestBody using JsonArray
    public void setRequestBody(JsonArray body) {
        httpRequest.body(body);
    }

    // Sets query params
    public void setRequestQueryParams(Map<String, String> params) {
        httpRequest.queryParams(params);
    }

    // Sets query params
    public void setRequestQueryParams(String param, String paramValue) {
        httpRequest.queryParams(param, paramValue);
    }

    // Sets query params
    public void setRequestPathParams(String param, String paramValue) {
        httpRequest.pathParams(param, paramValue);
    }

    // Sets RequestHeader
    public void setRequestHeader(Map<String, String> headers) {
        httpRequest.headers(headers);
    }

    // Sets RequestHeader
    public void setRequestHeader(String headerKey, String paramValue) {
        httpRequest.headers(headerKey, paramValue);
    }

    // Gets PostRequestResponse
    public Response getPOSTResponse() {
        return httpRequest.post();
    }

    // Returns POST response by given path
    public Response getPOSTResponsebyPath(String path) {
        return httpRequest.post(path);
    }

    // Returns POST response by given path
    public Response getPUTResponsebyPath(String path) {
        return httpRequest.put(path);
    }
    // Returns DELETE response by given path
    public Response getPATCHResponsebyPath(String path) {
        return httpRequest.patch(path);
    }

    // Returns DELETE response by given path
    public Response getDELETEResponsebyPath(String path) {
        return httpRequest.delete(path);
    }


    // Returns response by given path
    public Response getResponsebyPath(String path) {
        return httpRequest.get(path);
    }

    // Returns response
    public Response getResponse() {
        return httpRequest.get();
    }

    // Returns JsonPath object
    public JsonPath getJsonPath(Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }
}
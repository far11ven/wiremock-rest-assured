package com.auto1.qa.context;

import java.util.HashMap;
import java.util.Map;
import com.auto1.qa.context.enums.ContextEnums;

import io.restassured.response.Response;

/**
 * This class defines what Type of context can be stored
 *
 * @author Kushal Bhalaik
 */

public class ScenarioContext {

	private Map<String, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}

	// This method is used for storing any context
	public void setContext(ContextEnums key, Object value) {
		scenarioContext.put(key.toString(), value);
	}

	// This method is used for fetching any stored context
	public Object getContext(ContextEnums key) {
		return scenarioContext.get(key.toString());
	}

	// This method is used for storing an API response
	public void setResponse(ContextEnums key, Response value) {
		scenarioContext.put(key.toString(), value);
	}

	// This method is used for fetching stored API response
	public Object getResponse(ContextEnums key) {
		return scenarioContext.get(key.toString());
	}

}
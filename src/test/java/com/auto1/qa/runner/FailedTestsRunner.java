package com.auto1.qa.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/***
 * This class provides Failed TestRunner, which runs failed features from "target/rerun.txt"
 *
 */
@CucumberOptions(
		features = "@target/rerun.txt",	 //Cucumber picks the failed scenarios from this file 
		glue = { "com.auto1.qa.stepdefs" },
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:", "rerun:target/rerun.txt" }, 
		tags = { "@all" },
		dryRun = false,
		monochrome = true
		)

public class FailedTestsRunner extends AbstractTestNGCucumberTests {
	
	

}
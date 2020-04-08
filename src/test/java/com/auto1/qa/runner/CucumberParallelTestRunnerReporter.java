package com.auto1.qa.runner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.auto1.qa.helpers.ExtentHelper;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import com.auto1.qa.global.utils.ConfigReader;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

/***
 * This class provides Parallel TestRunner for all the features
 *
 */
@CucumberOptions(
        features = {"features"},
        glue = {"com.auto1.qa.stepdefs"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:target/rerun.txt"},
        tags = {"@api"},
        dryRun = false,
        monochrome = true
)

public class CucumberParallelTestRunnerReporter extends AbstractTestNGCucumberTests {

    private static ConfigReader configReader = ConfigReader.getInstance();
    private static ExtentHelper extent = ExtentHelper.getInstance();

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeClass
    public static void reporterSetup() {

        String currTestRunTimestamp = Long.toString(new Date().getTime());
        System.setProperty("TEST_RUN_TIMESTAMP", currTestRunTimestamp); // Save current Run Timestamp

        //attach extent reporter
        extent.getExtentReports().attachReporter(extent.getExtentHtmlReporter());

    }

    @AfterClass
    public static void reporterTeardown() throws UnknownHostException {

        ExtentCucumberAdapter.setSystemInfo("User", System.getProperty("user.name"));
        ExtentCucumberAdapter.setSystemInfo("Host Machine", InetAddress.getLocalHost().getHostName());
        ExtentCucumberAdapter.setSystemInfo("Operating System", System.getProperty("os.name").toString());
        ExtentCucumberAdapter.setSystemInfo("Web App Name", "auto1-test");
        ExtentCucumberAdapter.setSystemInfo("App URL", "<a href=" + configReader.getProperty("BASE_URL") + ">" + configReader.getProperty("BASE_URL") + "</a>");


        if (null != System.getProperty("mode")) {
            ExtentCucumberAdapter.setSystemInfo("Initiator", System.getProperty("mode"));
        } else {
            ExtentCucumberAdapter.setSystemInfo("Initiator", configReader.getRunnerConfigProperty("MODE"));
        }

        ExtentCucumberAdapter.setTestRunnerOutput("<pre><h4> TestLog_" + System.getProperty("TEST_RUN_TIMESTAMP") + "</h4></pre>");

        try {

            ExtentCucumberAdapter.setTestRunnerOutput(extent.getFinalLogs());

        } catch (IOException e) {

            ExtentCucumberAdapter.setTestRunnerOutput(
                    "<span style=\"color:red;\"> There was an error attaching logs to this report. Please check following directory for logs : <pre>/output/"
                            + System.getProperty("env") + "/logs/ </pre></span>");
            e.printStackTrace();
        }

        extent.flush();
        extent.createAssetsDirectory();
    }


}

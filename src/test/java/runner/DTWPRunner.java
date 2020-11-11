package runner;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import listener.CustomCucumber;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

@RunWith(CustomCucumber.class)
@CucumberOptions(features = "classpath:features/toan_local.feature",
        glue = "stepdefs",
        plugin = {"json:target/cucumber.json", "pretty"})
public class DTWPRunner {

}

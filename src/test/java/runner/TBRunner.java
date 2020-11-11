package runner;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.File;
import java.io.IOException;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/trialbalance.feature",
        glue = {"stepdefs"},
        plugin = {"json:target/cucumber.json"})

public class TBRunner{
}

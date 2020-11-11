package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/subsequence.feature",
        glue = "stepdefs",
        plugin ={"json:target/cucumber.json"})
public class BareboneRunner {
}

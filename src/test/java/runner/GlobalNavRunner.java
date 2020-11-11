package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(features = "classpath:features/globalnav.feature",
        glue = "stepdefs",
        plugin ={"pretty" , "html:report"})

public class GlobalNavRunner {
}

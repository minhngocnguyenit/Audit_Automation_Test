package runner;

import io.cucumber.junit.CucumberOptions;
import listener.CustomCucumber;
import org.junit.runner.RunWith;


@RunWith(CustomCucumber.class)
@CucumberOptions(features = "src/test/resources/features/smoke_testing.feature",
        glue = {"stepdefs"},
        plugin = {"json:target/cucumber.json", "pretty"})

public class RunnerTest {

}

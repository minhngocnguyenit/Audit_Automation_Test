package runner;

import io.cucumber.junit.CucumberOptions;
import listener.CustomCucumber;
import org.junit.runner.RunWith;


@RunWith(CustomCucumber.class)
@CucumberOptions(features = "classpath:features/DEV_smoke_testing.feature",
        glue = {"stepdefs"},
        plugin = {"json:target/cucumber.json", "pretty"})

public class DevEnvRunnerTest {
}

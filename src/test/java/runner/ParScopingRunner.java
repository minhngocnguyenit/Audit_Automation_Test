package runner;

//import com.cucumber.listener.Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/parscoping.feature",
        glue = "stepdefs",
        plugin ={"pretty" , "html:target/cucumber-reports/report.html"})

public class ParScopingRunner {


}

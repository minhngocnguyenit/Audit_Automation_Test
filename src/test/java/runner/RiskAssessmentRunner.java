package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/control_testing.feature",
        glue = "stepdefs",
        plugin ={"pretty" , "html:report"})
public class RiskAssessmentRunner {

    @BeforeClass
    public static void init() {
        String downloadFolder = System.getProperty("user.dir") + File.separator + "download";
        File file = new File(downloadFolder);
        if(!file.exists()) {
            file.mkdir();
        }else {
            try {
                FileUtils.cleanDirectory(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

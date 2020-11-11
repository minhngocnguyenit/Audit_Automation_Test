package stepdefs;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            try{
                scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
                //Check if is opening WPs
                try{
                    while (webDriver.getWindowHandles().size() > 1) {
                        webDriver.switchTo().window((String) webDriver.getWindowHandles().toArray()[1]);
                        webDriver.close();
                        webDriver.switchTo().window((String) webDriver.getWindowHandles().toArray()[0]);
                    }
                    webDriver.switchTo().window((String) webDriver.getWindowHandles().toArray()[0]);
                }catch (Exception e) {
                    //System.out.println(e.getMessage());
                }
                scenario.write("Scenario " + scenario.getName() + " fail.");
                logger.error("Scenario " + scenario.getName() + " fail.");
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

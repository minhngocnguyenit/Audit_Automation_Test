package stepdefs;

import com.sm.page.EngagementSettingPage;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;

public class EngagementSettingDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public EngagementSettingDefinition() {
        engagementSettingPage = EngagementSettingPage.getInstance(webDriver);
    }

    @And("I set Report Date {string}")
    public void iSetReportDate(String date) {
        logger.info("I set report date: " + date);
        engagementSettingPage.setReportDate(date);
    }

    @And("I set Archive Control Date {string}")
    public void iSetArchiveControlDate(String date) {
        logger.info("I set archive date: " + date);
        engagementSettingPage.setArchiveDate(date);
    }
}

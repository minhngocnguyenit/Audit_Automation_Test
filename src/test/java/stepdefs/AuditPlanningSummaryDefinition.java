package stepdefs;

import com.sm.page.AuditSummaryPage;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class AuditPlanningSummaryDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public AuditPlanningSummaryDefinition() {
        auditSummaryPage = AuditSummaryPage.getInstance(webDriver);
    }

    @Then("I should see the Audit Planning\\/Summary is loaded successfully")
    public void iShouldSeeTheAuditPlanningSummaryIsLoadedSuccessfully() {

    }

    @Then("I should see the {string} is loaded successfully")
    public void iShouldSeeTheIsLoadedSuccessfully(String wp) {
        logger.info("I should see the " + wp + " is loaded successfully");
        Assert.assertTrue("Problem when opening audit summary page", auditSummaryPage.isLoad());
        auditSummaryPage.verifyWPisLoad();
    }
}

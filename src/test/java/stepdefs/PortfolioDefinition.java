package stepdefs;

import com.sm.context.Context;
import com.sm.context.ScenarioContext;
import com.sm.page.PortfolioPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PortfolioDefinition extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public PortfolioDefinition() {
        portfolioPage = PortfolioPage.getInstance(this.webDriver);
    }

    @Then("View page after login successful")
    public void view_page_after_login_successful() throws InterruptedException {
        logger.info("I view page after login successfully");
        //Wait for approve from device
        Thread.sleep(20000);
        portfolioPage.isLoad();
    }

    @And("I create new engagement")
    public void i_create_new_engagement(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);

        String cloudCabinet = data.get(0).get(0);
        String contentFrom = data.get(1).get(0);
        String language = data.get(2).get(0);
        String engagementName = data.get(3).get(0);
        String engagementType = data.get(4).get(0);
        String periodEndDate = data.get(5).get(0);
        String country = data.get(6).get(0);
        String office = data.get(7).get(0);
        String entity = data.get(8).get(0);
        String entityChargeCode = data.get(9).get(0);
        String industry = data.get(10).get(0);
        String financialReportingFramework = data.get(11).get(0);
        String engagementStandard = data.get(12).get(0);
        String role = data.get(13).get(0);

        //Set engagement name prefix
        if(ScenarioContext.getContext(Context.ENGAGEMENT_PREFIX) == null){
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hhmmss");
            String engagementPrefix = " " + sdf.format(new Date());
            ScenarioContext.setContext(Context.ENGAGEMENT_PREFIX, engagementPrefix);
        }
        engagementName += ScenarioContext.getContext(Context.ENGAGEMENT_PREFIX);

        logger.info("I create new engagement: " + engagementName);
        portfolioPage.createEngagement(cloudCabinet, contentFrom,
            language, engagementName, engagementType, periodEndDate,
            country, office, entity, entityChargeCode, industry, financialReportingFramework,
            engagementStandard, role);
    }

    @Then("View page after create successful")
    public void view_page_after_create_successful(DataTable dataTable){
        //Wait for app loading
        List<String> lst = dataTable.asList(String.class);
        String engagementName = lst.get(0);
        String status = lst.get(1);
        engagementName += getEngagementPrefix();

        logger.info("I should see the engagement name '" + engagementName + "' on Portfolio");
        Assert.assertTrue(String.format("Can not find engagement '%s' via status '%s'", engagementName, status), portfolioPage.checkResultAfterCreate(engagementName, status));
    }

    @Given("Open an existing engagement {string}")
    public void open_an_existing_engagement(String engagementName) {
        engagementName += getEngagementPrefix();
        logger.info("I open an existing engagement '" + engagementName + "' on Portfolio");
        this.portfolioPage.openEngagementByName(engagementName);
    }

    @Given("Open the Engagement Setting")
    public void openTheEngagementSetting() {
        logger.info("I open the Engagement Setting");
        portfolioPage.openEngagementSetting();
    }

    @And("I navigate to Portfolio page")
    public void iNavigateToPortfolioPage() {
        logger.info("I navigate to Portfolio page");
        portfolioPage.openPortfolioPage();
    }

    @And("I submit Archive on Engagement {string}")
    public void iSubmitArchiveOnEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I submit archive on engagement: " + engagement);
        portfolioPage.submitArchive(engagement);
    }

    @Then("I should see the Portfolio page")
    public void iShouldSeeThePortfolioPage() {
        logger.info("I should see the Portfolio page");
        portfolioPage.isLoad();
    }

    @Then("I check status Engagement {string} is {string}")
    public void iCheckStatusEngagementIs(String engagement, String status) {
        engagement += getEngagementPrefix();
        logger.info("I check status on engagement: " + engagement);
        portfolioPage.verifyEngagementStatus(engagement, status);
    }

    @And("I wait until engagement {string} status is changed from {string} to {string} in {int} minutes")
    public void iWaitUntilEngagementStatusIsChangedFromToInMinutes(String engagementName, String oldStatus, String newStatus, int timeout) {
        engagementName += getEngagementPrefix();
        logger.info(String.format("I wait status of engagement '%s' is changed from '%s' to '%s' in %d minutes", engagementName, oldStatus, newStatus, timeout));
        portfolioPage.waitEngagementChangeStatus(engagementName, oldStatus, newStatus, timeout);
    }

    @And("I reject Archive on Engagement {string}")
    public void iRejectArchiveOnEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I reject Archive engagement: " + engagement);
        portfolioPage.rejectArchive(engagement);
    }

    @And("I open an archive rejected engagement {string}")
    public void iOpenAnArchiveRejectedEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I open a archive reject engagement: " + engagement);
        portfolioPage.openArchiveRejectEngagement(engagement);
    }

    @And("I approve Archive on Engagement {string}")
    public void iApproveArchiveOnEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I approve archive engagement: " + engagement);
        portfolioPage.approveArchive(engagement);
    }

    @And("I submit Deletion on Engagement {string}")
    public void iSubmitDeletionOnEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I submit deletion on engagement: " + engagement);
        portfolioPage.submitDeletion(engagement);
    }

    @And("I reject Deletion on Engagement {string}")
    public void iRejectDeletionOnEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I reject deletion for engagement: " + engagement);
        portfolioPage.rejectDeletion(engagement);
    }

    @And("I approve Deletion on Engagement {string}")
    public void iApproveDeletionOnEngagement(String engagement) {
        engagement += getEngagementPrefix();
        logger.info("I approve deletion for engagement: " + engagement);
        portfolioPage.approveDeletion(engagement);
    }

    @And("I confirm independence")
    public void iConfirmIndependence() {
        logger.info("I confirm independence");
        portfolioPage.confirmIndependence();
    }
}

package stepdefs;

import com.sm.page.EngagementPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Map;

public class EngagementDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public EngagementDefinition() {
        this.engagementPage = EngagementPage.getInstance(webDriver);
    }

    @Then("Navigate to global navigation successfully")
    public void viewGlobalNavigation() {
        logger.info("I should see the Global Navigation");
        this.engagementPage.isLoad();
    }

    @Given("I open Trial Balance working paper")
    public void i_open_Trial_Balance_working_paper() {
        logger.info("I open Trial Balance on Global Navigation");
        this.engagementPage.openTrialBalanceMenu();
    }

    @Given("I open Working Paper {string}")
    public void iOpenWorkingPaper(String wpName) {
        logger.info("I open Working Paper '" + wpName + "'.");
        this.engagementPage.openWorkingPage(wpName);
    }

    @Given("I create Standard working paper")
    public void i_create_standard_working_paper(DataTable dataTable) {
        logger.info("I create standard Working Paper.");
        List<String> data = dataTable.asList(String.class);
        this.engagementPage.createStandardWorkingPaper(data);
    }

    @Given("I create Custom working paper {string}")
    public void i_create_custom_working_paper(String wpName) {
        logger.info("I create custom Working Paper.");
        this.engagementPage.createCustomWorkingPaper(wpName);
    }

    @Then("I check existing wp")
    public void iCheckExistingWp(DataTable dataTable) {
        logger.info("I check existing Working Papers.");
        List<String> lst = dataTable.asList(String.class);
        engagementPage.checkExitingWps(lst);
    }

    @Given("I open ROMM Summary")
    public void i_open_ROMM_summary() {
        logger.info("I open ROMM Summary on Global Navigation");
        this.engagementPage.openRiskSummary();
    }

    @And("I set filter in {string} table")
    public void i_set_filter_in_table(String name, DataTable dataTable) {
        logger.info("I set filter in " + name + " table");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        String column = lst.get(0).get("Column");
        String select = lst.get(0).get("Select");
        String value = lst.get(0).get("Value");
        this.engagementPage.setFilterInTable(type, column, select, value);
    }

    @And("I export file in {string} table")
    public void i_export_file_in_table(String name, DataTable dataTable) {
        logger.info("I export file in" + name + "table");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        this.engagementPage.exportFuncInTable(type);
    }

    @And("I export {string} file in all table")
    public void i_export_file_in_all_table(String name, DataTable dataTable) {
        logger.info("I export" + name + "file in all table");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        this.engagementPage.exportAll(type);
    }

    @Then("ROMM Summary - I check file {string} is downloaded successfully")
    public void controlTestingICheckFileIsDownloadedSuccessfully(String fileName) {
        logger.info("I check file is downloaded successfully");
        this.engagementPage.verifyAttachmentFileIsDownload(fileName);
    }

    @Then("I check existing Subphase")
    public void iCheckExistingSubphase(DataTable dataTable) {
        logger.info("I check existing Subphase");
        List<String> subphases = dataTable.asList(String.class);
        engagementPage.checkExistingSubphase(subphases);
    }

    @Then("I check non existing wp")
    public void iCheckNonExistingWp(DataTable dataTable) {
        logger.info("I check none existing WP");
        List<String> wps = dataTable.asList(String.class);
        engagementPage.checkDontExistingWps(wps);
    }

    @Given("I open File Check")
    public void iOpenFileCheck() {
        logger.info("I open File Check");
        engagementPage.openFileCheck();
    }

    @Given("I open working paper tab")
    public void iOpenWorkingPaperTab() {
        logger.info("I open working paper tab");
        engagementPage.openWorkingTab();
    }
}

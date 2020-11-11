package stepdefs;

import com.sm.page.LeadsheetPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Map;

public class LeadsheetDefinition extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public LeadsheetDefinition() {
        this.leadsheetPage = LeadsheetPage.getInstance(webDriver);
        this.leadsheetPage.isLoad();
    }

    @And("I check data table in LeadSheet")
    public void i_check_data_table_in_LeadSheet() {
       // Todo
    }

    @And("I open PAR from LeadSheet")
    public void i_open_par_from_LeadSheet() {
        logger.info("I open PAR from Leadsheet");
        this.leadsheetPage.openParWorkingPaper();
    }

    @Then("I navigate to PAR from LeadSheet successfully")
    public void i_navigate_to_par_from_LeadSheet() {
        logger.info("I should navigate to PAR successfully");
        this.leadsheetPage.ParWPisLoad();
    }

    @And("I open RA from LeadSheet")
    public void i_open_RA_from_LeadSheet() {
        logger.info("I open RA from Leadsheet");
        this.leadsheetPage.openRAWorkingPaper();
    }

    @Then("I navigate to RA from LeadSheet successfully")
    public void i_navigate_to_RA_from_LeadSheet() {
        logger.info("I should navigate to RA successfully");
        this.leadsheetPage.RAWPisLoad();
    }

    @And("I close PAR working paper tab")
    public void i_close_par_working_paper_tab() {
        logger.info("I close PAR working paper");
        this.leadsheetPage.closeWPOpenedFromLeadSheet(false);
    }

    @And("I close RA working paper tab")
    public void i_close_RA_working_paper_tab() {
        logger.info("I close RA working paper");
        this.leadsheetPage.closeWPOpenedFromLeadSheet(false);
    }

    @Then("I check overall in LeadSheet")
    public void i_check_overall_in_LeadSheet(DataTable dataTable) {
        logger.info("I check Materiality on Leadsheet");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double overall = Double.parseDouble(lst.get(0).get("Overall"));
        double pm = Double.parseDouble(lst.get(0).get("PM"));
        double ctt = Double.parseDouble(lst.get(0).get("CTT"));
        this.leadsheetPage.checkOverallOfLeadSheet(overall, pm, ctt);
    }

    @And("I close LeadSheet")
    public void iClosePAR() {
        logger.info("I close Leadsheet working paper");
        leadsheetPage.closeWP(false);
    }

    @Then("I should see list ABCOTD Material on Leadsheet as following")
    public void iShouldSeeListABCOTDMaterialOnLeadsheetAsFollowing(DataTable dataTable) {
        logger.info("I should see list Material ABCOTDs");
        leadsheetPage.verifyListABCOTDs(dataTable.asList(String.class));
    }
}

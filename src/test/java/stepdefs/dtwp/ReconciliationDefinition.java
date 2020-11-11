package stepdefs.dtwp;

import com.sm.page.dtwp.BareboneAccountPage;
import com.sm.page.dtwp.ReconciliationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.List;

public class ReconciliationDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ReconciliationDefinition() {
        reconciliationPage = ReconciliationPage.getInstance(webDriver);
    }

    @Then("I check the Agree Summary will be displayed")
    public void iCheckTheAgreeSummaryWillBeDisplayed() {
        logger.info("I should see the agree summary");
        reconciliationPage.verifyWidgetAgreeTrialBalance();
    }

    @Then("I check the widget table {string} will be displayed")
    public void iCheckTheWidgetTableWillBeDisplayed(String title) {
        logger.info("I should see the widget table '" + title + "'");
        reconciliationPage.verifyWidgetTableDisplayed(title);
    }

    @And("I type some data to widget table {string}")
    public void iTypeSomeDataToWidgetTable(String title, DataTable dataTable) {
        logger.info("I type data into widget table '" + title + "'");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        reconciliationPage.typeDataIntoWidgetSampleTable(title, data);
    }

    @Then("I verify data on Agree Summary card")
    public void iVerifyDataOnAgreeSummaryCard() {
        logger.info("I verify amount on Agree Summary Card");
        reconciliationPage.verifyDataOnAgreeSummaryCard();
    }

    @And("I add {int} rows to widget table {string}")
    public void iAddRowsToWidgetTable(int row, String title) {
        logger.info("I add " + row + " to widget table '" + title + "'");
        reconciliationPage.addNewRowToWidgetTable(row, title);
    }
}

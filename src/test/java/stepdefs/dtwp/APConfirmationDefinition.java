package stepdefs.dtwp;

import com.sm.page.dtwp.APConfirmationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class APConfirmationDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public APConfirmationDefinition(){
        apConfirmationPage = APConfirmationPage.getInstance(webDriver);
    }

    @And("I type some data to AP Perform table")
    public void iTypeSomeDataToAPPerformTable(DataTable dataTable) {
        logger.info("I type some on AP Perform");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        apConfirmationPage.typeOnAPPerform(data);
    }

    @And("I update alternative procedure on AP Confirmation")
    public void iUpdateAlternativeProcedureOnAPConfirmation() {
        logger.info("I update alternative procedure");
        apConfirmationPage.updateAlternativeProcedures();
    }


    @And("I open Testing Sheet {int} on AP Confirmation")
    public void iOpenTestingSheetOnAPConfirmation(int index) {
        logger.info("I open Testing Sheet index " + index);
        apConfirmationPage.openTestingSheet(index);
    }

    @Then("I should see the AP Testing Sheet")
    public void iShouldSeeTheAPTestingSheet() {
        logger.info("I should see Testing Sheet");
        apConfirmationPage.checkTestingSheetDisplay();
    }


    @And("I save and close AP Testing sheet")
    public void iSaveAndCloseAPTestingSheet() {
        logger.info("I save and close AP Testing sheet");
        apConfirmationPage.saveCloseTestingSheet();
    }

    @Then("I check value on AP Balance Summary table after saving Testing Sheet")
    public void iCheckValueOnAPBalanceSummaryTableAfterSavingTestingSheet(DataTable dataTable) {
        logger.info("I check data on Balance Summary table");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int sheetIndex = Integer.parseInt(lst.get(0).get("Sheet Index"));
        double unexplainedDifference = Double.parseDouble(lst.get(0).get("Unexplained difference"));
        String wereAnyExpectedNote = lst.get(0).get("Were any exceptions noted?");
        apConfirmationPage.verifyDataOnBalanceSummaryTable(sheetIndex, unexplainedDifference, wereAnyExpectedNote);
    }

    @And("I type some data to AP Testing Sheet")
    public void iTypeSomeDataToAPTestingSheet(DataTable dataTable) {
        logger.info("I type data to AP Testing Sheet");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        apConfirmationPage.typeOnTestingSheetSheet(data);
    }
}

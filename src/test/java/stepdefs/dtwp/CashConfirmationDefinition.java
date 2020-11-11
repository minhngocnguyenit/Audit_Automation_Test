package stepdefs.dtwp;

import com.sm.page.dtwp.CashConfirmationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.List;

public class CashConfirmationDefinition extends BaseDefinition {

    private Logger logger  = Logger.getLogger(this.getClass().getName());

    public CashConfirmationDefinition() {
        cashConfirmationPage = CashConfirmationPage.getInstance(webDriver);
        cashConfirmationPage.isLoad();
    }

    @And("I generate Samples\\/selections for testing on Cash")
    public void iGenerateSamplesSelectionsForTestingOnCash() {
        logger.info("I generate sample/selection testing on Cash");
        cashConfirmationPage.generateSampleSelectionTesting();
    }


    @And("I type data to Cash Perform yes foreign currency table")
    public void iTypeDataToCashPerformYesForeignCurrencyTable(DataTable dataTable) {
        logger.info("I type data on Cash Perform Yes foreign currency table.");
        logger.info("I type some data to Samples/Selections table");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        cashConfirmationPage.typeOnCashPerformYesCurrency(data);
    }

    @Then("I should see the Cash Perform Yes Foreign Currency table")
    public void iShouldSeeTheCashPerformYesForeignCurrencyTable() {
        logger.info("I check the Cash Perform Yes foreign currency");
        cashConfirmationPage.cashPerformYesForeignCurrencyDisplayed();
    }

    @Then("I should see the Cash Perform No Foreign Currency table")
    public void iShouldSeeTheCashPerformNoForeignCurrencyTable() {
        logger.info("I check the Cash Perform No foreign currency");
        cashConfirmationPage.cashPerformNoForeignCurrencyDisplayed();
    }
}

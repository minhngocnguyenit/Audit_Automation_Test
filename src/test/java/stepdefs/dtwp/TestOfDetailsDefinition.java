package stepdefs.dtwp;

import com.sm.page.dtwp.TestOfDetailsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;
import org.junit.Test;
import stepdefs.BaseDefinition;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class TestOfDetailsDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public TestOfDetailsDefinition() {
        testOfDetailsPage = TestOfDetailsPage.getInstance(webDriver);
    }

    @And("I type {int} to field {string}")
    public void iTypeToField(int number, String label) {
        logger.info("I type number " + 5 +" to field '" + label + "'");
        testOfDetailsPage.typeOnFormulaField(String.valueOf(number),label);
    }

    @And("I update sample\\/selections table")
    public void iUpdateSampleSelectionsTable() {
        logger.info("I update sample/selections table");
        testOfDetailsPage.updateSampleSelectionTable();
    }

    @And("I enter {string} to Source Document {string}")
    public void iEnterToSourceDocument(String value, String label) {
        logger.info("I enter '" + value + "' to source document '" + label + "'");
        testOfDetailsPage.typeOnSourceDocument(label, value);
    }

    @And("I generate Samples\\/selections for testing")
    public void iGenerateSamplesSelectionsForTesting() {
        logger.info("I generate samples/selections for testing");
        testOfDetailsPage.generateSampleSelectionForTesting();
    }

    @And("I type some data to widget table Samples\\/selections")
    public void iTypeSomeDataToWidgetTableSamplesSelections(DataTable dataTable) {
        logger.info("I type some data to Samples/Selections table");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        testOfDetailsPage.typeDataOnSampleSelectionTable(data);
    }

    @And("I type data to widget Testing Table")
    public void iTypeDataToWidgetTestingTable(DataTable dataTable) {
        logger.info("I type some data to Testing table");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        testOfDetailsPage.typeDataOnTestingTable(data);
    }
}

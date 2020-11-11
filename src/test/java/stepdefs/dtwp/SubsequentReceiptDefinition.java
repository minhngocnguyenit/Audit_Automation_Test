package stepdefs.dtwp;

import com.sm.page.dtwp.SubsequentReceiptPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import stepdefs.BaseDefinition;

import java.util.ArrayList;
import java.util.List;

public class SubsequentReceiptDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public SubsequentReceiptDefinition() {
        subsequentReceiptPage = SubsequentReceiptPage.getInstance(webDriver);
    }

    @And("I type some data to Subsequent Receipt Perform table")
    public void i_type_some_data_to_Subsequent_Receipt_Perform_table(io.cucumber.datatable.DataTable dataTable) {
        logger.info("I type some data to Subsequent Receipt perform");
        List<List<String>> data = new ArrayList<>(dataTable.asLists(String.class));
        //Remove first row because first row is presented header
        data.remove(0);
        subsequentReceiptPage.typeOnPerform(data);
    }
}

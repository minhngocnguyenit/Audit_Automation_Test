package stepdefs;

import com.sm.page.ConcludingAnalyticalProceduresPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class CarDefinition extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public CarDefinition() {
        this.concludingAnalyticalProceduresPage = ConcludingAnalyticalProceduresPage.getInstance(webDriver);
        this.concludingAnalyticalProceduresPage.isLoad();
    }

    @And("I set up CAR Working Paper")
    public void i_set_up_CAR_working_paper(DataTable dataTable) {
        logger.info("I setup CAR");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double amount = Double.parseDouble(lst.get(0).get("Amount"));
        double percent = Double.parseDouble(lst.get(0).get("Percent"));
        this.concludingAnalyticalProceduresPage.SetUpCar(amount,percent);
    }

    @Then("Checking CAR overall is shown correctly")
    public void checking_CAR_overall_is_shown_correctly(DataTable dataTable) {
        logger.info("I verify Overall, Performance Materiality and CTT on CAR.");
        this.concludingAnalyticalProceduresPage = ConcludingAnalyticalProceduresPage.getInstance(webDriver);
        this.concludingAnalyticalProceduresPage.isLoad();
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double overall = Double.parseDouble(lst.get(0).get("Overall"));
        double pm = Double.parseDouble(lst.get(0).get("PM"));
        double ctt = Double.parseDouble(lst.get(0).get("CTT"));
        this.concludingAnalyticalProceduresPage.checkOverall(overall, pm, ctt);
    }

    @And("I close CAR")
    public void iCloseCAR() {
        logger.info("I close CAR.");
        concludingAnalyticalProceduresPage.closeWP(false);
    }
}

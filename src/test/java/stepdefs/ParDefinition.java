package stepdefs;

import com.sm.page.ParScopingPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ParDefinition extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public ParDefinition() {
        this.parScopingPage = ParScopingPage.getInstance(webDriver);
          this.parScopingPage.isLoad();
    }

    @And("I set up Par&Scoping Working Paper")
    public void i_set_up_ParScoping_working_paper(DataTable dataTable) throws InterruptedException {
        logger.info("I setup PAR");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double amount = Double.parseDouble(lst.get(0).get("Amount"));
        double percent = Double.parseDouble(lst.get(0).get("Percent"));
        this.parScopingPage.SetUpPar(amount,percent);
    }

    @Then("Checking overall is shown correctly")
    public void checking_overall_is_shown_correctly(DataTable dataTable) {
        logger.info("I verify Overall, Performance Materiality and CTT on PAR.");
        this.parScopingPage = ParScopingPage.getInstance(webDriver);
        this.parScopingPage.isLoad();
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double overall = Double.parseDouble(lst.get(0).get("Overall"));
        double pm = Double.parseDouble(lst.get(0).get("PM"));
        double ctt = Double.parseDouble(lst.get(0).get("CTT"));
        this.parScopingPage.checkOverall(overall, pm, ctt);
    }

    @And("I close PAR")
    public void iClosePAR() {
        logger.info("I close PAR.");
        parScopingPage.closeWP(false);
    }
}

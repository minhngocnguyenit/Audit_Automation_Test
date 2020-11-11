package stepdefs;

import com.sm.models.AbcotdsMateriality;
import com.sm.page.MaterialityPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaterialityDefinition extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public MaterialityDefinition() {
        this.materialityPage = MaterialityPage.getInstance(webDriver);
        this.materialityPage.isLoad();
    }

    @And("I set up Materiality working paper")
    public void i_set_up_Materiality_working_paper(DataTable dataTable) {
        logger.info("I setup Overall, Performance Materiality and CTT on Materiality");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        double overall = Double.parseDouble(lst.get(0).get("Overall"));
        double pm = Double.parseDouble(lst.get(0).get("PM"));
        double ctt = Double.parseDouble(lst.get(0).get("CTT"));
        this.materialityPage.setupMateriality(overall, pm, ctt);
    }

    @And("I set up ABCOTDs Materiality")
    public void iSetUpABCOTDsMateriality(DataTable dataTable) throws InterruptedException {
        logger.info("I enter ABCOTDs Materiality");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<AbcotdsMateriality> data = new ArrayList<>();
        for(Map m : lst) {
            AbcotdsMateriality a = new AbcotdsMateriality((String)m.get("abcotdName"), (String)m.get("determinedMateriality"),(String)m.get("determinedPM"));
            data.add(a);
        }
        this.materialityPage.enterABCOTDsMateriality(data);
    }

    @And("I close Materiality")
    public void iCloseMateriality() {
        logger.info("I close Materiality");
        this.materialityPage.closeWP(true);
    }
}

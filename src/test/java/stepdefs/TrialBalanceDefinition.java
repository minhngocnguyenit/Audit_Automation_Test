package stepdefs;

import com.sm.models.CustomComponents;
import com.sm.page.TrialBalancePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrialBalanceDefinition extends BaseDefinition{

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public TrialBalanceDefinition() {
        this.trialBalancePage = TrialBalancePage.getInstance(webDriver);
    }

    @Then("I should be at Trial Balance working paper successfully")
    public void iNavigateToTrialBalanceWorkingPaperSuccessfully() {
        logger.info("I should be at Trial Balance working paper.");
        this.trialBalancePage.isLoad();
    }

    @And("I upload a Trial Balance mapping file")
    public void i_upload_a_Trial_Balance_mapping_file(DataTable dataTable) {
        logger.info("I update the Trial Balance mapping file.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        String path = lst.get(0).get("path");
        int accessMateriality = Integer.parseInt(lst.get(0).get("accessMateriality"));
        this.trialBalancePage.uploadTrialBalanceFile(type, path, null, accessMateriality);
    }

    @Then("Checking file is uploaded correctly")
    public void checking_file_is_uploaded_correctly(DataTable dataTable) {
        logger.info("I verify file is updated successfully.");
        //| UploadType | FileName | Period | PeriodEnd | DateUploaded | Status |
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        String type = lst.get(0).get("UploadType");
        String fileName = lst.get(0).get("FileName");
        String period = lst.get(0).get("Period");
        String periodEnd = lst.get(0).get("PeriodEnd");
        String dateUploaded = lst.get(0).get("DateUploaded");
        String status = lst.get(0).get("Status");

       trialBalancePage.checkResultAfterUploadFile(type, fileName, period, periodEnd, dateUploaded, status);
    }

    @And("I uploaded a CP Interim file")
    public void i_uploaded_a_cp_interim_file(DataTable dataTable) throws InterruptedException {
        logger.info("I update CP Interim data");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        String path = lst.get(0).get("path");
        int accessMateriality = Integer.parseInt(lst.get(0).get("accessMateriality"));
        this.trialBalancePage.uploadTrialBalanceFile(type, path, null, accessMateriality);
    }

    @And("I uploaded a CP Final file")
    public void i_uploaded_a_cp_final_file(DataTable dataTable) throws InterruptedException {
        logger.info("I update CP Final data");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        String path = lst.get(0).get("path");
        int accessMateriality = Integer.parseInt(lst.get(0).get("accessMateriality"));
        this.trialBalancePage.uploadTrialBalanceFile(type, path, null, accessMateriality);
    }

    @And("I close trial balance tab")
    public void iCloseTrialBalanceTab() {
        logger.info("I close the Trial Balance");
        trialBalancePage.closeWP(false);
    }

    @And("I create Custom Content Area")
    public void i_create_custom_content_area(DataTable dataTable) throws InterruptedException {
        logger.info("I create Custom Content Area");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        String name = lst.get(0).get("Name");
        this.trialBalancePage.createCustomCA(name);
    }

    @And("I select linked custom ABCOTDs to custom CA")
    public void i_link_customABCOTDs_to_custom_CA(DataTable dataTable){
        logger.info("I select linked custom ABCOTDs to custom CA");
        List<String> lst = dataTable.asList(String.class);
        this.trialBalancePage.selectLinkedCustomABCOTDs(lst);
    }

    @And("I map custom ABCOTDs to Linked content Areas")
    public void i_map_customABCOTDs_to_linked_content_areas(DataTable dataTable) {
        logger.info("I map custom ABCOTDs to Linked content Areas");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        List<CustomComponents> data = new ArrayList<>();
        lst.forEach( m -> data.add(new CustomComponents(m.get("abcotd"), m.get("contentAreas"))));
        trialBalancePage.mapCustomABCOTDsToLinkedContentAreas(data);
    }

    @And("I close Manage custom components")
    public void iCloseManageCustomComponentsTab() {
        logger.info("I close Manage custom components");
        this.trialBalancePage.closeManageCustomComponents();
    }

    @And("I select some accounts in Account balance view")
    public void i_select_some_account_in_Account_balance_view(DataTable dataTable) throws InterruptedException {
        logger.info("I select some account in Account balance view");
        List<String> lst = dataTable.asList(String.class);
        this.trialBalancePage.selectAccountInAccountBalanceView(lst);
    }

    @And("I add journal entry with {string} type")
    public void i_add_journal_entry(String type) {
        logger.info("I add journal entry " + type + " type");
        this.trialBalancePage.addJournalEntry(type);
    }

    @And("I input amount into journal entry")
    public void i_input_amount_into_journal_entry(DataTable dataTable) {
        logger.info("I input amount into journal entry");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        String amount = lst.get(0).get("Amount");
        String text = lst.get(0).get("Text");
        this.trialBalancePage.inputAmountInJournalEntry(amount, text);
    }

    @And("I {string} journal entry")
    public void i_post_journal_entry(String name, DataTable dataTable) {
        logger.info("I " + name + " journal entry");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        int type = Integer.parseInt(lst.get(0).get("type"));
        this.trialBalancePage.postJournalEntry(type);
    }
}

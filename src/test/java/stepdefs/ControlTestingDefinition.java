package stepdefs;

import com.sm.models.Control;
import com.sm.models.Romm;
import com.sm.page.ControlTestingPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ControlTestingDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public ControlTestingDefinition() {
        controlTestingPage = ControlTestingPage.getInstance(webDriver);
        controlTestingPage.isLoad();
        controlTestingPage.enableEditing();
    }

    @And("Control Testing - I open {string} section")
    public void controlTestingIOpenSection(String section) {
        logger.info("I open '" + section + "' section on Control Testing.");
        controlTestingPage.openSectionByName(section);
    }

    @Then("Control Testing - I check control details data on Overview")
    public void controlTestingICheckControlDetailsDataOnOverview(DataTable dataTable) {
        logger.info("I check control details on Overview section - Control Testing.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Control control = new Control();
        control.setControlId(lst.get(0).get("controlId"));
        control.setTitle(lst.get(0).get("controlTitle"));
        control.setDescription(lst.get(0).get("controlDescription"));
        controlTestingPage.verifyControlDetailData(control);
    }

    @Then("Control Testing - I check control information on Overview")
    public void controlTestingICheckControlInformationOnOverview(DataTable dataTable) {
        logger.info("I check control information on Overview section - Control Testing.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Control control = new Control();
        control.setNature(lst.get(0).get("nature"));
        control.setFrequency(lst.get(0).get("frequency"));
        control.setApproach(lst.get(0).get("approach"));
        control.setType(lst.get(0).get("type"));
        control.setOeTestingStrategy(lst.get(0).get("oeTestingStrategy"));
        control.setOeDateLastTested(lst.get(0).get("oeDateLastTested"));
        controlTestingPage.verifyControlInformation(control);
    }

    @Then("Control Testing - I check control conclusion on Overview")
    public void controlTestingICheckControlConclusionOnOverview(DataTable dataTable) {
        logger.info("I check control conclusion on Overview section - Control Testing.");
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        Control control = new Control();
        control.setDesignConclusion(lst.get(0).get("controlConclusion"));
        control.setImplementationConclusion(lst.get(0).get("implementationConclusion"));
        control.setOeConclusion(lst.get(0).get("oeConclusion"));
        controlTestingPage.verifyControlConclusion(control);
    }

    @Then("Control Testing - T check Risk Associated on Overview")
    public void controlTestingTCheckRiskAssociatedAsRiskIDTitleAssertion(DataTable dataTable) {
        logger.info("I check Risk associated on Overview section - Control Testing.");
        List<Romm> romms = new ArrayList<>();
        List<Map<String, String>> lst = dataTable.asMaps(String.class, String.class);
        lst.forEach(e -> {
            Romm r = new Romm();
            r.setId(e.get("riskID"));
            r.setTitle(e.get("title"));
            r.setAssertion(Arrays.asList(e.get("assertion").split(",")));
            romms.add(r);
        });
        controlTestingPage.verifyRisk(romms);
    }

    @Then("I close Control Testing wp")
    public void iCloseControlTestingWp() {
        logger.info("I close Control Testing");
        controlTestingPage.closeWP(false);
    }

    @And("Control Testing - I open file on Attach Common tool")
    public void controlTestingIOpenFileOnAttachCommonTool() {
        logger.info("I open file on Attach Common Tool");
        controlTestingPage.openFileOnAttachCommonTool();
    }

    @Then("Control Testing - I answer {string} to Tailoring question {string}")
    public void iAnswerToTailoringQuestion(String heading, String answer) {
        logger.info("I answer '" + answer + "' to Tailoring question '" + heading + "'.");
        controlTestingPage.answerTailoringQuestion(heading, answer);
    }

    @And("Control Testing - I download Attach File")
    public void controlTestingIDownloadAttachFile() {
        logger.info("I download file on Attach Common Tool");
        controlTestingPage.downloadFileOnAttachCommonTool();
    }

    @Then("Control Testing - I check file {string} is downloaded successfully")
    public void controlTestingICheckFileIsDownloadedSuccessfully(String fileName) {
        logger.info("I check file is downloaded successfully");
        controlTestingPage.verifyAttachmentFileIsDownload(fileName);
    }

    @And("Control - I close Attachment GCT")
    public void controlICloseAttachmentGCT() {
        logger.info("I close Attachment GCT");
        controlTestingPage.closeGCTModal();
    }
}
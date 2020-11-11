package stepdefs;


import com.sm.page.FileCheckPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;

public class FileCheckDefinition extends BaseDefinition {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public FileCheckDefinition() {
        fileCheckPage = FileCheckPage.getInstance(webDriver);
    }

    @Then("I should see the File Check is loaded successfully")
    public void iShouldSeeTheFileCheckIsLoadedSuccessfully() {
        logger.info("I should see the File Check is loaded successfully");
        fileCheckPage.isLoad();
    }

    @Then("I should see the modal if the reported date is not set")
    public void iShouldSeeTheModalIfTheReportedDateIsNotSet() {
        logger.info("I should see the modal if the reported date is not set");
        fileCheckPage.checkFileCheckModal();
    }

    @And("I close File Check Modal")
    public void iCloseFileCheckModal() {
        logger.info("I close file check modal");
        fileCheckPage.closeFileCheckModal();
    }

    @And("I export file in File Check")
    public void iExportFileInFileCheck() {
        logger.info("I export file check");
        fileCheckPage.export();
    }

    @Then("File Check - I check file {string} is downloaded successfully")
    public void fileCheckICheckFileIsDownloadedSuccessfully(String fileName) {
        logger.info("Check export file");
        fileCheckPage.verifyAttachmentFileIsDownload(fileName);
    }

    @And("I close file check")
    public void iCloseFileCheck() {
        logger.info("I close file check");
        fileCheckPage.closeWP(false);
    }
}

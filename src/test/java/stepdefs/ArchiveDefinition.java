package stepdefs;

import com.sm.page.ArchivePage;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;

public class ArchiveDefinition extends BaseDefinition {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ArchiveDefinition() {
        archivePage = ArchivePage.getInstance(webDriver);
    }

    @And("I process the submit archive flow")
    public void iProcessTheSubmitArchiveFlow() {
        logger.info("I process submit archive");
        archivePage.processArchive();
    }

    @And("I process approve archive")
    public void iProcessApproveArchive() {
        logger.info("I process approve archive");
        archivePage.processApproveArchive();
    }
}

package stepdefs;

import com.sm.context.Context;
import com.sm.context.ScenarioContext;
import com.sm.helper.OSValidator;
import com.sm.page.*;
import com.sm.page.dtwp.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseDefinition{

    protected static WebDriver webDriver;

    protected LoginPage loginPage;
    protected PortfolioPage portfolioPage;
    protected EngagementPage engagementPage;
    protected TrialBalancePage trialBalancePage;
    protected MaterialityPage materialityPage;
    protected ParScopingPage parScopingPage;
    protected RiskAssessmentPage riskAssessmentPage;
    protected FSLPage fslPage;
    protected ControlTestingPage controlTestingPage;
    protected BareboneAccountPage bareboneAccountPage;
    protected SAPPage sapPage;
    protected TestOfDetailsPage testOfDetailsPage;
    protected ReconciliationPage reconciliationPage;
    protected LeadsheetPage leadsheetPage;
    protected ConcludingAnalyticalProceduresPage concludingAnalyticalProceduresPage;
    protected SubsequentReceiptPage subsequentReceiptPage;
    protected CashConfirmationPage cashConfirmationPage;
    protected APConfirmationPage apConfirmationPage;
    protected ARConfirmationPage arConfirmationPage;
    protected EngagementSettingPage engagementSettingPage;
    protected ArchivePage archivePage;
    protected FileCheckPage fileCheckPage;
    protected AuditSummaryPage auditSummaryPage;

    public void initPage() {
        if(OSValidator.isMac())
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        if(OSValidator.isWindows())
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        String downloadFilepath = System.getProperty("user.dir") + File.separator + "download";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    public String getEngagementPrefix() {
        String engagementPrefix = (String) ScenarioContext.getContext(Context.ENGAGEMENT_PREFIX);
        return  engagementPrefix == null ? "" : engagementPrefix;
    }
}
